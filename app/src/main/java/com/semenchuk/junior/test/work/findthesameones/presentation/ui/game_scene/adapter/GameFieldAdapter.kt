package com.semenchuk.junior.test.work.findthesameones.presentation.ui.game_scene.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.semenchuk.junior.test.work.findthesameones.databinding.GameCellBinding
import com.semenchuk.junior.test.work.findthesameones.presentation.models.Card

class GameFieldAdapter(
    private val onCardClickListener: (Int) -> Unit,
) : RecyclerView.Adapter<GameFieldAdapter.CardHolder>() {

    private var cards = listOf<Card>()

    fun submitData(data: List<Card>) {
        cards = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = GameCellBinding.inflate(inflater, parent, false)
        return CardHolder(binding)
    }

    override fun getItemCount(): Int = cards.size

    override fun onBindViewHolder(holder: CardHolder, position: Int) {
        val card = cards[position]
        holder.bind(card)
    }

    inner class CardHolder(private val binding: GameCellBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(card: Card) {
            if (card.isFlipped) {
                binding.cardImage.setIconResource(card.id)
            } else {
                binding.cardImage.setIconResource(0)
            }

            binding.cardImage.setOnClickListener {
                onCardClickListener.invoke(adapterPosition)
            }
        }
    }
}
