package com.octo.viewpagerobserver

interface ChildViewPagerObserverInterface {
    fun doWorkNow()
    fun attach(observable: ObservableAdapter)
}