package com.brooke.michael.filmbuff.extensions

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
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