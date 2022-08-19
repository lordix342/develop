package com.chi.test.lvl3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.chi.test.lvl3.adapters.ClickListener
import com.chi.test.lvl3.adapters.ZooAdapter
import com.chi.test.lvl3.databinding.FragmentFavoriteBinding
import com.chi.test.lvl3.models.ModelZooItem


class FavoriteFragment : Fragment(), ClickListener {
    private lateinit var binding : FragmentFavoriteBinding
    private val viewModel : ZooViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getFavorites()
        val adapter = ZooAdapter(requireContext(), this)
        viewModel.zooItemFavorites.observe(viewLifecycleOwner) {
            if (it!=null) {
                binding.rcFavorites.adapter = adapter
                binding.rcFavorites.layoutManager = LinearLayoutManager(requireContext())
                adapter.setData(it)
            }
        }
    }

    override fun onCheck(zooItem: ModelZooItem) {
        viewModel.updateDb(zooItem)
    }
}