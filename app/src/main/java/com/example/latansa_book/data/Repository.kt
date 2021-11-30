package com.example.latansa_book.data

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.latansa_book.util.log
import com.google.firebase.firestore.FirebaseFirestore


class Repository {
    fun setDataBuku(context: Context, data: Any) {
        log("repo send")
        data as ModelBuku
        val reff = FirebaseFirestore.getInstance().collection("Buku").document()
        val dataBuku = ModelBuku(
            reff.id,
            data.judulBuku,
            data.kategori,
            data.pengarang,
            data.tahunPenerbit,
            data.penerbit,
            data.fotoBuku
        )
        reff.set(dataBuku).addOnSuccessListener {
            Toast.makeText(context, "Success to set Data Firestore", Toast.LENGTH_SHORT).show()
            log("berhasil repo")
        }.addOnFailureListener{
            log("failed")
            Toast.makeText(context, "${it.message}", Toast.LENGTH_SHORT).show()
        }
    }

    fun getDataBuku(): LiveData<MutableList<ModelBuku>> {
        val mutableList = MutableLiveData<MutableList<ModelBuku>>()
        FirebaseFirestore.getInstance().collection("Buku").get().addOnSuccessListener {
            val data = mutableListOf<ModelBuku>()
            it.forEach {
                val listData = it.toObject(ModelBuku::class.java)
                data.add(listData)
            }
            mutableList.value=data
        }
        return mutableList

    }

}