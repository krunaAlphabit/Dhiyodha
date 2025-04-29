package com.alphabit.dhiyodha.Dashboard

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class CategoryPagerAdapter(fm: FragmentManager?, private var mNumOfTabs: Int) :
    FragmentStatePagerAdapter(fm!!) {
    override fun getItem(position: Int): Fragment {
        return DynamicFragment.addfrag(position)
    }

    override fun getCount(): Int {
        return mNumOfTabs
    }
}