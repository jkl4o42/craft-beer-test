package jkl.craftbeertest.main.sl

import jkl.craftbeertest.beer.data.cloud.CloudModule
import jkl.craftbeertest.beer.presentation.DispatchersList
import jkl.craftbeertest.detail.data.BeerFactDetails
import jkl.craftbeertest.main.presentation.NavigationCommunication

interface Core : CloudModule, ProvideNavigation, ProvideBeerDetails {

    fun provideDispatchers(): DispatchersList

    class Base(
        private val provideInstances: ProvideInstance
    ) : Core {

        private val beerDetails = BeerFactDetails.Base()

        private val navigationCommunication = NavigationCommunication.Base()

        private val cloudModule by lazy {
            provideInstances.provideCloudModule()
        }

        private val dispatchersList by lazy {
            DispatchersList.Base()
        }

        override fun <T> service(clasz: Class<T>): T = cloudModule.service(clasz)

        override fun provideNavigation() = navigationCommunication

        override fun provideBeerDetails(): BeerFactDetails.Mutable = beerDetails

        override fun provideDispatchers(): DispatchersList = dispatchersList
    }
}

interface ProvideNavigation {
    fun provideNavigation(): NavigationCommunication.Mutable
}

interface ProvideBeerDetails {
    fun provideBeerDetails(): BeerFactDetails.Mutable
}