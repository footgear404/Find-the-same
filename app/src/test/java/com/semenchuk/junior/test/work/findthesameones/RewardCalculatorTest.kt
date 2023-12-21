package com.semenchuk.junior.test.work.findthesameones

import com.semenchuk.junior.test.work.findthesameones.domain.CoinsHandler
import com.semenchuk.junior.test.work.findthesameones.domain.CoinsRepository
import junit.framework.TestCase.assertEquals
import org.junit.Test

class CoinsRepositoryTest : CoinsRepository {
    override fun getCoins(): Int = 0
    override fun updateCoins(coins: Int): Int = 0
    override fun resetCoins() = Unit
}

class RewardCalculatorTest {

    @Test
    fun testGetReward() {

        val coinsHandler = CoinsHandler(CoinsRepositoryTest())
// Check MAX_REWARD
        assertEquals(100, coinsHandler.getReward(15))

// Check reward with PENALTY
        assertEquals(95, coinsHandler.getReward(21))
        assertEquals(90, coinsHandler.getReward(22))

// Check MIN_REWARD
        assertEquals(50, coinsHandler.getReward(30))
    }
}