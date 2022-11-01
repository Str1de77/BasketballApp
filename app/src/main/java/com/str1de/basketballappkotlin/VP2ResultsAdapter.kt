package com.str1de.basketballappkotlin

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.ListFragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class VP2ResultsAdapter(FActivity: FragmentActivity, private val list: List<Fragment>): FragmentStateAdapter(FActivity) {
    override fun getItemCount(): Int {
        return list.size
    }

    override fun createFragment(position: Int): Fragment {
        return list[position]
    }
}