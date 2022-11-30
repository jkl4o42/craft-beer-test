package jkl.craftbeertest.beer.presentation

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.SCROLL_STATE_DRAGGING

abstract class RecyclerViewPaginator(recyclerView: RecyclerView) : RecyclerView.OnScrollListener() {

    private var currentPage: Int = 1

    private var endWithAuto = false

    private val layoutManager: RecyclerView.LayoutManager?

    private val startSize: Int
        get() = ++currentPage

    init {
        recyclerView.addOnScrollListener(this)
        this.layoutManager = recyclerView.layoutManager
    }

    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
        super.onScrollStateChanged(recyclerView, newState)
        if (newState == SCROLL_STATE_DRAGGING) {
            val visibleItemCount = layoutManager!!.childCount
            val totalItemCount = layoutManager.itemCount

            var firstVisibleItemPosition = 0
            if (layoutManager is LinearLayoutManager) {
                firstVisibleItemPosition = layoutManager.findLastVisibleItemPosition()

            } else if (layoutManager is GridLayoutManager) {
                firstVisibleItemPosition = layoutManager.findLastVisibleItemPosition()
            }

            if (visibleItemCount + firstVisibleItemPosition >= totalItemCount) {
                if (!endWithAuto) {
                    endWithAuto = true
                    loadMore(startSize)
                }
            } else {
                endWithAuto = false
            }
        }
    }

    fun reset() {
        currentPage = 0
    }

    abstract fun loadMore(page: Int)
}