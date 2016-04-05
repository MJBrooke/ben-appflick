package com.brooke.michael.filmbuff.extensions

import android.app.Activity
import android.content.Intent
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentTransaction
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.Toast
import com.brooke.michael.filmbuff.R
import com.brooke.michael.filmbuff.ui.activity.SearchActivity
import com.squareup.picasso.Picasso
import org.jetbrains.anko.async
import org.jetbrains.anko.uiThread


fun toast(view: View, message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(view.context, message, duration).show()
}

fun toast(view: View, message: Int, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(view.context, message, duration).show()
}

fun AppCompatActivity.closeKeyboard(){
    val keyboardManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    keyboardManager.hideSoftInputFromWindow(currentFocus.windowToken, 0)
}

fun SearchView.clear(){
    setQuery("", false);
    clearFocus();
    isIconified = true
}

fun AppCompatActivity.startSearchActivity(stringExtra: String){
    val intent = Intent(baseContext, SearchActivity::class.java)
    intent.putExtra("QUERY_STRING", stringExtra);
    startActivity(intent)
}

fun ViewGroup.inflateLayout(resID: Int): View {
    return LayoutInflater.from(context).inflate(resID, this, false)
}

fun ImageView.loadImage(url: String) {
    async(){
        val request = Picasso.with(context).load(url).placeholder(R.drawable.ic_menu_camera)
        uiThread { request.into(this@loadImage) }
    }
}

fun RecyclerView.setupDefaultConfig(){
    layoutManager = LinearLayoutManager(context)
    setHasFixedSize(true)
}

inline fun FragmentActivity.changeFragment(func: FragmentTransaction.() -> FragmentTransaction){
    supportFragmentManager
            .beginTransaction()
            .func()
            .addToBackStack(this.localClassName)
            .commit()
}

inline fun SwipeRefreshLayout.setupSwipeRefresh(crossinline onSwipe: () -> Unit) {
    setOnRefreshListener { onSwipe() }
    measure(View.MEASURED_SIZE_MASK, View.MEASURED_HEIGHT_STATE_SHIFT)
    isRefreshing = true
}