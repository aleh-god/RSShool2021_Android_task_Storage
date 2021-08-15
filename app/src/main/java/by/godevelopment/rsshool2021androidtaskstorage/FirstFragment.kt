package by.godevelopment.rsshool2021androidtaskstorage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.godevelopment.rsshool2021androidtaskstorage.adapter.CatAdapter
import by.godevelopment.rsshool2021androidtaskstorage.database.CatProvider
import by.godevelopment.rsshool2021androidtaskstorage.databinding.FragmentFirstBinding
import by.godevelopment.rsshool2021androidtaskstorage.entity.Cat

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

        val recyclerView = binding.recyclerView
        val linearLayoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = linearLayoutManager

        // TODO "Обернуть в скоуп функцию"
        val adapter = CatAdapter { position ->
            myActionClick(dataList[position])
        }
        recyclerView.adapter = adapter

    }

    private fun myActionClick(cat: Cat) {
        Toast.makeText(
            context,
            "Выбран котик: ${cat.name}", Toast.LENGTH_SHORT
        ).show()
    }

    private fun setupDataList() {
        dataList = CatProvider.catsList
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}