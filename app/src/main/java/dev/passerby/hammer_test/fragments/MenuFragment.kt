package dev.passerby.hammer_test.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.ahmadhamwi.tabsync.TabbedListMediator
import dev.passerby.hammer_test.Category
import dev.passerby.hammer_test.R
import dev.passerby.hammer_test.adapters.CategoriesAdapter
import dev.passerby.hammer_test.adapters.PizzaListAdapter
import dev.passerby.hammer_test.databinding.FragmentMenuBinding
import dev.passerby.hammer_test.viewmodels.MenuViewModel


class MenuFragment : Fragment() {

    private var _binding: FragmentMenuBinding? = null
    private val binding: FragmentMenuBinding
        get() = _binding ?: throw RuntimeException("FragmentMenuBinding is null")

    private val viewModel by lazy {
        ViewModelProvider(this)[MenuViewModel::class.java]
    }

    private lateinit var pizzaListAdapter: PizzaListAdapter
    private lateinit var categoriesAdapter: CategoriesAdapter
    private var categoriesList = mutableListOf<Category>()
    private val categories = listOf(
        getString(R.string.pizza_category_name),
        getString(R.string.combo_category_name),
        getString(R.string.desserts_category_name),
        getString(R.string.drinks_category_name)
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
        initTabLayout()
        initMediator()

        viewModel.pizzaList.observe(viewLifecycleOwner) {
            for (i in categories.indices) {
                categoriesList.add(Category(categories[i], it, i))
            }
            categoriesAdapter.submitList(categoriesList)
        }
    }

    private fun initMediator() {
        TabbedListMediator(
            binding.recyclerView,
            binding.tabLayout,
            categories.indices.toList(),
            true
        ).attach()
    }

    private fun initTabLayout() {
        for (category in categories) {
            binding.tabLayout.addTab(binding.tabLayout.newTab().setText(category))
        }
    }


    private fun initRecycler() {
        pizzaListAdapter = PizzaListAdapter(requireContext())
        categoriesAdapter = CategoriesAdapter(requireContext())
        binding.recyclerView.adapter = categoriesAdapter
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}