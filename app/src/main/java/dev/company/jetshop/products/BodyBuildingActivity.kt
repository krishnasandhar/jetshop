package dev.company.jetshop.products

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import dev.company.jetshop.R

class BodyBuildingActivity:AppCompatActivity(), BodyBuildingAdapter.point, BodyBuildingAdapter.point1 {

    var sqliteDB:SQLiteDatabase?=null
    var bodybuildinglist:ListView?=null

    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_body_building)

        bodybuildinglist = findViewById(R.id.bodybuildinglist)
        sqliteDB = this.openOrCreateDatabase("bodybuildregister.db", MODE_APPEND, null)

        val TABLE_CREATE =
            "create table if not exists user_bodybuildregister( id integer PRIMARY KEY AUTOINCREMENT,productname varchar(20),productprice varchar(15),date varchar(20))"
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

        val readdata = sqliteDB?.rawQuery("select * from user_bodybuildregister", null)

        val productsdetail = ArrayList <ProductDetails> ()

        while (readdata!!.moveToNext()) {

            val product = ProductDetails ()
            product.id = readdata.getInt(0)
            product.Productname=  readdata.getString(1)
            product.Productprice = readdata.getString(2)
            product.Date = readdata.getString(3)
            productsdetail.add(product)
        }
        val adapter = BodyBuildingAdapter(this@BodyBuildingActivity,productsdetail,layoutInflater,this,this)
        bodybuildinglist?.adapter = adapter
        adapter.notifyDataSetChanged()
    }
    private fun showAlerts() {

        val builder = AlertDialog.Builder(this@BodyBuildingActivity).create()

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
                sqliteDB?.execSQL("insert into user_bodybuildregister(productname,productprice,date) values('"+ productname+"','"+ price+"','"+date+"')")
                builder.dismiss()
                onResume()
            }
        }
        builder.setCanceledOnTouchOutside(false)
        builder.show()
    }
    override fun Moving(value: String?) {
        onResume()
    }
    override fun Moving2(value: String?) {
        onResume()
    }
}