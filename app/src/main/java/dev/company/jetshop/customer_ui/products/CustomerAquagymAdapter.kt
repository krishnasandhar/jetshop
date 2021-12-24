package dev.company.jetshop.customer_ui.products

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import dev.company.jetshop.products.ProductDetails
import dev.company.jetshop.R
import dev.company.jetshop.customer_ui.payment.MainActivity

class CustomerAquagymAdapter (
    val context:Activity,
    val arrayList:ArrayList<ProductDetails>,
    val layoutInflater:LayoutInflater
    ) :BaseAdapter() {

    var sqliteDB: SQLiteDatabase?=null

    override fun getCount(): Int {
        return arrayList.size
    }
    override fun getItem(position: Int): Any {
        return position
    }
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }
    @SuppressLint("SetTextI18n", "ViewHolder", "WrongConstant")
    override fun getView(position:Int, convertView:View?, parent:ViewGroup?):View {

        var convertView = convertView

        sqliteDB = context.openOrCreateDatabase("cartregister.db",AppCompatActivity.MODE_APPEND,null)
        val TABLE_CREATE = "create table if not exists user_cartregister( id integer PRIMARY KEY AUTOINCREMENT,productname varchar(20),productprice varchar(15),date varchar(20))"
        sqliteDB?.execSQL(TABLE_CREATE)

        convertView = LayoutInflater.from(context).inflate(R.layout.customersview, parent, false)

        val productlayout: LinearLayout = convertView.findViewById(R.id.productlayout)
        val btn_buy:Button = convertView.findViewById(R.id.buy)
        val productname: TextView = convertView.findViewById(R.id.productname)
        val price: TextView = convertView.findViewById(R.id.price)
        val date: TextView = convertView.findViewById(R.id.date)
        val img_cart: ImageView = convertView.findViewById(R.id.img_cart)

        val product: ProductDetails = arrayList[position]

        productname.text = product.Productname
        price.text = "Rs. "+product.Productprice
        date.text = "Date: "+product.Date

        btn_buy.setOnClickListener {
            val intent = Intent(this.context, MainActivity::class.java)
            context.startActivity(intent)
        }

        img_cart.setOnClickListener {
            val prod: ProductDetails = arrayList[position]
            sqliteDB?.execSQL("insert into user_cartregister(productname,productprice,date) values('"+ prod.Productname+"','"+ prod.Productprice+"','"+prod.Date+"')")
        }
        productlayout.setOnClickListener {

        }
        return convertView
    }
}