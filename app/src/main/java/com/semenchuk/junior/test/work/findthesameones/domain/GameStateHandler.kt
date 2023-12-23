package com.semenchuk.junior.test.work.findthesameones.domain

import com.semenchuk.junior.test.work.findthesameones.domain.interfaces.CardRepository

class GameStateHandler(
    private val cardRepository: CardRepository,
) {
    fun checkState(): State {
        return when (cardRepository.isAllFlipped) {
            true -> State.WIN
            else -> State.PROGRESS
        }
    }
}