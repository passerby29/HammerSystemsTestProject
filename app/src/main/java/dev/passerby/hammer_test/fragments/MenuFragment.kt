package dev.passerby.hammer_test.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
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
        "Category1", "Category2",
        "Category1", "Category2",
        "Category1", "Category2",
        "Category1", "c",
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
        viewModel.pizzaList.observe(viewLifecycleOwner) {
            categoriesList = mutableListOf(
                Category(
                    "Пицца",
                    it,
                    pos = 1
                ),
                Category(
                    "Остальное",
                    it,
                    pos = 2
                ),
            )
            categoriesAdapter.submitList(categoriesList)
        }
        initTabLayout()
        initMediator()
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
//            for (i in categories.indices) {
//                binding.tabLayout.getTabAt(i)?.customView = prepareTabView(i)
//            }
//            binding.tabLayout.setSelectedTabIndicatorColor(
//                ContextCompat.getColor(
//                    requireContext(),
//                    R.color.color_navigation_icons
//                )
//            )
//            binding.tabLayout.setTabTextColors(R.color.white, 0)
        }
    }


    private fun prepareTabView(pos: Int): View {
        val view: View = layoutInflater.inflate(R.layout.item_custom_tab, null)
        val tv_title = view.findViewById<TextView>(R.id.txt)
        tv_title.text = categories[pos]
        return view
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