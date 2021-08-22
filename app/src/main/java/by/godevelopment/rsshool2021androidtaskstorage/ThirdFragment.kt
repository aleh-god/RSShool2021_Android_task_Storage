package by.godevelopment.rsshool2021androidtaskstorage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation.findNavController
import by.godevelopment.rsshool2021androidtaskstorage.database.SqlBox
import by.godevelopment.rsshool2021androidtaskstorage.databinding.FragmentThirdBinding
import by.godevelopment.rsshool2021androidtaskstorage.entity.Cat
import by.godevelopment.rsshool2021androidtaskstorage.entity.OrderType
import com.google.android.material.snackbar.Snackbar

class ThirdFragment : Fragment() {

    private var _binding: FragmentThirdBinding? = null

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentThirdBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup()
    }

    private fun setup() {
        binding.headerOrder.text = "Select you order sort."

        binding.nameOrder.setOnClickListener { chekButtonAndAction(it) }
        binding.ageOrder.setOnClickListener { chekButtonAndAction(it) }
        binding.breedOrder.setOnClickListener { chekButtonAndAction(it) }
        binding.resetOrder.setOnClickListener { chekButtonAndAction(it) }
    }

    private fun chekButtonAndAction(v: View) {
        when (v.id) {
            binding.nameOrder.id -> SqlBox.orderList = OrderType.NAME
            binding.ageOrder.id -> SqlBox.orderList = OrderType.AGE
            binding.breedOrder.id -> SqlBox.orderList = OrderType.BREED
            binding.resetOrder.id -> SqlBox.orderList = OrderType.ID
        }
        findNavController(v).navigate(R.id.action_ThirdFragment_to_FirstFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}