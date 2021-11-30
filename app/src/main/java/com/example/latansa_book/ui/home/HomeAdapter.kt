package com.example.latansa_book.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.latansa_book.R
import com.example.latansa_book.data.ModelBuku
import com.example.latansa_book.databinding.ItemHomeBinding
import com.squareup.picasso.Picasso

class HomeAdapter() : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {
    private var dataAdapter = mutableListOf<ModelBuku>()
    fun setData(data: MutableList<ModelBuku>) {
        dataAdapter = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemHomeBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = dataAdapter[position]
        holder.initData(data)
    }

    override fun getItemCount(): Int {
        return dataAdapter.size
    }

    class ViewHolder(val binding: ItemHomeBinding) : RecyclerView.ViewHolder(binding.root) {
        fun initData(modelBuku: ModelBuku) {
            binding.IDItemHomeJudul.text = modelBuku.judulBuku
            binding.IDItemHomeReview.text = modelBuku.pengarang
            Picasso.get().load(modelBuku.fotoBuku).into(binding.imageView2)
            binding.IDItemHomeCardview.setOnClickListener{
                val bundle= Bundle()
                bundle.putString("idBuku", modelBuku.idBuku)
                it.findNavController().navigate(R.id.action_homeFragment_to_detailBukuFragment, bundle)
            }

        }
    }
}