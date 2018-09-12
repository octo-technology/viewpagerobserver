package com.octo.viewpagerobserver


import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.view.ViewGroup

interface ObservableStateAdapter{
    fun attach(observer: ViewPagerObserver)
    fun update()
    fun workFinished(container: ViewGroup, position: Int, offscreenLimit: Int)
    fun workFinished(container: ViewGroup, position: Int)
}

abstract class ObservableStateFragmentPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm), ObservableStateAdapter {
    private var observer: ViewPagerObserver? = null

    override fun attach(observer: ViewPagerObserver) {
        this.observer = observer
    }

    override fun update() {
        observer?.update()
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val fragment = super.instantiateItem(container, position)
        if (fragment is ChildViewPagerStateObserverInterface) {
            fragment.attach(this)
        }
        return fragment
    }

    override fun workFinished(container: ViewGroup, position: Int){
        val fragment = getFragment(container, position)
        if (fragment != null && fragment is ChildViewPagerStateObserverInterface) {
            fragment.doWorkNow()
        }
    }

    override fun workFinished(container: ViewGroup, position: Int, offscreenLimit: Int) {
        var i = 1
        while (i <= offscreenLimit) {
            workFinished(container, position - i)
            workFinished(container, position + i)
            i++
        }
    }

    fun reload(container: ViewGroup, position: Int) {
        val fragment = getFragment(container, position)
        if (fragment != null && (fragment is ChildViewPagerStateObserverInterface)){
            fragment.doWorkNow()
        }
    }

    private fun getFragment(container: ViewGroup, position: Int) : Fragment? = instantiateItem(container, position) as Fragment?

}
