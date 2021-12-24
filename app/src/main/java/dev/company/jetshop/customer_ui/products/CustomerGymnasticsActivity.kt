package dev.company.jetshop.customer_ui.products

import android.annotation.SuppressLint
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import dev.company.jetshop.products.ProductDetails
import dev.company.jetshop.R

class CustomerGymnasticsActivity: AppCompatActivity() {

    var sqliteDB: SQLiteDatabase?=null
    var gymnasticslist: ListView?= null

    @SuppressLint("WrongConstant", "Recycle")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_gymnastics)

        gymnasticslist = findViewById (R.id.gymnasticslist)
        sqliteDB = this.openOrCreateDatabase("gymnasticsregister.db", MODE_APPEND,null)

        val readdata = sqliteDB?.rawQuery("select * from user_gymnasticsregister", null)

        val productsdetail = ArrayList <ProductDetails> ()

        while (readdata!!.moveToNext()) {

            val product = ProductDetails ()
            product.id = readdata.getInt(0)
            product.Productname=  readdata.getString(1)
            product.Productprice = readdata.getString(2)
            product.Date = readdata.getString(3)
            productsdetail.add(product)
        }
        val adapter = CustomerGymnasticsAdapter(this@CustomerGymnasticsActivity, productsdetail,layoutInflater)
        gymnasticslist?.adapter = adapter
        adapter.notifyDataSetChanged()
    }
}