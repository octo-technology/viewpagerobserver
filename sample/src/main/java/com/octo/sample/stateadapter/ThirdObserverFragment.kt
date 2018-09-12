package com.octo.sample.stateadapter

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.octo.sample.R
import com.octo.viewpagerobserver.ChildViewPagerStateObserverInterface
import com.octo.viewpagerobserver.ObservableStateAdapter

class ThirdObserverFragment : Fragment(), ChildViewPagerStateObserverInterface {
    override fun attach(observable: ObservableStateAdapter) {
        /* do nothing */
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_third, container, false)
    }

    override fun doWorkNow(){
        Log.d(this.javaClass.name, "Doing work")
    }
}
