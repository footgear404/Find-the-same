package com.semenchuk.junior.test.work.findthesameones.presentation

import com.semenchuk.junior.test.work.findthesameones.R
import com.semenchuk.junior.test.work.findthesameones.data.CardRepositoryImpl
import com.semenchuk.junior.test.work.findthesameones.presentation.models.Card

fun main() {

    var allCards = mutableListOf<Card>()

    for (imageResource in CardRepositoryImpl.availableImages) {

        for (i in 0..1) {
            allCards.add(Card(id = imageResource, isFlipped = false, copy = i))
        }
    }

    val availableImages = listOf(
        R.drawable.ci_1_bag,
        R.drawable.ci_2_book,
        R.drawable.ci_3_compass,
        R.drawable.ci_4_helmet,
        R.drawable.ci_5_poison,
        R.drawable.ci_6_potion,
        R.drawable.ci_7_smoke_pipe,
        R.drawable.ci_8_soup_bowl,
        R.drawable.ci_9_tankard,
        R.drawable.ci_10_treasure_chest,
    )

    for (i in allCards) {
        val x = allCards.indexOf(i)
        println("${i.hashCode()} : $x")
    }

}