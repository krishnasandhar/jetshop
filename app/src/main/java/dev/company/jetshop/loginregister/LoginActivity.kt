package dev.company.jetshop.loginregister

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import dev.company.jetshop.R
import dev.company.jetshop.customer_ui.main.CustomerMainpage
import dev.company.jetshop.ui.main.MainPage

class LoginActivity:AppCompatActivity() {

    private var edt_email: EditText?=null
    private var txt_skiplogin: TextView?=null
    private var txt_otp: TextView?=null
    private var gotoregister: TextView?=null
    private var account1: LinearLayout?=null
    private var account2: LinearLayout?=null
    private var scrollview1: ScrollView?=null
    private var scrollview2: ScrollView?=null

    @SuppressLint("WrongConstant", "Recycle")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        edt_email     = findViewById (R.id.edt_email)
        txt_skiplogin = findViewById (R.id.txt_skiplogin)
        txt_otp       = findViewById (R.id.txt_otp)
        gotoregister  = findViewById (R.id.gotoregister)
        account1      = findViewById (R.id.account1)
        account2      = findViewById (R.id.account2)
        scrollview1   = findViewById (R.id.scroll1)
        scrollview2   = findViewById (R.id.scroll2)

        txt_otp?.setOnClickListener {

            val email = edt_email?.text.toString()

            if (email.isEmpty()) {

                Toast.makeText(this, "Enter your Email or Mobile", Toast.LENGTH_SHORT).show()

            } else if (email=="hellojetshop021@gmail.com") {

                Toast.makeText(this, "Welcome Admin", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MainPage::class.java)
                startActivity(intent)

            } else {

                val sqlite = this.openOrCreateDatabase("register.db",MODE_APPEND,null)

                val readdata = sqlite?.rawQuery("select * from user_register where email ='"+email+"' or mobile= '"+email+"' ",null)

                var is_login = false

                while (readdata!!.moveToNext()) {

                    is_login = true

                }
                if (is_login) {

                    Toast.makeText(this, "Successfully Logined", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, CustomerMainpage::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this, "Invalid email or mobile", Toast.LENGTH_SHORT).show()
                }
            }
        }
        txt_skiplogin?.setOnClickListener {
            startActivity(Intent(this, CustomerMainpage::class.java))
            finish()
        }
        gotoregister?.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}