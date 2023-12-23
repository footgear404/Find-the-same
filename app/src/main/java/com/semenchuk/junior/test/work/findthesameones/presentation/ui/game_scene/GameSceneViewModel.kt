package com.semenchuk.junior.test.work.findthesameones.presentation.ui.game_scene

import android.util.Log
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
    val timer = _timer.asStateFlow()
    val coins = _coins.asStateFlow()
    val currentReward = _currentReward.asStateFlow()
    val cards = _cards.asStateFlow()
    val gameState = _gameState.asStateFlow()

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

    fun flipCardInCardsList(position: Int) {
        Log.d("CARD_B", "${_cards.value[position]}")
        if (!_cards.value[position].isFlipped) {
            val updatedList =
                _cards.value.toMutableList() // Создаем новый список на основе текущего
            val currentCard = updatedList[position].copy(isFlipped = true)
            updatedList[position] = currentCard
            _cards.value = updatedList.toList() // Присваиваем новый список в _cards.value
            Log.d("CARD_A", "${_cards.value[position]}")
        }
    }

    fun updateCardsInStorage(cards: List<Card>) {
        val cardsFromStorage = cardsHandler.update(cards)
        Log.d("TAG", "updateCardsInStorage: $cardsFromStorage")
        _cards.value = cardsFromStorage
        _gameState.value = gameStateHandler.checkState()
    }
}
