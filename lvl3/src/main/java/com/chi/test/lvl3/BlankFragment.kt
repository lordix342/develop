package com.chi.test.lvl3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.chi.test.lvl3.databinding.FragmentBlankBinding

class BlankFragment : Fragment() {
    private lateinit var binding : FragmentBlankBinding
    private val viewModel : ZooViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBlankBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getZoo()
        val adapter = ZooAdapter(requireContext())
        viewModel.zooItem.observe(viewLifecycleOwner) {
            if (it!=null) {
                binding.rcViewZoo.adapter = adapter
                binding.rcViewZoo.layoutManager = LinearLayoutManager(requireContext())
                adapter.setData(it)
            }
        }
    }
}