package dev.company.jetshop.customer_ui.scan

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import dev.company.jetshop.R
import dev.company.jetshop.cart.CartActivity

class CustomerScanFragment: Fragment() {

    var btn_scan:Button?=null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val views = inflater.inflate(R.layout.fragment_customer_scan, container, false)

        btn_scan = views.findViewById(R.id.btn_scan)

        btn_scan?.setOnClickListener {
            startActivity(Intent(getActivity(),Barcodereader::class.java))
        }
        return views
    }
}