package jkl.craftbeertest.main.sl

import android.content.Context
import androidx.lifecycle.ViewModel
import jkl.craftbeertest.beer.domain.BeersRepository
import jkl.craftbeertest.beer.presentation.BeerViewModel
import jkl.craftbeertest.beer.sl.BeersModule
import jkl.craftbeertest.beer.sl.ProvideBeersRepository
import jkl.craftbeertest.detail.presentation.BeerDetailsViewModel
import jkl.craftbeertest.detail.sl.BeerDetailsModule
import jkl.craftbeertest.main.presentation.MainViewModel

interface DependencyContainer {

    fun <T : ViewModel> module(clasz: Class<T>): Module<*>

    class Error : DependencyContainer {
        override fun <T : ViewModel> module(clasz: Class<T>): Module<*> =
            throw IllegalStateException("no module found for $clasz")
    }

    class Base(
        private val context: Context,
        private val core: Core,
        private val dependencyContainer: DependencyContainer = Error()
    ) : DependencyContainer, ProvideBeersRepository {

        private val repository: BeersRepository by lazy {
            ProvideBeersRepository.Base(core, context).provideNumbersRepository()
        }

        override fun <T : ViewModel> module(clasz: Class<T>): Module<*> = when (clasz) {
            MainViewModel::class.java -> MainModule(core)
            BeerViewModel.Base::class.java -> BeersModule(context, core, this)
            BeerDetailsViewModel::class.java -> BeerDetailsModule(core)
            else -> dependencyContainer.module(clasz)
        }

        override fun provideNumbersRepository(): BeersRepository = repository
    }
}