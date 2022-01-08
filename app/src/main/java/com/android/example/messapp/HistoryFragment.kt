package com.android.example.messapp

import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.example.messapp.databinding.FragmentHistoryBinding
import com.android.example.messapp.databinding.FragmentLoginBinding
import com.google.android.material.tabs.TabLayoutMediator


class HistoryFragment : Fragment() {

    private lateinit var binding: FragmentHistoryBinding
    private val viewModel by viewModels<DateViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHistoryBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as AppCompatActivity).supportActionBar?.show()
        (activity as AppCompatActivity).supportActionBar?.title = "History"

        val adapter = HistoryAdapter()
        binding.recyclerviewHistory.adapter = adapter
        binding.recyclerviewHistory.layoutManager = LinearLayoutManager((activity as AppCompatActivity).applicationContext as Application)
        viewModel.init((activity as AppCompatActivity).applicationContext as Application)
        viewModel.alldate.observe(viewLifecycleOwner) {
            adapter.setDates(it)
        }
    }
}