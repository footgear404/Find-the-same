package com.semenchuk.junior.test.work.findthesameones.domain

import com.semenchuk.junior.test.work.findthesameones.domain.interfaces.CardRepository
import com.semenchuk.junior.test.work.findthesameones.presentation.models.Card

class CardsHandler(
    private val cardRepository: CardRepository,
) {

    fun get(): List<Card> {
        return cardRepository.initializeCards()
    }

    fun update(cards: List<Card>): List<Card> {
        return cardRepository.update(cards)
    }

}