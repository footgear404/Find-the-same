package com.semenchuk.junior.test.work.findthesameones.domain

class CoinsHandler(
    private val coinsRepository: CoinsRepository,
) {
    fun get(): Int {
        return coinsRepository.getCoins()
    }

    fun update(coins: Int): Int {
        return coinsRepository.updateCoins(coins)
    }

    fun reset() {
        coinsRepository.resetCoins()
    }
}