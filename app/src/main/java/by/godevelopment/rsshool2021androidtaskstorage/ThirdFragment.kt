package by.godevelopment.rsshool2021androidtaskstorage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import by.godevelopment.rsshool2021androidtaskstorage.databinding.FragmentThirdBinding
import by.godevelopment.rsshool2021androidtaskstorage.entity.Cat

class ThirdFragment : Fragment() {

    private var _binding: FragmentThirdBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var dataList: List<Cat>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentThirdBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup()
    }

    private fun setup() {
        binding.headerFilter.text = "Select you order sort."
        binding.NameFilter.setOnClickListener(
            fun(v: View) {
                findNavController(v).navigate(R.id.action_ThirdFragment_to_FirstFragment)
                // Do something in response to button click
            }
        )
        binding.AgeFilter.setOnClickListener(
            fun(v: View) {
                findNavController(v).navigate(R.id.action_ThirdFragment_to_FirstFragment)
                // Do something in response to button click
            }
        )
        binding.BreedFilter.setOnClickListener(
            fun(v: View) {
                findNavController(v).navigate(R.id.action_ThirdFragment_to_FirstFragment)
                // Do something in response to button click
            }
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}