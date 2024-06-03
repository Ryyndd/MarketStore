package com.example.marketstore.main

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Toast
import com.example.marketstore.R
import com.example.marketstore.auth.Constant
import com.example.marketstore.auth.LoginActivity
import com.example.marketstore.auth.SharedPreferencesHelper
import com.example.marketstore.databinding.FragmentProfileBinding
import java.util.zip.Inflater


class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding

    lateinit var preferences: SharedPreferencesHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    private fun moveToLogin() {
        startActivity(Intent(requireContext(), LoginActivity::class.java))
        requireActivity().finish()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentProfileBinding.inflate(inflater, container, false)

        preferences = SharedPreferencesHelper(requireContext())

        binding.txtUsername.text = preferences.getString(Constant.PREF_USERNAME)

        binding.btnLogout.setOnClickListener{
            dialogLogout()
        }

        return binding.root
    }

    private fun dialogLogout() {
        val dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE) // Ubah ke FEATURE_NO_TITLE
        dialog.setContentView(R.layout.dialog_logout)
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

        val btnNo = dialog.findViewById<View>(R.id.btnNo)
        btnNo.setOnClickListener {
            dialog.dismiss()
        }
        val btnYes = dialog.findViewById<View>(R.id.btnYes)
        btnYes.setOnClickListener {
            preferences.clear()
            moveToLogin()
        }

        dialog.show()
    }

}