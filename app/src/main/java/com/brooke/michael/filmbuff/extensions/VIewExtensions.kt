package com.brooke.michael.filmbuff.extensions

import android.content.Context
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentTransaction
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import com.squareup.picasso.Picasso

fun toast(view: View, message: Int, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(view.context, message, duration).show()
}

fun ViewGroup.inflateLayout(resID: Int): View {
    return LayoutInflater.from(context).inflate(resID, this, false)
}

fun ImageView.loadImage(url: String) {
    Picasso.with(context).load(url).into(this)
}

fun RecyclerView.setupDefaultConfig(context: Context){
    layoutManager = LinearLayoutManager(context)
    setHasFixedSize(true)
}

inline fun FragmentActivity.changeFragment(func: FragmentTransaction.() -> FragmentTransaction){
    supportFragmentManager
            .beginTransaction()
            .func()
            .commit()
}

inline fun SwipeRefreshLayout.setupSwipeRefresh(crossinline onSwipe: () -> Unit) {
    setOnRefreshListener { onSwipe() }
    measure(View.MEASURED_SIZE_MASK, View.MEASURED_HEIGHT_STATE_SHIFT)
    isRefreshing = true
}