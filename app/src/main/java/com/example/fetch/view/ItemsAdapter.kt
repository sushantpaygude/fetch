package com.example.fetch.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fetch.R
import com.example.fetch.model.*


class GroupAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var itemList : List<ListItem> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if(viewType == 0) {
            val itemView = LayoutInflater.from(parent.context).inflate(R.layout.layout_group, parent, false)
            GroupViewHolder(itemView)
        } else {
            val itemView = LayoutInflater.from(parent.context).inflate(R.layout.layout_item, parent, false)
            ItemViewHolder(itemView)
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewType = itemList[position].getType()
        if(viewType == ItemType.TYPE_HEADER.getValue()) {
            val groupViewHolder = holder as GroupViewHolder
            groupViewHolder.bind(itemList[position] as HeaderItem)
        }
        if(viewType == ItemType.TYPE_CONTENT.getValue()) {
            val itemViewHolder = holder as ItemViewHolder
            itemViewHolder.bind(itemList[position] as ContentItem)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return itemList[position].getType()
    }

    class GroupViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val itemGroup : TextView = itemView.findViewById(R.id.item_group)

        fun bind(item : HeaderItem) {
            this.itemGroup.text = item.listId.toString()

        }
    }

    class ItemViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        private val itemName: TextView = itemView.findViewById(R.id.item_name)
        private val itemId: TextView = itemView.findViewById(R.id.item_id)

        fun bind(item: ContentItem) {
            this.itemId.text = item.id
            this.itemName.text = item.name
        }
    }
}