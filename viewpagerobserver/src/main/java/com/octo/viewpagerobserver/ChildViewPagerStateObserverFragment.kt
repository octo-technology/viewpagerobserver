package com.octo.viewpagerobserver

import android.support.v4.app.Fragment

abstract class ChildViewPagerStateObserverFragment : Fragment(), ChildViewPagerStateObserverInterface {
    private var observable: ObservableStateAdapter? = null

    override fun attach(observable: ObservableStateAdapter) {
        this.observable = observable
    }

    override fun doWorkNow() {
        /* do nothing by default */
    }
}