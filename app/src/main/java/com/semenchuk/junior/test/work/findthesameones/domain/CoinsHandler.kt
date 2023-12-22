package com.semenchuk.junior.test.work.findthesameones.domain

import com.semenchuk.junior.test.work.findthesameones.utils.GameConstants.MAX_REWARD
import com.semenchuk.junior.test.work.findthesameones.utils.GameConstants.MIN_REWARD
import com.semenchuk.junior.test.work.findthesameones.utils.GameConstants.PENALTY
import com.semenchuk.junior.test.work.findthesameones.utils.GameConstants.TIME_THRESHOLD

class CoinsHandler(
    private val coinsRepository: CoinsRepository,
) {
    fun get(): Int {
        return coinsRepository.getCoins()
    }

    fun getReward(timeInSeconds: Int): Int {
        val rewardCoins = when {
            timeInSeconds <= TIME_THRESHOLD -> MAX_REWARD
            else -> maxOf(MIN_REWARD, MAX_REWARD - (timeInSeconds - TIME_THRESHOLD) * PENALTY)
        }
        return rewardCoins
    }

    fun update(coins: Int): Int {
        return coinsRepository.updateCoins(coins)
    }

    fun reset(): Int {
        return coinsRepository.resetCoins()
    }
}