package jkl.craftbeertest.beer.sl

import android.content.Context
import com.google.gson.Gson
import jkl.craftbeertest.beer.data.BaseBeersRepository
import jkl.craftbeertest.beer.data.BeerDataToDomain
import jkl.craftbeertest.beer.data.HandleDataRequest
import jkl.craftbeertest.beer.data.cache.BeersCacheDataSource
import jkl.craftbeertest.beer.data.cloud.BeersCloudDataSource
import jkl.craftbeertest.beer.data.cloud.BeersService
import jkl.craftbeertest.beer.domain.*
import jkl.craftbeertest.beer.presentation.*
import jkl.craftbeertest.main.sl.Core
import jkl.craftbeertest.main.sl.Module

class BeersModule(
    private val context: Context,
    private val core: Core,
    private val provideRepository: ProvideBeersRepository
) : Module<BeerViewModel.Base> {

    override fun viewModel(): BeerViewModel.Base {
        val repository = provideRepository.provideNumbersRepository()
        val communications = BeersCommunications.Base(
            BeersListCommunication.Base(),
            BeersProgressCommunication.Base(),
            BeerItemCommunication.Base()
        )
        return BeerViewModel.Base(
            HandleBeersRequest.Base(
                core.provideDispatchers(),
                communications,
                BeersResultMapper(
                    communications,
                    BeersUiMapper()
                )
            ),
            HandleBeersData.Base(
                core.provideDispatchers(),
                communications
            ),
            BeersInteractor.Base(
                repository,
                HandleRequest.Base(
                    HandleError.Base(ManageResources.Base(context)),
                    repository
                ),
                core.provideBeerDetails()
            ),
            core.provideNavigation(),
            communications
        )
    }
}

interface ProvideBeersRepository {

    fun provideNumbersRepository(): BeersRepository

    class Base(
        private val core: Core,
        private val context: Context
    ) : ProvideBeersRepository {
        override fun provideNumbersRepository(): BeersRepository {
            val cloudDataSource = BeersCloudDataSource.Base(core.service(BeersService::class.java))
            val cacheDataSource = BeersCacheDataSource.Base(context, Gson())
            return BaseBeersRepository(
                cacheDataSource,
                cloudDataSource,
                HandleDataRequest.Base(
                    BeerDataToDomain(),
                    HandleDomainError()
                )
            )
        }
    }
}