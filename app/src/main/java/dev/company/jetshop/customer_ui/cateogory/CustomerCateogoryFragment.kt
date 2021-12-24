package dev.company.jetshop.customer_ui.cateogory

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.SearchView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dev.company.jetshop.R
import dev.company.jetshop.cart.CartActivity
import dev.company.jetshop.customer_ui.products.*

class CustomerCateogoryFragment: Fragment(), CustomerCateogoryAdapter.Clicklistener {

    lateinit var searchView: SearchView
    private var recyclerView: RecyclerView?=null
    private var gridLayoutManager: GridLayoutManager?=null
    private var itemsAdapter: CustomerCateogoryAdapter?=null
    var addtocart: ImageView?=null

    val image = arrayOf (
        CustomerCaetogoryModal(R.drawable.aquagym,"aquagym"), CustomerCaetogoryModal(R.drawable.archery,"archery"),
        CustomerCaetogoryModal(R.drawable.baseball,"baseball"), CustomerCaetogoryModal(R.drawable.badminton,"badminton"),
        CustomerCaetogoryModal(R.drawable.basketball,"baseketball"), CustomerCaetogoryModal(R.drawable.billards,"billards"),
        CustomerCaetogoryModal(R.drawable.boxing,"boxing"), CustomerCaetogoryModal(R.drawable.gymspace,"body building"),
        CustomerCaetogoryModal(R.drawable.carrom,"carrom"), CustomerCaetogoryModal(R.drawable.cricket,"cricket"),
        CustomerCaetogoryModal(R.drawable.cycling,"cycling"), CustomerCaetogoryModal(R.drawable.dance,"dance"),
        CustomerCaetogoryModal(R.drawable.darts,"darts"), CustomerCaetogoryModal(R.drawable.fishing,"fishing"),
        CustomerCaetogoryModal(R.drawable.football,"football"), CustomerCaetogoryModal(R.drawable.golf,"golf"),
        CustomerCaetogoryModal(R.drawable.gymnastics,"gymnastics"), CustomerCaetogoryModal(R.drawable.hockey,"hockey"),
        CustomerCaetogoryModal(R.drawable.landkites,"kites"), CustomerCaetogoryModal(R.drawable.rubyfootball,"rugbyfootbal"),
        CustomerCaetogoryModal(R.drawable.running,"running"), CustomerCaetogoryModal(R.drawable.skating,"skating"),
        CustomerCaetogoryModal(R.drawable.tennis,"tennis"), CustomerCaetogoryModal(R.drawable.volleyball,"volleyball"))

    override fun onCreateView (
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val views =  inflater.inflate(R.layout.fragment_customer_cateogory, container, false)

        val itemlist = ArrayList<CustomerCaetogoryModal>()

        for (items in image) {

            itemlist.add(items)
        }
        recyclerView = views.findViewById(R.id.recycleView) as RecyclerView
        gridLayoutManager = GridLayoutManager(context, 2, LinearLayoutManager.VERTICAL, false)
        recyclerView?.layoutManager = gridLayoutManager
        itemsAdapter = CustomerCateogoryAdapter(this,itemlist)
        itemsAdapter?.setData(itemlist)
        recyclerView?.adapter = itemsAdapter
        recyclerView?.setHasFixedSize(true)

        searchView = views.findViewById(R.id.searchview)

        searchView.setOnQueryTextListener(object:androidx.appcompat.widget.SearchView.OnQueryTextListener,SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(filterString: String?): Boolean {

                itemsAdapter!!.filter.filter(filterString)
                return true
            }
            override fun onQueryTextChange(filterString: String?): Boolean {

                itemsAdapter!!.filter.filter(filterString)
                return true
            }
        })
        addtocart = views.findViewById(R.id.addtocart)
        addtocart?.setOnClickListener {
            startActivity(Intent(getActivity(), CartActivity::class.java))
        }

        return views
    }
    override fun Clickeditem(itemsModal: CustomerCaetogoryModal) {

        Log.e("TAG",itemsModal.name!!)

        when (itemsModal.name) {
            "aquagym" ->
                startActivity(Intent(getActivity(), CustomerAquagymActivity::class.java))
            "archery" ->
                startActivity(Intent(getActivity(), CustomerArcheryActivity::class.java))
            "baseball" ->
                startActivity(Intent(getActivity(), CustomerBaseballActivity::class.java))
            "badminton" ->
                startActivity(Intent(getActivity(), CustomerBadmintonActivity::class.java))
            "baseketball" ->
                startActivity(Intent(getActivity(), CustomerBaseketballActivity::class.java))
            "billards" ->
                startActivity(Intent(getActivity(), CustomerBillardsActivity::class.java))
            "boxing" ->
                startActivity(Intent(getActivity(), CustomerBoxingActivity::class.java))
            "body building" ->
                startActivity(Intent(getActivity(), CustomerBodyBuildingActivity::class.java))
            "carrom" ->
                startActivity(Intent(getActivity(), CustomerCarromActivity::class.java))
            "cricket" ->
                startActivity(Intent(getActivity(), CustomerCricketActivity::class.java))
            "cycling" ->
                startActivity(Intent(getActivity(), CustomerCyclingActivity::class.java))
            "dance" ->
                startActivity(Intent(getActivity(), CustomerDanceActivity::class.java))
            "darts" ->
                startActivity(Intent(getActivity(), CustomerDartsActivity::class.java))
            "fishing" ->
                startActivity(Intent(getActivity(), CustomerFishingActivity::class.java))
            "football" ->
                startActivity(Intent(getActivity(), CustomerFootballActivity::class.java))
            "golf" ->
                startActivity(Intent(getActivity(), CustomerGolfActivity::class.java))
            "gymnastics" ->
                startActivity(Intent(getActivity(), CustomerGymnasticsActivity::class.java))
            "hockey" ->
                startActivity(Intent(getActivity(), CustomerHockeyActivity::class.java))
            "kites" ->
                startActivity(Intent(getActivity(), CustomerKitesActivity::class.java))
            "rugbyfootbal" ->
                startActivity(Intent(getActivity(), CustomerRugbyfootballActivity::class.java))
            "running" ->
                startActivity(Intent(getActivity(), CustomerRunningActivity::class.java))
            "skating" ->
                startActivity(Intent(getActivity(), CustomerSkatingActivity::class.java))
            "tennis" ->
                startActivity(Intent(getActivity(), CustomerTennisActivity::class.java))
            "volleyball" ->
                startActivity(Intent(getActivity(), CustomerVolleyballActivity::class.java))
            else -> {

                Toast.makeText(getActivity(), "No Action", Toast.LENGTH_SHORT).show()
            }
        }
    }
}