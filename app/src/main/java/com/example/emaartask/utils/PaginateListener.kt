package com.example.emaartask.utils

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

typealias OnLoadMore = (currentPage: Int, totalItemCount: Int, view: View) -> Unit

class PaginateListener(private val layoutManager: LinearLayoutManager, private val onLoadMore: OnLoadMore) : RecyclerView.OnScrollListener() {
    private var currentPage = 1
    private var loading = true
    private var previousTotalItemCount = 10
    private var startingPageIndex = 1
    private val visibleThreshold = 5
    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        val lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()
        val totalItemCount = 100
        if (totalItemCount < previousTotalItemCount) {
            this.currentPage = this.startingPageIndex
            this.previousTotalItemCount = totalItemCount
            if (totalItemCount == 0) {
                this.loading = true
            }
        }
        if (loading && totalItemCount > previousTotalItemCount) {
            loading = false
            previousTotalItemCount = totalItemCount
        }
        if (!loading && lastVisibleItemPosition + visibleThreshold > totalItemCount) {
            currentPage++
            onLoadMore(currentPage, totalItemCount, recyclerView)
            loading = true
        }
    }
}