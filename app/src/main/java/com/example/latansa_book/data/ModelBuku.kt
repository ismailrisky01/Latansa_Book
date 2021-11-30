package com.example.latansa_book.data

class ModelBuku(val idBuku: String, val judulBuku: String, val kategori:String, val pengarang: String,
                val tahunPenerbit: String, val penerbit: String,  val fotoBuku:String) {
constructor():this("","","","","","","0")
}