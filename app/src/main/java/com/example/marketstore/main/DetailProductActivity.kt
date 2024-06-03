package com.example.marketstore.main

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.marketstore.R
import com.example.marketstore.databinding.ActivityDetailProductBinding
import com.example.marketstore.model.DataProduct
import com.example.marketstore.model.Product

class DetailProductActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailProductBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

       // val productList = DataProduct()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val Products = intent.getParcelableExtra<Product>(Product.INTENT_PARCELABLE)

        binding.imageProduct.setImageResource(Products?.image!!)
        binding.titleProduct.text = Products.title
        binding.txtDescription.text = Products.description
        binding.priceProduct.text = Products.price.toString()

        binding.btnCart.setOnClickListener {
            dialogAddtoCart()
        }

        binding.btnCheckout.setOnClickListener {
            dialogCheckout()
        }

        binding.btnBack.setOnClickListener {
            onSupportNavigateUp()
        }

        binding.btnShare.setOnClickListener{
                val t1 = binding.titleProduct.text.toString()
                val shareIntent = Intent()
                shareIntent.action = Intent.ACTION_SEND
                shareIntent.type="text/plain"
                shareIntent.putExtra(Intent.EXTRA_TEXT, t1)
                startActivity(Intent.createChooser(shareIntent,"Share via"))
        }


    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
    companion object {
        val INTENT_PARCELABLE = "OBJECT_INTENT"
    }
    private fun dialogCheckout() {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE) // Ubah ke FEATURE_NO_TITLE
        dialog.setContentView(R.layout.dialog_checkout)
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

        val btnClose = dialog.findViewById<View>(R.id.btnClose)
        btnClose.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }
    private fun dialogAddtoCart() {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE) // Ubah ke FEATURE_NO_TITLE
        dialog.setContentView(R.layout.dialog_addtocart)
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

        val btnClose = dialog.findViewById<View>(R.id.btnClose)
        btnClose.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }

}