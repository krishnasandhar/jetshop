package dev.company.jetshop.customer_ui.cateogory

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import dev.company.jetshop.R

class CustomerCateogoryAdapter(var clickListener: Clicklistener, var ItemList: ArrayList<CustomerCaetogoryModal>)
    :RecyclerView.Adapter<CustomerCateogoryAdapter.ItemsAdapterVH>(),Filterable {

    var ItemListFilter = ArrayList<CustomerCaetogoryModal>()

    fun setData(ItemList: ArrayList<CustomerCaetogoryModal>) {
        this.ItemList = ItemList
        this.ItemListFilter = ItemList
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder (parent: ViewGroup, viewType: Int): ItemsAdapterVH {
        return ItemsAdapterVH (LayoutInflater.from(parent.context).inflate(R.layout.cutomercaetogorylist, parent, false))

    }
    override fun onBindViewHolder (holder: ItemsAdapterVH, position: Int) {

        val itemsModal = ItemList [position]
        holder.name.text  = itemsModal.name
        holder.image.setImageResource(itemsModal.icon!!)

        holder.itemView.setOnClickListener {
            clickListener.Clickeditem(itemsModal)
        }
    }
    override fun getItemCount(): Int {
        return ItemList.size
    }
    class ItemsAdapterVH (itemView: View)  : RecyclerView.ViewHolder(itemView)  {

        var name: TextView = itemView.findViewById (R.id.textview)
        var image: ImageView = itemView.findViewById (R.id.imageview)
    }
    interface Clicklistener {
        fun Clickeditem (itemsModal: CustomerCaetogoryModal)
    }
    override fun getFilter(): Filter {
        return object: Filter(){
            override fun performFiltering(charsequence: CharSequence?): FilterResults {

                val filterResults = FilterResults()
                if (charsequence == null || charsequence.length<0) {
                    filterResults.count = ItemListFilter.size
                    filterResults.values = ItemListFilter

                } else {

                    val searchchar = charsequence.toString().toLowerCase()

                    val itemModal = ArrayList<CustomerCaetogoryModal>()

                    for (item in ItemListFilter) {
                        if (item.name!!.contains(searchchar)) {
                            itemModal.add(item)

                        }
                    }
                    filterResults.count = itemModal.size
                    filterResults.values = itemModal
                }

                return filterResults
            }
            override fun publishResults(constraint: CharSequence?, filterResults: FilterResults?) {

                ItemList = filterResults!!.values as ArrayList<CustomerCaetogoryModal>
                notifyDataSetChanged()
            }
        }
    }
}