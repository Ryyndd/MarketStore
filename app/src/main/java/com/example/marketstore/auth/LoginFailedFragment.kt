package com.example.marketstore.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.marketstore.R
import com.example.marketstore.databinding.FragmentLoginFailedBinding


class LoginFailedFragment : Fragment() {


    private lateinit var binding: FragmentLoginFailedBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginFailedBinding.inflate(inflater, container, false)

        binding.btnBackLogin.setOnClickListener{
            findNavController().navigate(R.id.action_loginFailedFragment_to_loginFragment)
        }

        return binding.root

    }

}