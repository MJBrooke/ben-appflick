package com.brooke.michael.filmbuff.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.astuetz.PagerSlidingTabStrip;
import com.brooke.michael.filmbuff.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Michael on 06-Mar-16.
 */
public class MainFragment extends Fragment {

    @Bind(R.id.sliding_tabs)
    PagerSlidingTabStrip mSlidingTabs;

    @Bind(R.id.view_pager)
    ViewPager mViewPager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_discover, container, false);

        ButterKnife.bind(this, rootView);

        MoviePagerAdapter adapter = new MoviePagerAdapter(getChildFragmentManager());
        mViewPager.setAdapter(adapter);
        mSlidingTabs.setViewPager(mViewPager);

        return rootView;
    }

    public class MoviePagerAdapter extends FragmentPagerAdapter {

        private final String[] TITLES = {
                "This Month",
                "Most Popular",
                "Highest Rated"
        };

        ThisMonthFragment thisMonthFragment;

        public MoviePagerAdapter(FragmentManager fm) {
            super(fm);
            thisMonthFragment = new ThisMonthFragment();
        }

        @Override
        public int getCount() {
            return TITLES.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return TITLES[position];
        }

        @Override
        public Fragment getItem(int position) {

            return new ThisMonthFragment();

//            Fragment frag = thisMonthFragment;
//
//            switch(position){
//                case 0:
//                    frag = thisMonthFragment;
//                    break;
//                case 1:
//                    frag = thisMonthFragment;
//                    break;
//                case 2:
//                    frag = thisMonthFragment;
//                    break;
//                default:
//                    frag = thisMonthFragment;
//            }
//
//            return frag;
        }

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }

    }
}
