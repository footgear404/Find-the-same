package com.semenchuk.junior.test.work.findthesameones.data

import com.semenchuk.junior.test.work.findthesameones.R
import com.semenchuk.junior.test.work.findthesameones.domain.interfaces.CardRepository
import com.semenchuk.junior.test.work.findthesameones.presentation.models.Card

class CardRepositoryImpl : CardRepository {

    override var isAllFlipped: Boolean = false

    private var cardStorage = mutableListOf<Card>()

    private var lastOpenedCard: Card? = null

    override fun initializeCards(): List<Card> {
        val cards = availableImages.flatMap { imageResource ->
            (1..2).map { copy ->
                Card(id = imageResource, isFlipped = false, copy = copy)
            }
        }

        cardStorage = cards.shuffled().toMutableList()
        return cardStorage.toList()
    }

    override fun update(cards: List<Card>): List<Card> {
        val card =
            cards.zip(cardStorage).firstOrNull { it.first.isFlipped != it.second.isFlipped }?.first

        card?.let {
            if (lastOpenedCard == null) {
                lastOpenedCard = it
                cardStorage[cards.indexOf(it)] = it
            } else {
                if (lastOpenedCard!!.id != it.id) {
                    cardStorage[cardStorage.indexOf(lastOpenedCard)] =
                        lastOpenedCard!!.copy(isFlipped = false)
                    cardStorage[cards.indexOf(it)] = it.copy(isFlipped = false)
                    lastOpenedCard = null
                } else {
                    cardStorage[cards.indexOf(it)] = it
                    lastOpenedCard = null
                }
            }
        }
        isAllFlipped = cardStorage.all { it.isFlipped }

        return cardStorage
    }

    companion object {
        val availableImages = listOf(
            R.drawable.ci_15_compass,
            R.drawable.ci_11_scull,
            R.drawable.ci_14_sword,
            R.drawable.ci_16_helmet,
            R.drawable.ci_5_poison,
            R.drawable.ci_6_potion,
            R.drawable.ci_17_pendant,
            R.drawable.ci_8_soup_bowl,
            R.drawable.ci_9_tankard,
            R.drawable.ci_10_treasure_chest,
        )
    }
}