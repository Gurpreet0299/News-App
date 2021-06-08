package com.example.newsapp

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class ViewPagerAdapter(supportFragmentManager :FragmentManager) : FragmentPagerAdapter(supportFragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val mFragmentList = ArrayList<Fragment>();                     //List of all fragments
    private val mFragmentTitleList = ArrayList<String>();                  //List of titles of all fragments

    override fun getItem(position: Int): Fragment {
        return mFragmentList[position]                                         //return fragment at a particular position
    }

    override fun getCount(): Int {
        return mFragmentList.size                                    //return number of fragments
         }

    override fun getPageTitle(position: Int): CharSequence? {
        return mFragmentTitleList[position]
    }

    fun addFragment(fragment :Fragment,title :String){

        mFragmentList.add(fragment)
        mFragmentTitleList.add(title)
    }
}