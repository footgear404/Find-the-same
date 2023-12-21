package com.semenchuk.junior.test.work.findthesameones.presentation.models

import com.semenchuk.junior.test.work.findthesameones.entity.CardEntity

data class Card(
    override val id: Int,
    override var isFlipped: Boolean,
) : CardEntity

