package dev.company.jetshop.ui.cateogory

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dev.company.jetshop.products.AquagymActivity
import dev.company.jetshop.R
import dev.company.jetshop.products.*

class CateogoryFragment:Fragment(), CateogoryAdapter.Clicklistener {

    lateinit var searchView:SearchView
    private var recyclerView:RecyclerView?=null
    private var gridLayoutManager:GridLayoutManager?=null
    private var itemsAdapter:CateogoryAdapter?=null

    val image = arrayOf(CateogoryModal(R.drawable.placeholder,"aquagym"),CateogoryModal(R.drawable.placeholder,"archery"),
        CateogoryModal(R.drawable.placeholder,"baseball"),CateogoryModal(R.drawable.placeholder,"badminton"),
        CateogoryModal(R.drawable.placeholder,"baseketball"),CateogoryModal(R.drawable.placeholder,"billards"),
        CateogoryModal(R.drawable.placeholder,"boxing"),CateogoryModal(R.drawable.placeholder,"body building"),
        CateogoryModal(R.drawable.placeholder,"carrom"),CateogoryModal(R.drawable.placeholder,"cricket"),
        CateogoryModal(R.drawable.placeholder,"cycling"),CateogoryModal(R.drawable.placeholder,"dance"),
        CateogoryModal(R.drawable.placeholder,"darts"),CateogoryModal(R.drawable.placeholder,"fishing"),
        CateogoryModal(R.drawable.placeholder,"football"),CateogoryModal(R.drawable.placeholder,"golf"),
        CateogoryModal(R.drawable.placeholder,"gymnastics"),CateogoryModal(R.drawable.placeholder,"hockey"),
        CateogoryModal(R.drawable.placeholder,"kites"),CateogoryModal(R.drawable.placeholder,"rugbyfootbal"),
        CateogoryModal(R.drawable.placeholder,"running"),CateogoryModal(R.drawable.placeholder,"skating"),
        CateogoryModal(R.drawable.placeholder,"tennis"),CateogoryModal(R.drawable.placeholder,"volleyball"))

    override fun onCreateView (
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val views =  inflater.inflate(R.layout.fragment_cateogory, container, false)

        val itemsModallist = ArrayList<CateogoryModal>()

        for (items in image) {

            itemsModallist.add(items)
        }
        recyclerView = views.findViewById(R.id.recycleView) as RecyclerView
        gridLayoutManager = GridLayoutManager(context, 2, LinearLayoutManager.VERTICAL, false)
        recyclerView?.layoutManager = gridLayoutManager
        itemsAdapter = CateogoryAdapter(this,itemsModallist)
        itemsAdapter?.setData(itemsModallist)
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
        return views
    }
    override fun Clickeditem(itemsModal: CateogoryModal) {

        Log.e("TAG", itemsModal.alphaChar!!)

        when (itemsModal.alphaChar) {

            "aquagym"->
                startActivity(Intent(getActivity(), AquagymActivity::class.java))
            "archery"->
                startActivity(Intent(getActivity(), ArcheryActivity::class.java))
            "baseball"->
                startActivity(Intent(getActivity(), BaseballActivity::class.java))
            "badminton"->
                startActivity(Intent(getActivity(), BadmintonActivity::class.java))
            "baseketball"->
                startActivity(Intent(getActivity(), BaseketBallActivity::class.java))
            "billards"->
                startActivity(Intent(getActivity(), BillardsActivity::class.java))
            "body building"->
                startActivity(Intent(getActivity(), BodyBuildingActivity::class.java))
            "boxing"->
                startActivity(Intent(getActivity(), BoxingActivity::class.java))
            "carrom"->
                startActivity(Intent(getActivity(), CarromActivity::class.java))
            "cricket"->
                startActivity(Intent(getActivity(), CricketActivity::class.java))
            "cycling"->
                startActivity(Intent(getActivity(), CyclingActivity::class.java))
            "dance"->
                startActivity(Intent(getActivity(), DanceActivity::class.java))
            "darts"->
                startActivity(Intent(getActivity(), DartsActivity::class.java))
            "fishing"->
                startActivity(Intent(getActivity(), FishingActivity::class.java))
            "football"->
                startActivity(Intent(getActivity(), FootballActivity::class.java))
            "golf"->
                startActivity(Intent(getActivity(), GolfActivity::class.java))
            "gymnastics"->
                startActivity(Intent(getActivity(), GymnasticsActivity::class.java))
            "hockey"->
                startActivity(Intent(getActivity(), HockeyActivity::class.java))
            "kites"->
                startActivity(Intent(getActivity(), KitesActivity::class.java))
            "rugbyfootbal"->
                startActivity(Intent(getActivity(), RugbyfootballActivity::class.java))
            "running"->
                startActivity(Intent(getActivity(), RunningActivity::class.java))
            "skating"->
                startActivity(Intent(getActivity(), SkatingActivity::class.java))
            "tennis"->
                startActivity(Intent(getActivity(), TennisActivity::class.java))
            "volleyball"->
                startActivity(Intent(getActivity(), VolleyballActivity::class.java))

            else-> {

                Toast.makeText(getActivity(), "No Action", Toast.LENGTH_SHORT).show()

            }
        }
    }
}