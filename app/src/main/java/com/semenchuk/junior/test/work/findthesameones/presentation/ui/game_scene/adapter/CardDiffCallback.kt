package com.semenchuk.junior.test.work.findthesameones.presentation.ui.game_scene.adapter

import androidx.recyclerview.widget.DiffUtil
import com.semenchuk.junior.test.work.findthesameones.presentation.models.Card

class CardDiffCallback(
    private val oldList: List<Card>,
    private val newList: List<Card>,
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        // Возвращаем payload только если содержимое элемента изменилось
        return if (oldList[oldItemPosition] != newList[newItemPosition]) {
            listOf(newList[newItemPosition])
        } else {
            null
        }
    }
}