# ChildViewPagerObserver

## Version
1.0.0  
## Installation

With Gradle simple add this to your build.gradle file
```sh
compile 'com.octo:viewpagerobserver:1.0.0'
```
or  Copy paste the lib folder on your project

# Why
As you know `ViewPager` handle fragments for you and enable the user to swipe between different fragments.
But what happens if you have to do some work on a specific fragment (the one the user is focus on, in `onStart()` method for example).
You cannot guarantee that because the lifecycle of the fragments inside the view pager exists and is independant of your work. You can limit this with `offscreenLimit` property of the ViewPager but this property is limited to 1.
<img src="https://raw.githubusercontent.com/octo-technology/viewpagerobserver/master/images/classicviewpager.png" width="275" /> 

With `ViewPagerObserver` you can prevent the neighborhood fragment nearby with the `update()` method.
<img src="https://raw.githubusercontent.com/octo-technology/viewpagerobserver/master/images/viewpagerobserver.png" width="275" />

# How it works
In fact it works like the usual `ViewPager`.

## Adapter
You just need to extend:
 `ObservableStateFragmentPagerAdapter` instead of FragmentStatePagerAdapter
or `ObservableFragmentPagerAdapter` instead of FragmentPagerAdapter
1. If you choose ObservableFragmentPagerAdapter -> then you should use ChildViewPagerObserverInterface
2. If you chooseObservableStateFragmentPagerAdapter -> then you should use ChildViewPagerStateObserverInterface

## Fragment
Then you can use a `ChildViewPagerObserverFragment` or `ChildViewPagerStateObserverFragment` inside your `ViewPagerObserver` 
This Fragment handle observable object automatically for you.

But you can do it manually with `ChildViewPagerObserverInterface` or `ChildViewPagerStateObserverInterface` on your specific fragment.
```
class FragmentB : Fragment(), ChildViewPagerObserverInterface {  
    private var observable: ObservableAdapter? = null  
	override fun attach(observable: ObservableAdapter) {  
        this.observable = observable  
    }    
    override fun doWorkNow(){  
        Log.d(this.javaClass.name, "Doing work")
        observable?.update() // When a task (like a http request is finished you can prevent the neighborhood fragment with this method
    }  
}
```

OR

```
class FragmentB : Fragment(), ChildViewPagerStateObserverInterface {  
    private var observable: ObservableStateAdapter? = null  
    override fun attach(observable: ObservableStateAdapter) {  
        this.observable = observable  
    }   
    override fun doWorkNow(){  
        Log.d(this.javaClass.name, "Doing work")
        observable?.update() // When a task (like a http request is finished you can prevent the neighborhood fragment with this method
    }   
}
```
