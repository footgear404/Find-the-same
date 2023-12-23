package com.semenchuk.junior.test.work.findthesameones.domain.interfaces

import com.semenchuk.junior.test.work.findthesameones.presentation.models.Card

interface CardRepository {

    var isAllFlipped: Boolean
    fun initializeCards(): List<Card>

    fun update(cards: List<Card>): List<Card>
}