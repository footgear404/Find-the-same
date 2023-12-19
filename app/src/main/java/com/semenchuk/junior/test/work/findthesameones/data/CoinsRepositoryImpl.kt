package com.semenchuk.junior.test.work.findthesameones.data

import com.semenchuk.junior.test.work.findthesameones.domain.CoinsRepository

class CoinsRepositoryImpl : CoinsRepository {

    private var availableCoins = START_COINS

    override fun getCoins(): Int {
        return availableCoins
    }

    override fun updateCoins(coins: Int): Int {
        availableCoins += coins
        return availableCoins
    }

    override fun resetCoins() {
        availableCoins = START_COINS
    }

    companion object {
        const val START_COINS = 100
    }
}