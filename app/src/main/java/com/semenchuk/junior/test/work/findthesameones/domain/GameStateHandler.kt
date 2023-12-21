package com.semenchuk.junior.test.work.findthesameones.domain

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