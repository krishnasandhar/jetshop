package dev.company.jetshop.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.ViewModelProvider
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import dev.company.jetshop.cart.CartActivity
import dev.company.jetshop.R

class HomeFragment: Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    var cart: ImageView?=null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        val views = inflater.inflate(R.layout.fragment_home, container, false)

        cart = views.findViewById (R.id.addtocart)

        cart?.setOnClickListener {
            val intent = Intent (getActivity(),CartActivity::class.java)
            getActivity()?.startActivity(intent)
        }
        val imageslider = views.findViewById<ImageSlider>(R.id.imageslider)
        val imagelist = ArrayList<SlideModel>()

        imagelist.add(SlideModel(R.drawable.placeholder, ScaleTypes.FIT))
        imagelist.add(SlideModel(R.drawable.placeholder, ScaleTypes.FIT))
        imagelist.add(SlideModel(R.drawable.placeholder, ScaleTypes.FIT))
        imagelist.add(SlideModel(R.drawable.placeholder, ScaleTypes.FIT))
        imagelist.add(SlideModel(R.drawable.placeholder, ScaleTypes.FIT))
        imageslider.setImageList(imagelist, ScaleTypes.FIT)

        val imageslider2 = views.findViewById<ImageSlider>(R.id.imageslider2)
        val imagelist2 = ArrayList<SlideModel>()
        imagelist2.add(SlideModel(R.drawable.placeholder, ScaleTypes.FIT))
        imagelist2.add(SlideModel(R.drawable.placeholder, ScaleTypes.FIT))
        imagelist2.add(SlideModel(R.drawable.placeholder, ScaleTypes.FIT))
        imageslider2.setImageList(imagelist2, ScaleTypes.FIT)

        return views
    }
}