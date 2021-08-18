package by.godevelopment.rsshool2021androidtaskstorage

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.godevelopment.rsshool2021androidtaskstorage.adapter.CatAdapter
import by.godevelopment.rsshool2021androidtaskstorage.database.CatProducerWithSql
import by.godevelopment.rsshool2021androidtaskstorage.database.CatProvider
import by.godevelopment.rsshool2021androidtaskstorage.database.ContractDB
import by.godevelopment.rsshool2021androidtaskstorage.databinding.FragmentFirstBinding
import by.godevelopment.rsshool2021androidtaskstorage.entity.Cat
import com.google.android.material.snackbar.Snackbar

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var dataList: List<Cat>

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
//        val catAdapter = CatAdapter { position ->
//            myActionClick(dataList[position])
//        }
//        val recyclerView = binding.recyclerView
//        recyclerView.layoutManager = linearLayoutManager
//        recyclerView.adapter = catAdapter

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = CatAdapter { position ->
                myActionClick(dataList[position])
            }
        }

    }

    private fun myActionClick(cat: Cat) {
        Snackbar.make(binding.root, "Выбран котик: ${cat.name}", Snackbar.LENGTH_LONG)
            .setAction("Удалить котика") {
                // Responds to click on the action
            }
            .show()
    }

    private fun setupDataList() {

        dataList = CatProvider.catsListSQL
        Log.i(CatProvider.TAG, "getListOfCats() ${dataList.size}")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}