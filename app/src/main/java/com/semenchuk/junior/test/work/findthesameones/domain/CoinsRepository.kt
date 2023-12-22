package com.semenchuk.junior.test.work.findthesameones.domain

interface CoinsRepository {
    fun getCoins(): Int
    fun updateCoins(coins: Int): Int
    fun resetCoins(): Int
}