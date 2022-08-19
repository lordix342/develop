package com.chi.test.lvl2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.chi.test.lvl2.databinding.FragmentNewStudentBinding
import com.chi.test.lvl2.room.ModelStudent


class NewStudentFragment : Fragment() {
    private lateinit var binding : FragmentNewStudentBinding
    private val viewModel: ViewModel by viewModels()
    private var date = "2000/1/1"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewStudentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.successInsert.value = false
        binding.datePicker1.init(
            2000,
            0,
            0
        ) { _,year,monthP,dayP ->
            val month = monthP+1
            val day = dayP+1
            date = "$year/$month/$day"
        }
        binding.button.setOnClickListener {
            insertStudent()
        }
    }

    private fun insertStudent() {
        val name = binding.editTextName.text.toString()
        val ageString = binding.editAge.text.toString()

        if (name == "") Toast.makeText(requireContext(), "Enter name", Toast.LENGTH_SHORT)
            .show() else {
            if (ageString == "") Toast.makeText(
                requireContext(),
                "Enter name",
                Toast.LENGTH_SHORT
            ).show() else {
                val age = ageString.toInt()
                viewModel.insertStudent(
                    ModelStudent(
                        null,
                        name,
                        age,
                        binding.checkBoxEditStudent.isChecked,
                        date,
                        binding.editDescription.text.toString()
                    )
                )
            }
        }
    }
}

