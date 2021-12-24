package dev.company.jetshop.ui.scan

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import dev.company.jetshop.R

class ScanFragment: Fragment() {

    var btn_scan:Button?=null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val views = inflater.inflate(R.layout.fragment_scan, container, false)

//        btn_scan = views.findViewById(R.id.btn_scan)
//
//        btn_scan?.setOnClickListener {
//
//            val scanner = IntentIntegrator(getActivity())
//
//            scanner.initiateScan()
//        }

        return views
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

//        if (resultCode == Activity.RESULT_OK){

//            val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)

//            if(result!=null) {
//
//                if (result.contents==null) {
//                    Toast.makeText(getActivity(), "Cancelled", Toast.LENGTH_SHORT).show()
//                } else {
//                    Toast.makeText(getActivity(), "Scanned:" + result.contents, Toast.LENGTH_SHORT).show()
//                }
//            } else {
//                super.onActivityResult(requestCode, resultCode, data)
//            }
        }
//    }
}