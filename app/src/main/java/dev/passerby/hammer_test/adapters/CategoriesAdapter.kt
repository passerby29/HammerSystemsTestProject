package dev.passerby.hammer_test.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import dev.passerby.hammer_test.Category
import dev.passerby.hammer_test.callbacks.CategoryDiffCallback
import dev.passerby.hammer_test.databinding.ItemCategoryBinding
import dev.passerby.hammer_test.viewholders.CategoryViewHolder

class CategoriesAdapter(private val context: Context) :
    ListAdapter<Category, CategoryViewHolder>(CategoryDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val itemView =
            ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val item = getItem(position)
        val pizzaAdapter = PizzaListAdapter(context)

        holder.binding.recyclerView.adapter = pizzaAdapter

        pizzaAdapter.submitList(item.items)
    }
}