package com.octo.viewpagerobserver

interface ChildViewPagerStateObserverInterface {
    fun doWorkNow()
    fun attach(observable: ObservableStateAdapter)
}