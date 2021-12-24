package dev.company.jetshop.products

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import dev.company.jetshop.R

class BaseballAdapter (
    val context: Activity,
    val arrayList: ArrayList<ProductDetails>,
    val layoutInflater: LayoutInflater,
    var v: move,
    var v1: move1,
): BaseAdapter() {

    var sqliteDB: SQLiteDatabase?=null
    var readdata = sqliteDB?.rawQuery("select * from user_baseballregister.db", null)

    override fun getCount(): Int {
        return arrayList.size
    }
    interface move {
        fun Moving(value: String?)
    }
    interface move1 {
        fun Moving1(value: String?)
    }
    override fun getItem(position: Int): Any {
        return position
    }
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }
    @SuppressLint("WrongConstant", "SetTextI18n", "ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        var convertView = convertView

        val productname: TextView
        val price: TextView
        val date: TextView
        val img_update: ImageView
        val img_delete: ImageView

        convertView = LayoutInflater.from(context).inflate(R.layout.productsview, parent, false)

        productname = convertView.findViewById(R.id.productname)
        price = convertView.findViewById(R.id.price)
        date = convertView.findViewById(R.id.date)
        img_update = convertView.findViewById(R.id.img_update)
        img_delete = convertView.findViewById(R.id.img_delete)

        var product = ProductDetails()
        product = arrayList[position]

        productname.text = product.Productname
        price.text = "Rs. "+product.Productprice
        date.text = "Date: "+product.Date

        img_delete?.setOnClickListener {

            deleteData(product.id)
        }

        img_update?.setOnClickListener {

            val builder = AlertDialog.Builder(context).create()
            val view = layoutInflater.inflate(R.layout.addnewproduct,null)
            builder.setView(view)

            val btn_save = view.findViewById <TextView> (R.id.btn_save)
            val edt_productname = view.findViewById <EditText> (R.id.edt_productname)
            val edt_price = view.findViewById <EditText> (R.id.edt_price)
            val edt_date = view.findViewById <EditText> (R.id.edt_date)

            edt_productname.setText(product.Productname)
            edt_price.setText(product.Productprice)
            edt_date.setText(product.Date)

            btn_save?.setOnClickListener {

                sqliteDB = context.openOrCreateDatabase("baseballregister.db", Context.MODE_APPEND,null)
                sqliteDB?.execSQL(" update user_baseballregister set productname = '"+edt_productname.text.toString()+"' ,  productprice = '"+edt_price.text.toString()+"',date = '"+edt_date.text.toString()+"' where id ="+product.id+" ")
                notifyDataSetChanged()
                builder.dismiss()
                v.Moving("Moving")
            }
            builder.setCanceledOnTouchOutside(false)
            builder.show()
        }
        return convertView
    }
    @SuppressLint("WrongConstant")
    fun deleteData(id:Int){

        sqliteDB = context.openOrCreateDatabase("baseballregister.db", Context.MODE_APPEND,null)

        val builder = AlertDialog.Builder(context)
        builder.setTitle("Delete Product")
        builder.setMessage("Do you want delete this Product?")
        builder.setPositiveButton(android.R.string.yes) { dialog, which ->
            sqliteDB?.execSQL("delete from user_baseballregister where  id="+id+" ")
            notifyDataSetChanged()
            dialog.dismiss()
            v1.Moving1("Moving1")
        }
        builder.setNegativeButton(android.R.string.no) { dialog, which ->
            dialog.dismiss()
        }
        builder.show()
    }
}