package jkl.craftbeertest.main.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel

class MainViewModel(
    private val navigationCommunication: NavigationCommunication.Mutable
) : ViewModel(), Init, Communication.Observe<NavigationStrategy> {

    override fun observe(owner: LifecycleOwner, observer: Observer<NavigationStrategy>) =
        navigationCommunication.observe(owner, observer)

    override fun init(isFirstRun: Boolean) {
        if (isFirstRun) {
            navigationCommunication.map(NavigationStrategy.Replace(Screen.Beer))
        }
    }

}