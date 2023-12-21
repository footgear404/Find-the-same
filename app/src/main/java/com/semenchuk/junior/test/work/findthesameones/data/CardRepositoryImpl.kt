package com.semenchuk.junior.test.work.findthesameones.data

import com.semenchuk.junior.test.work.findthesameones.R
import com.semenchuk.junior.test.work.findthesameones.domain.CardRepository
import com.semenchuk.junior.test.work.findthesameones.presentation.models.Card

class CardRepositoryImpl : CardRepository {

    override var isAllFlipped: Boolean = false

    private var cardStorage = mutableListOf<Card>()

    override fun initializeCards(): List<Card> {
        val cards = mutableListOf<Card>()

        for (imageResource in availableImages) {
            for (i in 1..2) {
                cards.add(Card(id = imageResource, isFlipped = false))
            }
        }

        cardStorage = cards.shuffled().toMutableList()

        return cardStorage.toList()
    }

    override fun flipCard(position: Int): Int {
        val card = cardStorage[position]

        card.isFlipped = card.isFlipped != true
        cardStorage[position] = card

        isAllFlipped = cardStorage.all { it.isFlipped }

        return position
    }

    companion object {
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
    }
}