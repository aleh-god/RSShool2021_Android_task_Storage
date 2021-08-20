package by.godevelopment.rsshool2021androidtaskstorage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.godevelopment.rsshool2021androidtaskstorage.databinding.FragmentWaitBinding

class WaitFragment : Fragment() {
    private var _binding: FragmentWaitBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWaitBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.wait.text = "Please wait!"
        findNavController().navigate(R.id.action_WaitFragment_to_FirstFragment)

    }
}