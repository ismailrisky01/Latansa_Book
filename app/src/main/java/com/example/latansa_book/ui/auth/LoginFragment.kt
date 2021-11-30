package com.example.latansa_book.ui.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.latansa_book.R
import com.example.latansa_book.databinding.FragmentLoginBinding
import com.example.latansa_book.util.log
import com.google.firebase.auth.FirebaseAuth



class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
    _binding = FragmentLoginBinding.inflate(inflater, container, false)
    return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.IDLoginBtnLogin.setOnClickListener{
            val user = binding.IDLoginEmail.text.toString()
            val pass = binding.IDLoginPassword.text.toString()
            FirebaseAuth.getInstance().signInWithEmailAndPassword(user, pass).addOnCompleteListener {
                if (it.isSuccessful) {
                    log("Berhasil")
                    findNavController().navigate(R.id.action_loginFragment_to_homeFragment)

                } else {
                    log("failed")
                    Toast.makeText(context, "${it.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }.addOnFailureListener {
                Toast.makeText(context, "${it.message}", Toast.LENGTH_SHORT).show()

            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding= null
    }
}