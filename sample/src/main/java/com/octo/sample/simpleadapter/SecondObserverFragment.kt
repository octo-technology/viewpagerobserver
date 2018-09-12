package com.octo.sample.simpleadapter

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.octo.viewpagerobserver.ChildViewPagerObserverInterface
import com.octo.viewpagerobserver.ObservableAdapter
import com.octo.sample.R
import kotlinx.android.synthetic.main.fragment_second.view.workButton

class SecondObserverFragment : Fragment(), ChildViewPagerObserverInterface {
    private var observable: ObservableAdapter? = null
    override fun attach(observable: ObservableAdapter) {
        this.observable = observable
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_second, container, false).also {
            it.workButton.setOnClickListener{
                observable?.update()
            }
        }
    }

    override fun doWorkNow(){
        Log.d(this.javaClass.name, "Doing work")
    }

}
