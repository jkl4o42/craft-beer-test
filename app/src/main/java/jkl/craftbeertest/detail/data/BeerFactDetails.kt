package jkl.craftbeertest.detail.data

import jkl.craftbeertest.beer.presentation.BeerUi

interface BeerFactDetails {

    interface Save {
        fun save(data: BeerUi)
    }

    interface Read {
        fun read(): BeerUi?
    }

    interface Mutable : Save, Read

    class Base : Mutable {

        private var value: BeerUi? = null

        override fun save(data: BeerUi) { value = data }

        override fun read(): BeerUi? = value
    }
}