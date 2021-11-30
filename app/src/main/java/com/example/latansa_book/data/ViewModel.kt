package com.example.latansa_book.data

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ViewModel(application: Application): AndroidViewModel(application) {
    private val repository: Repository = Repository()
    fun setDataBuku(context: Context, modelBarang: ModelBuku){
        viewModelScope.launch(Dispatchers.IO) {
            repository.setDataBuku(context, modelBarang)
        }
    }
    fun getDataBuku(): LiveData<MutableList<ModelBuku>> {
        return repository.getDataBuku()
    }
}
