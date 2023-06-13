package com.braula.finnapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.braula.finnapp.R
import com.braula.finnapp.databinding.AdItemBinding
import com.braula.finnapp.domain.model.Ad
import com.bumptech.glide.Glide

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

        const val BASE_IMAGE_URL = "https://images.finncdn.no/dynamic/480x360c/"
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdViewHolder {
        val binding = AdItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AdViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AdViewHolder, position: Int) {
        with(holder.binding) {
            val view = this.root
            with(getItem(position)) {
                Glide
                    .with(view)
                    .load("${BASE_IMAGE_URL}${this.imageSuffix}")
                    .placeholder(R.drawable.ic_placeholder)
                    .into(image)

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

