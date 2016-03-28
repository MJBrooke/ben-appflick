package com.brooke.michael.filmbuff.extensions

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import com.squareup.picasso.Picasso

fun ViewGroup.inflateLayout(resID: Int): View {
    return LayoutInflater.from(context).inflate(resID, this, false)
}

fun ImageView.loadImage(url: String){
    Picasso.with(context).load(url).into(this)
}

fun Fragment.toast(message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
}

fun FragmentActivity.changeFragment(fragmentContainer: Int, fragmentToChangeTo: Fragment){
    supportFragmentManager.beginTransaction().add(fragmentContainer, fragmentToChangeTo).commit()
}

fun RecyclerView.setupDefaultConfig(context: Context){
    layoutManager = LinearLayoutManager(context)
    setHasFixedSize(true)
}

inline fun SwipeRefreshLayout.setupSwipeRefresh(crossinline func: () -> Unit) {
    setOnRefreshListener { func() }
    measure(View.MEASURED_SIZE_MASK,View.MEASURED_HEIGHT_STATE_SHIFT)
    isRefreshing = true
}