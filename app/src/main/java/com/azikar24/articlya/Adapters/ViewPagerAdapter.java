package com.azikar24.articlya.Adapters;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    private final List<Fragment> mFragmentList = new ArrayList<>();

    /**
     * This method to initialize the fragment
     *
     * @param fm
     */
    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    /**
     * This method is to add a fragment
     *
     * @param fragment
     */

    public void addFragment(Fragment fragment) {
        mFragmentList.add(fragment);
    }


    /**
     * This method to get the fragments
     *
     * @param position
     * @return fragment
     */

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    /**
     * @return number of fragments
     */

    @Override
    public int getCount() {
        return mFragmentList.size();
    }
}
