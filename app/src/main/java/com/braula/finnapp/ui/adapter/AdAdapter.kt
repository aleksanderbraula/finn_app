package com.braula.finnapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.braula.finnapp.data.model.Ad
import com.braula.finnapp.databinding.AdItemBinding

class AdAdapter: ListAdapter<Ad, AdAdapter.AdViewHolder>(DIFF_UTIL_CALLBACK) {

    companion object {
        val DIFF_UTIL_CALLBACK = object : DiffUtil.ItemCallback<Ad>() {
            override fun areItemsTheSame(
                oldItem: Ad,
                newItem: Ad
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: Ad,
                newItem: Ad
            ): Boolean {
                return oldItem.areContentsTheSame() == newItem.areContentsTheSame()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdViewHolder {
        val binding = AdItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AdViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AdViewHolder, position: Int) {
        with(holder.binding) {
            with(getItem(position)) {
                titleText.text = title ?: "<No title>"
                priceText.text = (price ?: 0).toString()
                locationText.text = location ?: "<No location>"

                favoriteCheckBox.isChecked = isFavorite
            }
        }
    }

    inner class AdViewHolder(
        val binding: AdItemBinding
    ): RecyclerView.ViewHolder(binding.root)
}

