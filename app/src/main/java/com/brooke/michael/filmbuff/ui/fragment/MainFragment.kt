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

/**
 * Created by Michael on 06-Mar-16.
 */
class MainFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater!!.inflate(R.layout.fragment_discover, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        val adapter = MoviePagerAdapter(childFragmentManager)
        view_pager.adapter = adapter
        sliding_tabs.setViewPager(view_pager)
    }

    inner class MoviePagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        private val TITLES = arrayOf("This Month", "Most Popular", "Highest Rated")

        override fun getCount(): Int {
            return TITLES.size
        }

        override fun getPageTitle(position: Int): CharSequence {
            return TITLES[position]
        }

        override fun getItem(position: Int): Fragment {
            return ThisMonthFragment()
        }

        override fun getItemPosition(`object`: Any?): Int {
            return POSITION_NONE
        }

    }
}
