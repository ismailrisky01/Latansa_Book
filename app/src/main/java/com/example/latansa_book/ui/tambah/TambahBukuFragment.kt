package com.example.latansa_book.ui.tambah

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.latansa_book.R
import com.example.latansa_book.data.ModelBuku
import com.example.latansa_book.data.ModelReview
import com.example.latansa_book.data.ViewModel
import com.example.latansa_book.databinding.FragmentHomeBinding
import com.example.latansa_book.databinding.FragmentTambahBukuBinding
import com.example.latansa_book.util.log
import com.google.firebase.storage.FirebaseStorage
import java.util.*


class TambahBukuFragment : Fragment() {

    private var _binding: FragmentTambahBukuBinding? = null
    private val binding get() = _binding!!
    var selectedPhotoUri: Uri? = null
    var modelBarangGlobal: ModelBuku = ModelBuku("","","","","","","0")
    private val mViewModel by lazy {
        ViewModelProvider(this).get(ViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentTambahBukuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.IDTambahButtonUpload.setOnClickListener {
            getImage()
            log("kirim data")
            Log.d("latansarpl","upload")
        }
        binding.IDTambahButtonSimpan.setOnClickListener {
            cekData()
            log("kirim data")
        }
    }
    fun cekData() {
        log("kirim data")
          val judul = binding.IDTambahJudul.text.toString()
            val kategori = binding.IDTambahKategori.text.toString()
            val pengarang = binding.IDTambahPengarang.text.toString()
            val tahunTerbit = binding.IDTambahTahunTerbit.text.toString()
            val penerbit = binding.IDTambahPenerbit.text.toString()
if(selectedPhotoUri!=null){
    uploadImage(selectedPhotoUri).observe(viewLifecycleOwner,  {
        mViewModel.setDataBuku(requireContext(),
            ModelBuku("", judul, kategori, pengarang,tahunTerbit,penerbit,it)
        )
    })
}


    }

    private fun uploadImage(imageUri: Uri?): MutableLiveData<String> {

        log("coba upload")
        val filename = UUID.randomUUID().toString()
        val storage = FirebaseStorage.getInstance().getReference("/RPL/$filename")
        var uri = MutableLiveData<String>()
        storage.putFile(imageUri!!).addOnSuccessListener {
            log("berhasil upload")
            storage.downloadUrl.addOnSuccessListener {
                uri.value = it.toString()

            }.addOnFailureListener {
                log(it.localizedMessage)

            }

        }
        return uri

    }

    fun getImage() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
        startActivityForResult(intent, 100)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == 100 && data != null) {
            selectedPhotoUri = data.data
            val bitmap = MediaStore.Images.Media.getBitmap(
                requireContext().contentResolver,
                selectedPhotoUri
            )
            binding.IDTambahTxtLokasi.text = data.data.toString()
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding= null
    }
}