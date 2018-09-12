package com.octo.viewpagerobserver

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.util.AttributeSet

interface ViewPagerObserver {
    fun update()
    fun getCurrentPosition(): Int
    fun getId(): Int
}

open class ViewPager(@get:JvmName(
    "getContext_") private val context: Context, attrs: AttributeSet? = null) : android.support.v4.view.ViewPager(
    context,
    attrs), ViewPagerObserver {


    override fun setAdapter(adapter: PagerAdapter?) {
        super.setAdapter(adapter)

        getObservableFragmentPagerAdapter()?.attach(this)
        getObservableStateFragmentPagerAdapter()?.attach(this)

        this.addOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(position: Int,
                                        positionOffset: Float,
                                        positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
                getObservableFragmentPagerAdapter()?.workFinished(currentItem)
                getObservableStateFragmentPagerAdapter()?.workFinished(this@ViewPager, currentItem)
            }

        })
    }

    private fun getObservableFragmentPagerAdapter() = adapter as? ObservableFragmentPagerAdapter?

    private fun getObservableStateFragmentPagerAdapter() = adapter as? ObservableStateFragmentPagerAdapter?

    override fun update() {
        notifyAllChildViewPagerObserverFragment()
    }

    private fun notifyAllChildViewPagerObserverFragment() {
        getObservableFragmentPagerAdapter()?.workFinished(currentItem, offscreenPageLimit)
        getObservableStateFragmentPagerAdapter()?.workFinished(this, currentItem, offscreenPageLimit)
    }


    override fun getCurrentPosition() = currentItem

    fun reload() {
        getObservableFragmentPagerAdapter()?.reload(currentItem)
        getObservableStateFragmentPagerAdapter()?.reload(this, currentItem)
    }


}