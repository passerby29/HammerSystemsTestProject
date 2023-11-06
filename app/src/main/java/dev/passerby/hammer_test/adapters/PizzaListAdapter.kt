package dev.passerby.hammer_test.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.Glide
import dev.passerby.domain.models.PizzaModel
import dev.passerby.hammer_test.R
import dev.passerby.hammer_test.callbacks.PizzaDiffCallback
import dev.passerby.hammer_test.databinding.ItemPizzaBinding
import dev.passerby.hammer_test.viewholders.PizzaViewHolder

class PizzaListAdapter(private val context: Context) :
    ListAdapter<PizzaModel, PizzaViewHolder>(PizzaDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PizzaViewHolder {
        val itemView =
            ItemPizzaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PizzaViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PizzaViewHolder, position: Int) {
        val item = getItem(position)
        val binding = holder.binding
        with(binding) {
            Glide.with(context).load(item.imageUrl).into(pizzaMainImageView)
            pizzaNameTextView.text = item.title
            pizzaDescriptionTextView.text = item.sizes.toString()
            pizzaPriceButton.text =
                String.format(context.getString(R.string.pizza_price_placeholder), item.price)
        }
    }
}