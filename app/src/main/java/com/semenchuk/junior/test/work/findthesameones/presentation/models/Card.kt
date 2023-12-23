package com.semenchuk.junior.test.work.findthesameones.presentation.models

import com.semenchuk.junior.test.work.findthesameones.entity.CardEntity
import kotlin.random.Random

data class Card(
    override val id: Int,
    override var isFlipped: Boolean,
    var copy: Int
) : CardEntity

