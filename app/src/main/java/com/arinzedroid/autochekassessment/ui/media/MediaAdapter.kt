package com.arinzedroid.autochekassessment.ui.media

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.arinzedroid.autochekassessment.databinding.ItemsMediaLayoutBinding
import com.arinzedroid.autochekassessment.model.MediaEntity
import com.arinzedroid.autochekassessment.ui.ItemClickListener

class MediaAdapter (
    val listener: ItemClickListener<MediaEntity>)
    :PagedListAdapter<MediaEntity, MediaAdapter.ItemViewHolder>(ITEM_DIFF) {


    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bindItem(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val layoutInflator = LayoutInflater.from(parent.context)
        val binding = ItemsMediaLayoutBinding.inflate(layoutInflator)
        return ItemViewHolder(binding)
    }


    inner class ItemViewHolder(private val binding: ItemsMediaLayoutBinding): RecyclerView.ViewHolder(binding.root){

        fun bindItem(item: MediaEntity?){
            binding.item = item
            binding.listener = listener
            binding.executePendingBindings()
        }
    }

    companion object {
        val ITEM_DIFF = object : DiffUtil.ItemCallback<MediaEntity>() {
            override fun areContentsTheSame(oldItem: MediaEntity, newItem: MediaEntity): Boolean {
                return  oldItem == newItem
            }

            override fun areItemsTheSame(oldItem: MediaEntity, newItem: MediaEntity): Boolean {
                return  oldItem == newItem
            }
        }
    }
}