package by.godevelopment.rsshool2021androidtaskstorage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.godevelopment.rsshool2021androidtaskstorage.adapter.CatAdapter
import by.godevelopment.rsshool2021androidtaskstorage.database.SqlBox
import by.godevelopment.rsshool2021androidtaskstorage.databinding.FragmentFirstBinding
import by.godevelopment.rsshool2021androidtaskstorage.entity.Cat
import by.godevelopment.rsshool2021androidtaskstorage.entity.OrderType
import com.google.android.material.snackbar.Snackbar

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!  // This property is only valid between onCreateView and onDestroyView.

    private lateinit var dataList: List<Cat>
    private lateinit var catAdapter: CatAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup()
    }

    private fun setup() {
        setupDataList()
        setupRecyclerView()
    }

    private fun setupRecyclerView() {

        // TODO "Обернуть в скоуп функцию"
//        val linearLayoutManager = LinearLayoutManager(context)
        //        val recyclerView = binding.recyclerView
//        recyclerView.layoutManager = linearLayoutManager
//        recyclerView.adapter = catAdapter

        catAdapter = CatAdapter { position ->
            myActionClick(dataList[position])
        }.apply { setDataList(dataList) }

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = catAdapter
        }

    }

    private fun myActionClick(cat: Cat) {
        Snackbar.make(binding.root, "Выбран котик: ${cat.name}", Snackbar.LENGTH_LONG)
            .setAction("Удалить котика") {
                // Responds to click on the action
                SqlBox.catProducerWithSql.deleteCatInDataBase(cat.id)
                // TODO "обновить адаптер"
                setupDataList()
                catAdapter.setDataList(dataList)
            }
            .show()
    }

    private fun setupDataList() {
        dataList = when (SqlBox.orderList) {
            OrderType.ID -> SqlBox.catProducerWithSql.getListOfCats()
            OrderType.NAME -> SqlBox.catProducerWithSql.getSortedListOfCats(OrderType.NAME)
            OrderType.AGE -> SqlBox.catProducerWithSql.getSortedListOfCats(OrderType.AGE)
            OrderType.BREED -> SqlBox.catProducerWithSql.getSortedListOfCats(OrderType.BREED)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}