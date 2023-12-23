package com.semenchuk.junior.test.work.findthesameones.presentation.ui.end_game

import androidx.lifecycle.ViewModel
import com.semenchuk.junior.test.work.findthesameones.domain.CoinsHandler

class EndGameViewModel(
    private val coinsHandler: CoinsHandler,
) : ViewModel() {
    fun updateReward(coins: Int): Int {
        return coinsHandler.update(coins)
    }
}