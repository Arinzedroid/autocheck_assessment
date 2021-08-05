package com.arinzedroid.autochekassessment.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.arinzedroid.autochekassessment.databinding.ItemsStatusLayoutBinding
import com.arinzedroid.autochekassessment.model.PopularCarEntity

class StatusAdapter: PagedListAdapter<PopularCarEntity, StatusAdapter.ItemHolder>(ITEM_DIFF) {


    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.bindItem(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemsStatusLayoutBinding.inflate(inflater)
        return ItemHolder(binding)
    }

    inner class ItemHolder(private val bindings: ItemsStatusLayoutBinding): RecyclerView.ViewHolder(bindings.root){

        fun bindItem(item: PopularCarEntity?){
            bindings.items = item
            bindings.executePendingBindings()
        }
    }


    companion object {
        val ITEM_DIFF = object : DiffUtil.ItemCallback<PopularCarEntity>() {
            override fun areContentsTheSame(oldItem: PopularCarEntity, newItem: PopularCarEntity): Boolean {
                return oldItem == newItem
            }

            override fun areItemsTheSame(oldItem: PopularCarEntity, newItem: PopularCarEntity): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}


