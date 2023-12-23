package com.semenchuk.junior.test.work.findthesameones.data

import com.semenchuk.junior.test.work.findthesameones.data.storage.AppStorage
import com.semenchuk.junior.test.work.findthesameones.domain.interfaces.CoinsRepository

class CoinsRepositoryImpl(
    private val appStorage: AppStorage,
) : CoinsRepository {

    override fun getCoins(): Int {
        return appStorage.availableCoins
    }

    override fun updateCoins(coins: Int): Int {
        appStorage.availableCoins += coins
        return appStorage.availableCoins
    }

    override fun resetCoins(): Int {
        appStorage.availableCoins = START_COINS
        return appStorage.availableCoins
    }

    companion object {
        const val START_COINS = 0
    }
}