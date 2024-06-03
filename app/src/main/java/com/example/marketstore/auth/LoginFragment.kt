package com.example.marketstore.auth

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.marketstore.R
import com.example.marketstore.databinding.FragmentLoginBinding
import com.example.marketstore.main.MainActivity

class LoginFragment : Fragment() {

    // Binding Fragment Login
    private lateinit var binding: FragmentLoginBinding

    lateinit var preferences : SharedPreferencesHelper

    //Username & Password
    private var username = "nando" //+ "triani" + "yola" + "ikhsan"
    private var password = "nando" // + "triani" + "yola" + "ikhsan"

    private val userAccount = mapOf(
        "nando" to "nando",
        "triani" to "triani",
        "yola" to "yola",
        "ikhsan" to "ikhsan"
    )


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Insialisasi binding ke inflater
        binding = FragmentLoginBinding.inflate(inflater, container, false)

        // Insialisasi SharePrefrences
        preferences = SharedPreferencesHelper(requireContext())

        // Action Login Butoon ke fragment Success
        binding.btnLogin.setOnClickListener {
            val inputUsername = binding.txtUsername.text?.toString()?.trim()
            val inputPassword = binding.txtPassword.text?.toString()?.trim()

            // Validasi Username & Password Satu Value
           // if ( inputUsername == username && inputPassword == password ) {

            // Validasi dengan Map untuk banyak value
                if ( userAccount[inputUsername] == inputPassword ) {
                    // penulisan / pemanggilan data yang di ambil dari txtUsername
                    preferences.putString(Constant.PREF_USERNAME, inputUsername.toString())
                    // penulisan / pemanggilan data yang di ambil dari txtPassword
                    preferences.putString(Constant.PREF_PASSWORD, inputPassword.toString() )
                    // data terkirim bahwa user login
                    preferences.putBoolean(Constant.PREF_USER_LOGIN, true)
                    it.findNavController().navigate(R.id.action_loginFragment_to_loginSuccesFragment)
                } else {
                    // data tidak terkirim
                    preferences.putBoolean(Constant.PREF_USER_LOGIN, false)
                    it.findNavController().navigate(R.id.action_loginFragment_to_loginFailedFragment)
                }
            }

        return binding.root

    }

    // Life cycle onStart Ketika dikbuka
    override fun onStart() {
        super.onStart()
        if (preferences.getBoolean(Constant.PREF_USER_LOGIN)) {
            startActivity(Intent(requireContext(), MainActivity::class.java))
            requireActivity().finish()
        }
    }

}