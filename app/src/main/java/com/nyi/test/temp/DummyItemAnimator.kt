package com.nyi.test.temp

import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SimpleItemAnimator

class DummyItemAnimator : SimpleItemAnimator() {

    override fun animateAdd(holder: RecyclerView.ViewHolder?): Boolean {
        TODO("Not yet implemented")
    }
    override fun animateChange(
        oldHolder: RecyclerView.ViewHolder?,
        newHolder: RecyclerView.ViewHolder?,
        fromLeft: Int,
        fromTop: Int,
        toLeft: Int,
        toTop: Int
    ): Boolean {
        TODO("Not yet implemented")
    }
    override fun animateMove(
        holder: RecyclerView.ViewHolder?,
        fromX: Int,
        fromY: Int,
        toX: Int,
        toY: Int
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun animateRemove(holder: RecyclerView.ViewHolder?): Boolean {
        TODO("Not yet implemented")
    }

    override fun endAnimation(item: RecyclerView.ViewHolder) {
        TODO("Not yet implemented")
    }

    override fun endAnimations() {
        TODO("Not yet implemented")
    }

    override fun isRunning(): Boolean {
        TODO("Not yet implemented")
    }

    override fun runPendingAnimations() {
        TODO("Not yet implemented")
    }
}