package com.octo.sample.simpleadapter

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.octo.sample.R
import com.octo.viewpagerobserver.ChildViewPagerObserverInterface
import com.octo.viewpagerobserver.ObservableAdapter

class ThirdObserverFragment : Fragment(), ChildViewPagerObserverInterface {
    override fun attach(observable: ObservableAdapter) {
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
