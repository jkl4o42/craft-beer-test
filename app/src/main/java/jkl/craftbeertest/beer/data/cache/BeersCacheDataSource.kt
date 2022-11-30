package jkl.craftbeertest.beer.data.cache

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

interface BeersCacheDataSource {

    suspend fun likeBeer(item: Int): Int
    suspend fun unlikeBeer(item: Int): Int

    class Base(
        context: Context,
        private val gson: Gson
    ) : BeersCacheDataSource {

        private val mutex = Mutex()

        private val sharedPreference = context.getSharedPreferences(NAME, Context.MODE_PRIVATE)
        private val itemType = object : TypeToken<ArrayList<Int>>() {}.type
        private val favorite = sharedPreference.getString(ITEM_NAME, null)
        private val list = gson.fromJson<ArrayList<Int>>(favorite, itemType) ?: ArrayList()

        override suspend fun likeBeer(item: Int): Int {
            mutex.withLock {
                list.add(item)
                val json = gson.toJson(list)
                sharedPreference.edit().putString(ITEM_NAME, json).apply()
                return item
            }
        }

        override suspend fun unlikeBeer(item: Int): Int {
            mutex.withLock {
                list.findLast { it == item }?.apply {
                    list.remove(this)
                }
                val json = gson.toJson(list)
                sharedPreference.edit().putString(ITEM_NAME, json).apply()
                return item
            }
        }

        companion object {
            private const val NAME = "sharedPreference"
            private const val ITEM_NAME = "favorite"
        }
    }
}