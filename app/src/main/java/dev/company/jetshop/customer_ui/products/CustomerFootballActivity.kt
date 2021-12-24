package dev.company.jetshop.customer_ui.products

import android.annotation.SuppressLint
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import dev.company.jetshop.products.ProductDetails
import dev.company.jetshop.R

class CustomerFootballActivity: AppCompatActivity() {

    var sqliteDB: SQLiteDatabase?=null
    var footballlist: ListView?= null

    @SuppressLint("WrongConstant", "Recycle")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_football)

        footballlist = findViewById (R.id.footballlist)
        sqliteDB = this.openOrCreateDatabase("footballregister.db", MODE_APPEND,null)

        val readdata = sqliteDB?.rawQuery("select * from user_footballregister", null)

        val productsdetail = ArrayList <ProductDetails> ()

        while (readdata!!.moveToNext()) {

            val product = ProductDetails ()
            product.id = readdata.getInt(0)
            product.Productname=  readdata.getString(1)
            product.Productprice = readdata.getString(2)
            product.Date = readdata.getString(3)
            productsdetail.add(product)
        }
        val adapter = CustomerFootballAdapter(this@CustomerFootballActivity, productsdetail,layoutInflater)
        footballlist?.adapter = adapter
        adapter.notifyDataSetChanged()
    }
}