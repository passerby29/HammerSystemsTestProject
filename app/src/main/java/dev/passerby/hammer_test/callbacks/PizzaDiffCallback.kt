package dev.passerby.hammer_test.callbacks

import androidx.recyclerview.widget.DiffUtil
import dev.passerby.domain.models.PizzaModel

class PizzaDiffCallback : DiffUtil.ItemCallback<PizzaModel>() {
    override fun areItemsTheSame(oldItem: PizzaModel, newItem: PizzaModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: PizzaModel, newItem: PizzaModel): Boolean {
        return oldItem == newItem
    }
}