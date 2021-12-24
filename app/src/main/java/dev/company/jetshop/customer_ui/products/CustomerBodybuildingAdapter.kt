package dev.company.jetshop.customer_ui.products

import android.annotation.SuppressLint
import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import dev.company.jetshop.products.ProductDetails
import dev.company.jetshop.R

class CustomerBodybuildingAdapter (
    val context: Activity,
    val arrayList: ArrayList<ProductDetails>,
    val layoutInflater: LayoutInflater,
) : BaseAdapter() {

    override fun getCount(): Int {
        return arrayList.size
    }
    override fun getItem(position: Int): Any {
        return position
    }
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }
    @SuppressLint("SetTextI18n", "ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        var convertView = convertView

        convertView = LayoutInflater.from(context).inflate(R.layout.customersview, parent, false)

        val productname: TextView = convertView.findViewById(R.id.productname)
        val price: TextView = convertView.findViewById(R.id.price)
        val date: TextView = convertView.findViewById(R.id.date)
        val img_cart: ImageView = convertView.findViewById(R.id.img_cart)

        var product = ProductDetails()
        product = arrayList[position]

        productname.text = product.Productname
        price.text = "Rs. "+product.Productprice
        date.text = "Date: "+product.Date

        img_cart.setOnClickListener {

        }
        return convertView
    }
}