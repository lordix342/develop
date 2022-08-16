package com.chi.test.lvl2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.chi.test.lvl2.databinding.FragmentBlankBinding

class BlankFragment : Fragment() {
    private lateinit var binding : FragmentBlankBinding
    private val viewM: ViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBlankBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewM.student.observe(viewLifecycleOwner) {
            if (it!=null) {
                binding.textAge1.text = it.age.toString()
                binding.textName1.text = it.name
                binding.checkBox2.isChecked = it.isStudent
            }
        }
    }
}