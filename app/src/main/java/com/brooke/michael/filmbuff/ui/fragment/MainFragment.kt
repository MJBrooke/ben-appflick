package com.brooke.michael.filmbuff.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.brooke.michael.filmbuff.R
import com.brooke.michael.filmbuff.enum.TAB_TYPE

import kotlinx.android.synthetic.main.fragment_discover.*

class MainFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater!!.inflate(R.layout.fragment_discover, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        view_pager.adapter = MoviePagerAdapter(childFragmentManager)
        sliding_tabs.setViewPager(view_pager)
    }

    inner class MoviePagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        private val TITLES = arrayOf("This Month", "Most Popular", "Highest Rated")

        override fun getCount(): Int = TITLES.size

        override fun getPageTitle(position: Int): CharSequence = TITLES[position]

        override fun getItem(position: Int): Fragment = when(position){
            0 -> TabFragment(TAB_TYPE.THIS_MONTH)
            else -> TabFragment(TAB_TYPE.MOST_POPULAR)
        }

        override fun getItemPosition(`object`: Any?): Int = POSITION_NONE

    }
}
