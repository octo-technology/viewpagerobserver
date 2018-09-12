package com.octo.sample.stateadapter

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import com.octo.viewpagerobserver.ObservableStateFragmentPagerAdapter
import com.octo.sample.R
import kotlinx.android.synthetic.main.activity_main.viewPagerObserver

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewPagerObserver.adapter = PageAdapter(supportFragmentManager)
        viewPagerObserver.currentItem = 1
    }
}

class PageAdapter(fragmentManager: FragmentManager) : ObservableStateFragmentPagerAdapter(fragmentManager) {
    override fun getCount(): Int {
        return 3
    }

    override fun getItem(position: Int): Fragment {

        return when (position) {
            0 -> FirstObserverFragment()
            1 -> SecondObserverFragment()
            2 -> ThirdObserverFragment()
            else -> {
                throw IllegalStateException()
            }
        }
    }
}
