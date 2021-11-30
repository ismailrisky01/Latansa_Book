package com.example.latansa_book

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.latansa_book.data.ViewModel
import com.example.latansa_book.databinding.FragmentDetailBukuBinding
import com.example.latansa_book.databinding.FragmentLoginBinding
import com.example.latansa_book.util.log
import com.squareup.picasso.Picasso


class DetailBukuFragment : Fragment() {
    private var _binding: FragmentDetailBukuBinding? = null
    private val binding get() = _binding!!
    private val mViewModel by lazy {
        ViewModelProvider(this).get(ViewModel::class.java)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDetailBukuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.IDDetailConstraintTambah.visibility = View.GONE
        val idBuku=requireArguments().getString("idBuku").toString()
        log(idBuku)
        mViewModel.getDataBuku().observe(viewLifecycleOwner,{
            val data=it.filter {
                it.idBuku==idBuku
            }


            binding.IDDetailNamaBuku.text = data[0].judulBuku
            binding.IDDetailPengarang.text = data[0].pengarang
            binding.IDDetailKategori.text = data[0].kategori
            binding.IDDetailTahunTerbit.text = data[0].tahunPenerbit
            binding.IDDetailPenerbit.text = data[0].penerbit
            Picasso.get().load(data[0].fotoBuku).into(binding.IDDetailFotoBuku)

        })

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding= null
    }
}