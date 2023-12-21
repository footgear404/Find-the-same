package com.semenchuk.junior.test.work.findthesameones.domain

import com.semenchuk.junior.test.work.findthesameones.presentation.models.Card

interface CardRepository {

    var isAllFlipped: Boolean
    fun initializeCards(): List<Card>

    fun flipCard(position: Int): Int
}