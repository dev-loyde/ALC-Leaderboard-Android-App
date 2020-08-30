package com.devloyde.lidboard.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class BoardSectionsPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {
    private var mFragmentList: ArrayList<Fragment> = ArrayList()

    // Gets the total item count of Fragments in list
    override fun getItemCount(): Int {
        return mFragmentList.size
    }

    // Adds a new fragment to the list updating the viewpager and notifies changes
    fun addFragment(fragments: Fragment) {
        mFragmentList.add(fragments)
        notifyDataSetChanged()
    }

    // Creates a new Fragment from list at a specified position
    override fun createFragment(position: Int): Fragment {
        return mFragmentList[position];
    }
}