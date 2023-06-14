package com.braula.finnapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.braula.finnapp.R
import com.braula.finnapp.databinding.AdItemBinding
import com.braula.finnapp.domain.model.Ad
import com.braula.finnapp.utils.visibility
import com.bumptech.glide.Glide

class AdAdapter(private val favoriteCallback: FavoriteCallback): ListAdapter<Ad, AdAdapter.AdViewHolder>(DIFF_UTIL_CALLBACK) {

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

    private val favoriteIds = arrayListOf<String>()
    private val ads = arrayListOf<Ad>()

    fun submitAds(ads: List<Ad>) {
        this.ads.apply {
            clear()
            addAll(ads)
            submitList(ads)
        }
    }

    fun submitFavoriteIds(favoriteIds: List<String>) {
        this.favoriteIds.apply {
            clear()
            addAll(favoriteIds)
        }
    }

    interface FavoriteCallback {
        fun onFavoriteAdded(ad: Ad)
        fun onFavoriteRemoved(id: String)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdViewHolder {
        val binding = AdItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AdViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AdViewHolder, position: Int) {
        with(holder.binding) {
            val view = this.root
            with(getItem(position)) {
                val isFavorite = favoriteIds.contains(this.id)
                Glide
                    .with(view)
                    .load("${BASE_IMAGE_URL}${this.imageSuffix}")
                    .placeholder(R.drawable.ic_placeholder)
                    .into(image)

                titleText.text = title ?: "<No title>"
                priceText.text = price.toString()
                locationText.text = location ?: "<No location>"

                favoriteCheckBox.isChecked = isFavorite
                favoriteCheckBox.setOnCheckedChangeListener { button, state ->
                    if (button.isShown) {
                        this.isFavorite = state
                        if (state) {
                            favoriteIds.add(this.id)
                            favoriteCallback.onFavoriteAdded(this)
                        } else {
                            favoriteIds.remove(this.id)
                            favoriteCallback.onFavoriteRemoved(this.id)
                        }
                    }
                }
            }
        }
    }

    fun showOnlyFavorites() {
        val filtered = ads.filter { favoriteIds.contains(it.id) }
        submitList(filtered)
    }

    fun showAll() {
        submitList(ads)
    }

    inner class AdViewHolder(
        val binding: AdItemBinding
    ): RecyclerView.ViewHolder(binding.root)
}

