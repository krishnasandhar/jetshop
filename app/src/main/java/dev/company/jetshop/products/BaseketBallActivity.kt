package dev.company.jetshop.products

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import dev.company.jetshop.R

class BaseketBallActivity: AppCompatActivity(), BaseketballAdapter.go, BaseketballAdapter.go1{

    var baseketballlist:ListView?=null
    var sqliteDB:SQLiteDatabase?=null

    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_baseket_ball)

        baseketballlist = findViewById(R.id.baseketballlist)
        sqliteDB = this.openOrCreateDatabase("baseketregister.db", MODE_APPEND, null)

        val TABLE_CREATE =
            "create table if not exists user_baseketregister( id integer PRIMARY KEY AUTOINCREMENT,productname varchar(20),productprice varchar(15),date varchar(20))"
        sqliteDB?.execSQL(TABLE_CREATE)
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.products_join, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.add-> {
                showAlerts()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    @SuppressLint("Recycle")
    override fun onResume() {
        super.onResume()

        val readdata = sqliteDB?.rawQuery("select * from user_baseketregister", null)

        val productsdetail = ArrayList <ProductDetails> ()

        while (readdata!!.moveToNext()) {

            val product = ProductDetails ()
            product.id = readdata.getInt(0)
            product.Productname=  readdata.getString(1)
            product.Productprice = readdata.getString(2)
            product.Date = readdata.getString(3)
            productsdetail.add(product)
        }
        val adapter = BaseketballAdapter(this@BaseketBallActivity,productsdetail,layoutInflater,this,this)
        baseketballlist?.adapter = adapter
        adapter.notifyDataSetChanged()
    }
    private fun showAlerts() {

        val builder = AlertDialog.Builder(this@BaseketBallActivity).create()

        val view = layoutInflater.inflate(R.layout.addnewproduct,null)

        builder.setView(view)

        val btn_save = view.findViewById <TextView> (R.id.btn_save)

        val edt_productname = view.findViewById <EditText> (R.id.edt_productname)

        val edt_price = view.findViewById <EditText> (R.id.edt_price)

        val edt_date = view.findViewById <EditText> (R.id.edt_date)

        btn_save.setOnClickListener {

            val productname = edt_productname?.text.toString()

            val price = edt_price?.text.toString()

            val date = edt_date?.text.toString()

            if (productname.length==0) {
                Toast.makeText(this, "Enter the ProductName", Toast.LENGTH_SHORT).show()
            } else if (price.length==0) {
                Toast.makeText(this, "Enter the Price", Toast.LENGTH_SHORT).show()
            } else if (date.length==0) {
                Toast.makeText(this, "Enter the Date", Toast.LENGTH_SHORT).show()
            } else {
                sqliteDB?.execSQL("insert into user_baseketregister(productname,productprice,date) values('"+ productname+"','"+ price+"','"+date+"')")
                builder.dismiss()
                onResume()
            }
        }
        builder.setCanceledOnTouchOutside(false)
        builder.show()
    }
    override fun pass(value: String?) {
        onResume()
    }
    override fun pass1(value: String?) {
        onResume()
    }
}