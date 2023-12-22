package com.semenchuk.junior.test.work.findthesameones.presentation.ui.game_scene

import androidx.lifecycle.ViewModel
import com.semenchuk.junior.test.work.findthesameones.domain.CardsHandler
import com.semenchuk.junior.test.work.findthesameones.domain.CoinsHandler
import com.semenchuk.junior.test.work.findthesameones.domain.GameStateHandler
import com.semenchuk.junior.test.work.findthesameones.domain.State
import com.semenchuk.junior.test.work.findthesameones.presentation.models.Card
import com.semenchuk.junior.test.work.findthesameones.utils.GameConstants.MAX_REWARD
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class GameSceneViewModel(
    private val coinsHandler: CoinsHandler,
    private val cardsHandler: CardsHandler,
    private val gameStateHandler: GameStateHandler,
) : ViewModel() {

    private var _timer = MutableStateFlow<Int?>(null)
    private var _coins = MutableStateFlow(MAX_REWARD)
    private var _currentReward = MutableStateFlow(MAX_REWARD)
    private var _cards = MutableStateFlow<List<Card>>(emptyList())
    private var _gameState = MutableStateFlow<State>(State.AWAIT)
    val timer get() = _timer.asStateFlow()
    val coins get() = _coins.asStateFlow()
    val currentReward get() = _currentReward.asStateFlow()
    val cards get() = _cards.asStateFlow()
    val gameState get() = _gameState.asStateFlow()

    init {
        _coins.value = coinsHandler.get()
        _cards.value = cardsHandler.get()
    }

    fun setTime(timeInSeconds: Int) {
        _timer.value = timeInSeconds
    }

    fun calculateReward(timeInSeconds: Int) {
        _currentReward.value = coinsHandler.getReward(timeInSeconds)
    }

    fun saveReward(coins: Int) {
        coinsHandler.update(coins)
    }

    fun flipCard(cardPosition: Int): Int {
        val changedPosition = cardsHandler.flip(cardPosition)
        _gameState.value = gameStateHandler.checkState()
        return changedPosition
    }
}
