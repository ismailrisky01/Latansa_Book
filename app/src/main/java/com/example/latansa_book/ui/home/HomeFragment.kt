package com.example.latansa_book.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.latansa_book.R
import com.example.latansa_book.data.ModelBuku
import com.example.latansa_book.data.ModelReview
import com.example.latansa_book.data.ViewModel
import com.example.latansa_book.databinding.FragmentHomeBinding
import com.example.latansa_book.databinding.FragmentLoginBinding


class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val mViewModel by lazy {
        ViewModelProvider(this).get(ViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
   mViewModel.getDataBuku().observe(viewLifecycleOwner, Observer {
       val adapter = HomeAdapter()
       binding.IDHomeRecyclerview.layoutManager = GridLayoutManager(requireContext(), 2)
       binding.IDHomeRecyclerview.adapter = adapter
       adapter.setData(it)
   })


    }

    override fun onDestroy() {
        super.onDestroy()
        _binding= null
    }
}