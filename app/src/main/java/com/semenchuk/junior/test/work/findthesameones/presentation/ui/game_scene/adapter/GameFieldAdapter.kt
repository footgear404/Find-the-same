package com.semenchuk.junior.test.work.findthesameones.presentation.ui.game_scene.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.semenchuk.junior.test.work.findthesameones.R
import com.semenchuk.junior.test.work.findthesameones.databinding.GameCellBinding
import com.semenchuk.junior.test.work.findthesameones.domain.utils.CardAnimator
import com.semenchuk.junior.test.work.findthesameones.presentation.models.Card

class GameFieldAdapter(
    private val onCardClickListener: (Int, Card) -> Unit,
    private val cardAnimator: CardAnimator,
) : RecyclerView.Adapter<GameFieldAdapter.CardHolder>() {

    private var cards = listOf<Card>()

    fun submitData(data: List<Card>) {
        val diffResult = DiffUtil.calculateDiff(CardDiffCallback(cards, data))
        cards = data
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = GameCellBinding.inflate(inflater, parent, false)
        return CardHolder(binding)
    }

    override fun getItemCount(): Int = cards.size

    override fun onBindViewHolder(holder: CardHolder, position: Int, payloads: MutableList<Any>) {
        if (payloads.isNotEmpty()) {
            holder.updateCard(cards[position])
        } else {
            super.onBindViewHolder(holder, position, payloads)
        }
    }

    override fun onBindViewHolder(holder: CardHolder, position: Int) {
        holder.bind(cards[position])
    }

    inner class CardHolder(private val binding: GameCellBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(card: Card) {
            updateCard(card)
            binding.cardImage.setOnClickListener {
                onCardClickListener.invoke(adapterPosition, card)
            }
        }

        fun updateCard(card: Card) {
            val rotationParams = if (card.isFlipped) {
                Triple(0f, 180f, card.id)
            } else {
                Triple(180f, 0f, R.drawable.coins)
            }

            binding.cardImage.apply {
                cardAnimator.rotateCard(
                    this,
                    rotationParams.first,
                    rotationParams.second,
                    onAnimationHalfway = { setIconResource(rotationParams.third) },
                    onAnimationEnd = { isEnabled = !card.isFlipped }
                )
            }
        }
    }
}
