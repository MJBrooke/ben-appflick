package com.brooke.michael.filmbuff.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.brooke.michael.filmbuff.R

import kotlinx.android.synthetic.main.fragment_discover.*

class MainFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View?
            = inflater!!.inflate(R.layout.fragment_discover, container, false)

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        viewPager.offscreenPageLimit = 2
        viewPager.adapter = MoviePagerAdapter(childFragmentManager)
        slidingTabs.setViewPager(viewPager)
    }

    inner class MoviePagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        private val TITLES = arrayOf("This Month", "Most Popular", "Highest Rated")

        override fun getCount(): Int = TITLES.size

        override fun getPageTitle(position: Int): CharSequence = TITLES[position]

        override fun getItem(position: Int): Fragment = when(position){
            0 -> TabFragment(TabType.THIS_MONTH)
            1 -> TabFragment(TabType.MOST_POPULAR)
            2 -> TabFragment(TabType.HIGHEST_RATED)
            else -> TabFragment(TabType.THIS_MONTH)
        }

        override fun getItemPosition(`object`: Any?): Int = POSITION_NONE

    }
}
