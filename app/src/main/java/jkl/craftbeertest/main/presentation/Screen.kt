package jkl.craftbeertest.main.presentation

import jkl.craftbeertest.beer.presentation.BeersFragment
import jkl.craftbeertest.detail.presentation.BeerDetailsFragment

sealed class Screen {

    abstract fun fragment(): Class<out BaseFragment<*>>

    object Details : Screen() {
        override fun fragment(): Class<out BaseFragment<*>> = BeerDetailsFragment::class.java
    }

    object Beer : Screen() {
        override fun fragment(): Class<out BaseFragment<*>> = BeersFragment::class.java
    }
}