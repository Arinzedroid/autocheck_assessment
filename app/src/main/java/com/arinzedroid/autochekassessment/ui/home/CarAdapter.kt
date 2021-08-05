package com.arinzedroid.autochekassessment.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.arinzedroid.autochekassessment.databinding.ItemsHomeLayoutBinding
import com.arinzedroid.autochekassessment.model.SearchCarEntity
import com.arinzedroid.autochekassessment.ui.ItemClickListener

class CarAdapter(private val listener: ItemClickListener<SearchCarEntity>)
    :PagedListAdapter<SearchCarEntity, CarAdapter.ItemViewHolder>(ITEM_DIFF) {


    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bindItem(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val layoutInflator = LayoutInflater.from(parent.context)
        val binding = ItemsHomeLayoutBinding.inflate(layoutInflator)
        return ItemViewHolder(binding)
    }


    inner class ItemViewHolder(private val binding: ItemsHomeLayoutBinding): RecyclerView.ViewHolder(binding.root){

        fun bindItem(item: SearchCarEntity?){
            binding.item = item
            binding.listener = listener
            binding.executePendingBindings()
        }
    }

    companion object {
        val ITEM_DIFF = object : DiffUtil.ItemCallback<SearchCarEntity>() {
            override fun areContentsTheSame(oldItem: SearchCarEntity, newItem: SearchCarEntity): Boolean {
                return oldItem == newItem
            }

            override fun areItemsTheSame(oldItem: SearchCarEntity, newItem: SearchCarEntity): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}