package com.semenchuk.junior.test.work.findthesameones.presentation.ui.game_scene

import androidx.lifecycle.ViewModel
import com.semenchuk.junior.test.work.findthesameones.domain.CoinsHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class GameSceneViewModel(
    private val coinsHandler: CoinsHandler,
) : ViewModel() {

    private var _timer = MutableStateFlow<Int?>(null)
    private var _coins = MutableStateFlow<Int?>(null)
    private var _currentReward = MutableStateFlow<Int?>(null)

    val timer get() = _timer.asStateFlow()
    val coins get() = _coins.asStateFlow()
    val currentReward get() = _currentReward.asStateFlow()

    init {
        _coins.value = coinsHandler.get()
    }

    fun setTime(timeInSeconds: Int) {
        _timer.value = timeInSeconds
    }

    fun takeReward(timeInSeconds: Int) {
        _currentReward.value = coinsHandler.getReward(timeInSeconds)
    }
}
