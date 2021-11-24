package com.example.latansa_book

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Adapter
import androidx.recyclerview.widget.RecyclerView
import com.example.latansa_book.data.ModelBuku
import com.example.latansa_book.data.ModelUser
import com.example.latansa_book.databinding.ItemHomeBinding

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
        fun initData(modelUser: ModelBuku) {

        }
    }
}