package kr.loner.fx.ui.util

import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SimpleItemAnimator

fun RecyclerView.animationRemove(){
    val animator: RecyclerView.ItemAnimator = this.itemAnimator!!
    if (animator is SimpleItemAnimator)
        animator.supportsChangeAnimations = false
}
