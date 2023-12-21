package com.semenchuk.junior.test.work.findthesameones.domain

import com.semenchuk.junior.test.work.findthesameones.presentation.models.Card

class CardsHandler(
    private val cardRepository: CardRepository,
) {

    fun get(): List<Card> {
        return cardRepository.initializeCards()
    }

    fun flip(position: Int): Int {
        return cardRepository.flipCard(position)
    }
}