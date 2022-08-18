package com.chi.test.lvl3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import com.chi.test.lvl3.databinding.FragmentMainBinding
import com.google.android.material.tabs.TabLayoutMediator


class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    private val fragmentList = listOf(BlankFragment(), FavoriteFragment())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewPager()
    }
    private fun initViewPager() {
        val tableList =
            listOf(resources.getString(R.string.zoo), resources.getString(R.string.favorites))
        val adapterPager = PageAdapter(activity as FragmentActivity,
            list = fragmentList as List<Fragment>)
        binding.pager.adapter = adapterPager
        TabLayoutMediator(binding.tabLayout, binding.pager) { tabItem, position ->
            tabItem.text = tableList[position]
        }.attach()
    }
}