package com.octo.viewpagerobserver

import android.support.v4.app.Fragment

abstract class ChildViewPagerObserverFragment : Fragment(), ChildViewPagerObserverInterface {
    private var observable: ObservableAdapter? = null

    override fun attach(observable: ObservableAdapter) {
        this.observable = observable
    }

    override fun doWorkNow() {
        /* do nothing by default */
    }
}