package com.deved.myepxinperu.ui.main

import androidx.appcompat.app.AppCompatActivity
import com.deved.myepxinperu.R
import com.deved.myepxinperu.ui.home.HomeFragment
import com.deved.myepxinperu.ui.share.ShareFragment


object OrdersCommandProcessor {
    private var queue = HashMap<Int, OrderCommand>()

    private lateinit var activity: AppCompatActivity

    fun init(activity: AppCompatActivity) {
        this.activity = activity
        queue[R.id.itemHome] = HomeCommand(HomeFragment.newInstance())
        queue[R.id.itemFavorites] = HomeCommand(ShareFragment.newInstance())
    }

    fun invoke(key: Int) = apply {
        if (queue.containsKey(key)) {
            queue[key]?.execute {
                activity.supportFragmentManager.beginTransaction().apply {
                    replace(R.id.frameLayoutMain, it)
                    addToBackStack(null)
                }.commit()
            }
        }
    }

    fun clear() {
        queue.clear()
    }
}



