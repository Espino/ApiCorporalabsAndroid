package com.phonedeveloper.apicorporalabs.ui.adapter.callback

import androidx.paging.AsyncPagingDataDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListUpdateCallback
import com.phonedeveloper.domain.model.Article
import kotlinx.coroutines.Dispatchers

object ItemCallback {

    val differ: AsyncPagingDataDiffer<Article> by lazy {
        AsyncPagingDataDiffer(
            diffCallback = DIFF_UTIL,
            updateCallback = updateCallback,
            mainDispatcher = Dispatchers.Unconfined,
            workerDispatcher = Dispatchers.Unconfined,
        )
    }

    val DIFF_UTIL = object : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }

    val updateCallback = object : ListUpdateCallback {
        override fun onInserted(position: Int, count: Int) {
            println("onInserted: ${differ.snapshot()}")
        }

        override fun onRemoved(position: Int, count: Int) {
            println("onRemoved: ${differ.snapshot()}")
        }

        override fun onMoved(fromPosition: Int, toPosition: Int) {
            println("onMoved: ${differ.snapshot()}")
        }

        override fun onChanged(position: Int, count: Int, payload: Any?) {
            println("onChanged: ${differ.snapshot()}")
        }
    }

}