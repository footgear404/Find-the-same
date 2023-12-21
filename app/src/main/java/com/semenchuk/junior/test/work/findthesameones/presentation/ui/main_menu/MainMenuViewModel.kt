package com.semenchuk.junior.test.work.findthesameones.presentation.ui.main_menu

import androidx.lifecycle.ViewModel
import com.semenchuk.junior.test.work.findthesameones.domain.CoinsHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainMenuViewModel(
    coinsHandler: CoinsHandler,
) : ViewModel() {
    private var _coins = MutableStateFlow<Int>(0)
    val coins get() = _coins.asStateFlow()

    init {
        _coins.value = coinsHandler.get()
    }
}