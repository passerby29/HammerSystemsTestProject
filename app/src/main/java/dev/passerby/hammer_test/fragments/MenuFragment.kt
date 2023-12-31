package dev.passerby.hammer_test.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ahmadhamwi.tabsync.TabbedListMediator
import dev.passerby.hammer_test.Category
import dev.passerby.hammer_test.R
import dev.passerby.hammer_test.adapters.CategoriesAdapter
import dev.passerby.hammer_test.databinding.FragmentMenuBinding
import dev.passerby.hammer_test.viewmodels.MenuViewModel


class MenuFragment : Fragment() {

    private var _binding: FragmentMenuBinding? = null
    private val binding: FragmentMenuBinding
        get() = _binding ?: throw RuntimeException("FragmentMenuBinding is null")

    private val viewModel by lazy {
        ViewModelProvider(this)[MenuViewModel::class.java]
    }

    private lateinit var categoriesAdapter: CategoriesAdapter
    private var categoriesList = mutableListOf<Category>()
    private var categories = listOf<String>()

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
        categories = listOf(
            getString(R.string.pizza_category_name),
            getString(R.string.combo_category_name),
            getString(R.string.desserts_category_name),
            getString(R.string.drinks_category_name)
        )

        initRecycler()
        initTabLayout()
        initMediator()

        viewModel.pizzaList.observe(viewLifecycleOwner) {
            for (i in categories.indices) {
                if (it.isNotEmpty()) {
                    categoriesList.add(Category(categories[i], it, i))

                }
            }
            categoriesAdapter.submitList(categoriesList.toList())
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
        categoriesAdapter = CategoriesAdapter(requireContext())
        binding.recyclerView.apply {
            adapter = categoriesAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}