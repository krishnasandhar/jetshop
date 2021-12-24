package dev.company.jetshop.customer_ui.payment

import android.app.ProgressDialog
import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import com.android.volley.AuthFailureError
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Response
import com.android.volley.RetryPolicy
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.braintreepayments.api.dropin.DropInActivity
import com.braintreepayments.api.dropin.DropInRequest
import com.braintreepayments.api.dropin.DropInResult
import dev.company.jetshop.R
import java.util.HashMap

class MainActivity: AppCompatActivity() {

    var API_GET_TOKEN="https://www.uzhavanstudios.com/santhosh/HeroApi/BrainTree/main.php"
    var API_CHECKOUT="https://www.uzhavanstudios.com/santhosh/HeroApi/BrainTree/mycheckout.php"
    var token:String?=null
    var amount:String?=null
    var paramsHash:HashMap<String,String>?=null
    var btn_pay:Button?=null
    var edit_amount:EditText?=null
    var group_payment:LinearLayout?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edit_amount = findViewById<View>(R.id.edit_amount) as EditText
        btn_pay = findViewById<View>(R.id.btn_pay) as Button
        group_payment = findViewById<View>(R.id.group_payment) as LinearLayout

        getToken().execute()

        btn_pay!!.setOnClickListener {
            Log.e("token ", "token - $token")
            submitPayment()
        }
    }
    private fun submitPayment() {
        val payValue = edit_amount!!.text.toString()
        if (!payValue.isEmpty()) {
            val dropInRequest = DropInRequest().clientToken(token)
            startActivityForResult(dropInRequest.getIntent(this), REQUEST_CODE)
        } else
            Toast.makeText(this,"Enter a valid amount for payment",Toast.LENGTH_SHORT).show()
    }
    private fun sendPayments() {
        val queue = Volley.newRequestQueue(this)
        val stringRequest: StringRequest = object : StringRequest(
            Method.POST, API_CHECKOUT,
            Response.Listener { response ->
                if (response.contains("Successful")) {
                    Toast.makeText(this, "Payment Success", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Payment Failed", Toast.LENGTH_SHORT).show()
                }
                Log.d("Response", response)
            },
            Response.ErrorListener { error -> Log.d("Err", error.toString()) }) {
            @Throws(AuthFailureError::class)
            override fun getParams(): Map <String, String> {
                return paramsHash!!
            }
            @Throws(AuthFailureError::class)
            override fun getHeaders(): Map <String, String> {
                val params: MutableMap<String, String> = HashMap()
                params["Content-type"] = "application/x-www-form-urlencoded"
                return params
            }
        }
        val mRetryPolicy: RetryPolicy = DefaultRetryPolicy (0,
            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        )
        stringRequest.retryPolicy = mRetryPolicy
        queue.add(stringRequest)
    }
    private inner class getToken: AsyncTask<Any?, Any?, Any?>() {
        var mDailog: ProgressDialog? = null
        override fun doInBackground (objects: Array <Any?> ): Any? {

            val jReq: StringRequest = object: StringRequest(
                Method.GET, API_GET_TOKEN,
                Response.Listener { response ->

                    try {
                        token = ""+response
                        Log.e("response ", "" + response)
                    } catch (e: Exception) {
                        Log.i("MaintActivity", e.message.toString())
                    }
                },
                Response.ErrorListener { volleyError ->
                    if (volleyError != null) {
                        Log.i("MaintActivity", volleyError.message.toString())
                    }
                })
            {
                override fun getParams(): Map <String, String> {
                    val `object`: MutableMap <String, String> = HashMap()
                    return `object`
                }
                @Throws(AuthFailureError::class)
                override fun getHeaders(): Map <String, String> {
                    val params: MutableMap <String, String> = HashMap()
                    params["Content-Type"] = "application/x-www-form-urlencoded"
                    return params
                }
            }
            val requestQueue = Volley.newRequestQueue(applicationContext)
            requestQueue.add(jReq)
            mDailog!!.dismiss()
            return null
        }
        override fun onPreExecute() {
            super.onPreExecute()
            mDailog = ProgressDialog(
                this@MainActivity,
                android.R.style.Theme_DeviceDefault_Light_Dialog)
            mDailog!!.setCancelable(false)
            mDailog!!.setMessage("Loading Wallet, Please Wait")
            mDailog!!.show()
        }
        override fun onPostExecute(o: Any?) {
            super.onPostExecute(o)
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Log.e("token1 ", "token - $resultCode")
                val result: DropInResult =
                    data!!.getParcelableExtra(DropInResult.EXTRA_DROP_IN_RESULT)!!
                val nonce = result.paymentMethodNonce
                val strNounce = nonce!!.nonce
                if (!edit_amount!!.text.toString().isEmpty()) {
                    amount = edit_amount!!.text.toString()
                    paramsHash = HashMap()
                    paramsHash!!["amount"] = amount!!
                    paramsHash!!["nonce"] = strNounce
                    paramsHash!!["paymentMethodNonce"] = strNounce
                    sendPayments()
                } else {
                    Toast.makeText(this, "Please enter a valid amount", Toast.LENGTH_SHORT).show()
                }
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "User canceled", Toast.LENGTH_SHORT).show()
            } else {
                val error = data!!.getSerializableExtra(DropInActivity.EXTRA_ERROR) as Exception?
                Log.d("Err", error.toString())
            }
        }
    }
    companion object {
        private const val REQUEST_CODE = 1234
    }
}