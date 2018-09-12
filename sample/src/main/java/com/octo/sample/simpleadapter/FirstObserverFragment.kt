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
import kotlinx.android.synthetic.main.fragment_first.view.*

class FirstObserverFragment : Fragment(), ChildViewPagerObserverInterface {
    override fun attach(observable: ObservableAdapter) {
        /* do nothing */
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_first, container, false).also {
            it.button.setOnClickListener {
                startActivity(SecondActivity.newIntent(
                    requireContext()))
            }
        }
    }

    override fun doWorkNow() {
        Log.d(this.javaClass.name, "Doing work")
    }

}
