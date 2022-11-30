package jkl.craftbeertest.main.sl

import jkl.craftbeertest.main.presentation.MainViewModel
import jkl.craftbeertest.main.presentation.NavigationCommunication

class MainModule(private val provideNavigation: ProvideNavigation): Module<MainViewModel> {
    override fun viewModel(): MainViewModel = MainViewModel(provideNavigation.provideNavigation())
}