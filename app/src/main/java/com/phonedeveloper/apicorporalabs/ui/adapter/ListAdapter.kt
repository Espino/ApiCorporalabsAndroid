package com.phonedeveloper.apicorporalabs.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.AsyncPagingDataDiffer
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.phonedeveloper.apicorporalabs.R
import com.phonedeveloper.apicorporalabs.databinding.ItemNewsArticleBinding
import com.phonedeveloper.apicorporalabs.ui.adapter.callback.ItemCallback.DIFF_UTIL
import com.phonedeveloper.apicorporalabs.ui.adapter.callback.ItemCallback.updateCallback
import com.phonedeveloper.domain.model.Article

class ListAdapter :
    PagingDataAdapter<Article, ListAdapter.ItemViewHolder>(diffCallback = DIFF_UTIL) {

//    val differ = AsyncListDiffer(this, DIFF_UTIL)
    val differPagerAdapter = AsyncPagingDataDiffer(diffCallback = DIFF_UTIL, updateCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = ItemNewsArticleBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it, position)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return differPagerAdapter.itemCount
    }
//
//    override fun getItemCount(): Int {
////        return differ.currentList.size
//    }

    inner class ItemViewHolder(
        val binding: ItemNewsArticleBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(article: Article, position: Int) {
            binding.apply {
//            with(binding) {
                if (position % 2 == 1) {
                    rootCardView.rotationY = 180f
                    imgView.rotationY = 180f
                    tvName.rotationY = 180f
                    tvDescription.rotationY = 180f
                } else {
                    rootCardView.rotationY = 0f
                    imgView.rotationY = 0f
                    tvName.rotationY = 0f
                    tvDescription.rotationY = 0f
                }

                tvName.text = article.title
                tvDescription.text = article.abstract


                if(!article.media.isNullOrEmpty()){
                    article.media!![0].let {
                        if(!it.mediaMetadata.isNullOrEmpty()){
                            it.mediaMetadata?.get(1)?.url?.let {
                                Glide.with(itemView.context)
                                    .load(it)
                                    .apply(
                                        RequestOptions()
                                            .placeholder(R.drawable.loading_animation)
                                            .error(R.drawable.ic_broken_image)
                                    )
                                    .into(imgView)
//                                    .into(binding.articleImage)

                            }
                        }
                    }
                }
                rootCardView.setOnClickListener {
                    onItemClickListener?.let {
                        it(article)
                    }
                }
            }
        }
    }

    private var onItemClickListener: ((Article) -> Unit)? = null

    fun setOnItemClickListener(listener: (Article) -> Unit) {
        onItemClickListener = listener
    }

}









