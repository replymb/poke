package com.replymb.poke.view.adapter

import android.graphics.Rect
import android.view.View
import androidx.annotation.DimenRes
import androidx.recyclerview.widget.RecyclerView

class HorizontalSpaceItemDecorator(@DimenRes val horizontalSpace: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        if(parent.getChildAdapterPosition(view) > 0) {
            outRect.left = view.resources.getDimensionPixelSize(horizontalSpace)
        }
        if(parent.getChildAdapterPosition(view) < (parent.childCount - 1)) {
            outRect.right = view.resources.getDimensionPixelSize(horizontalSpace)
        }
    }
}