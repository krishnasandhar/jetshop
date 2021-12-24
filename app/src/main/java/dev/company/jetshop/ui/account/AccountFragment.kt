package dev.company.jetshop.ui.account

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import dev.company.jetshop.loginregister.AccountDetails
import dev.company.jetshop.cart.CartActivity
import dev.company.jetshop.loginregister.LoginActivity
import dev.company.jetshop.R

class AccountFragment: Fragment() {

    var login:TextView?=null
    var myprofile:TextView?=null
    var username:TextView?=null
    var cart: ImageView?=null
    var orders: TextView?=null

    override fun onCreateView (inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        val views = inflater.inflate (R.layout.fragment_account, container, false)

        login = views.findViewById(R.id.login)
        username = views.findViewById(R.id.username)
        myprofile = views.findViewById(R.id.myprofile)
        cart = views.findViewById(R.id.cart)
        orders = views.findViewById(R.id.orders)

        login?.setOnClickListener {
            val intent = Intent (getActivity(), LoginActivity::class.java)
            getActivity()?.startActivity(intent)
        }
        myprofile?.setOnClickListener {
            val intent = Intent(getActivity(), AccountDetails::class.java)
            getActivity()?.startActivity(intent)
        }
        orders?.setOnClickListener {
            val intent = Intent(getActivity(), CartActivity::class.java)
            getActivity()?.startActivity(intent)
        }
        return views
    }
}