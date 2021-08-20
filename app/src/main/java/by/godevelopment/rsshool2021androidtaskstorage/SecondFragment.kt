package by.godevelopment.rsshool2021androidtaskstorage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import by.godevelopment.rsshool2021androidtaskstorage.database.SqlBox
import by.godevelopment.rsshool2021androidtaskstorage.databinding.FragmentSecondBinding
import com.google.android.material.snackbar.Snackbar

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonSecond.setOnClickListener {
            val name = binding.Name.text
            val age = binding.Age.text.toString().toIntOrNull()
            val breed = binding.Breed.text
            var chek = true

            if (name.isNullOrBlank()) {
                binding.Name.error = "Даже у бездомного котика есть имя!"
                chek = false
            }
            if (breed.isNullOrBlank()) {
                binding.Breed.error = "У нас только породистые котики!"
                chek = false
            }
            val wtfChek = age != null && age in 1..33
            if (!wtfChek) {
                binding.Age.error = "Котики столько неживут!"
                chek = false
            }
            if (!chek) Snackbar.make(binding.root, "Заполните поля правильно.", Snackbar.LENGTH_LONG).show()
            else if (SqlBox.catProducerWithSql.insertCatInDataBase(name.toString(), age!!, breed.toString()))
                findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
            else Snackbar.make(binding.root, "Котик не хочет в базу данных. Попробуйте снова.", Snackbar.LENGTH_LONG).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}