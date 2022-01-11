package com.example.desafiomobile.common

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.pagination(
    layoutManager: LinearLayoutManager,
    listener: PaginationListener
) {
    addOnScrollListener(object : RecyclerView.OnScrollListener() {

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            val visibleItemCount = layoutManager.childCount
            val totalItemCount = layoutManager.itemCount
            val pastVisibleItems = layoutManager.findFirstVisibleItemPosition()
            val totalItemsLoaded = visibleItemCount + pastVisibleItems

            if (listener.isPaginationLocked())
                return

            if (totalItemsLoaded >= totalItemCount) {
                listener.loadMore()
            }
        }
    })
}

interface PaginationListener {

    /**
     * this method is called to know if the view is waiting more items or does not have more items
     */
    fun isPaginationLocked(): Boolean

    /**
     * This method is called only if is not last page and is not loading
     */
    fun loadMore()
}