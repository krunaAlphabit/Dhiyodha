package com.alphabit.dhiyodha.Utils

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class GridSpacingItemDecoration(
    private val spanCount: Int,
    private val spacing: Int,
    private val includeEdge: Boolean,
    private val headerNum: Int
) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view) - headerNum // item position
        if (position >= 0) {
            val column = position % spanCount // item column
            if (includeEdge) {

                if(position % 2 == 0) {
                    /// right side
                    outRect.left =
                        spacing - column * spacing / spanCount // spacing - column * ((1f / spanCount) * spacing)
                    outRect.right = 0
                } else {
                    // left side
                    outRect.right =
                        (column + 1) * spacing / spanCount // (column + 1) * ((1f / spanCount) * spacing)
                    outRect.left =0

                }
                   if (position < spanCount) { // top edge
                    outRect.top = spacing
                }
                outRect.bottom = spacing // item bottom
            } else {
                if(position % 2 == 0){
                    /// right side
                    outRect.left = column * spacing / spanCount // column * ((1f / spanCount) * spacing)
                    outRect.right = 0
                } else {
                    // left side
                    outRect.right =
                        spacing - (column + 1) * spacing / spanCount // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                    outRect.left =0
                }
                  if (position >= spanCount) {
                    outRect.top = spacing // item top
                }
            }
        } else {
            outRect.left = 0
            outRect.right = 0
            outRect.top = 0
            outRect.bottom = 0
        }
    }
}