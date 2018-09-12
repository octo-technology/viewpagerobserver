package com.octo.viewpagerobserver


import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.view.ViewGroup

interface ObservableAdapter{
    fun attach(observer: ViewPagerObserver)
    fun update()
    fun workFinished(position: Int, offscreenLimit: Int)
    fun workFinished(position: Int)
}

abstract class ObservableFragmentPagerAdapter(val fm: FragmentManager) : FragmentPagerAdapter(fm), ObservableAdapter {
    private var observer: ViewPagerObserver? = null

    override fun attach(observer: ViewPagerObserver) {
        this.observer = observer
    }

    override fun update() {
        observer?.update()
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val fragment = super.instantiateItem(container, position)
        if (fragment is ChildViewPagerObserverInterface) {
            fragment.attach(this)
        }
        return fragment
    }

    override fun workFinished(position: Int){
        val fragment = getFragment(position)
        if (fragment != null && fragment is ChildViewPagerObserverInterface) {
            fragment.doWorkNow()
        }
    }

    private fun getFragment(position: Int) = fm.findFragmentByTag("android:switcher:" + observer?.getId() + ":" + position)

    override fun workFinished(position: Int, offscreenLimit: Int) {
        var i = 1
        while (i <= offscreenLimit) {
            workFinished(position - i)
            workFinished(position + i)
            i++
        }
    }

    fun reload(position: Int) {
        val fragment = getFragment(position)
        if (fragment != null && (fragment is ChildViewPagerObserverInterface)){
            fragment.doWorkNow()
        }
    }
}
