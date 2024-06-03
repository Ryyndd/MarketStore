package com.example.marketstore.main

import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.marketstore.R
import com.example.marketstore.controller.rvProductAdapter
import com.example.marketstore.databinding.FragmentHomeBinding
import com.example.marketstore.model.CharCategory
import com.example.marketstore.model.DataProduct
import com.example.marketstore.model.Product
import java.util.Locale


class HomeFragment : Fragment() {

    private lateinit var binding : FragmentHomeBinding
    private lateinit var homeFragment: HomeFragment
    private lateinit var productFragment: ProductFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val context = requireContext()
        binding = FragmentHomeBinding.bind(view)
        binding.rvHomeVertical.layoutManager = GridLayoutManager(context,2)
        val productList = DataProduct()
        binding.rvHomeVertical.setHasFixedSize(true)

        homeFragment = HomeFragment()
        productFragment = ProductFragment()


        val rvAdapter = rvProductAdapter(productList) {
            val intent = Intent(context, DetailProductActivity::class.java)
            intent.putExtra(INTENT_PARCELABLE, it)
            startActivity(intent)
        }

        binding.btnAll.setOnClickListener {
            loadFragment(productFragment)
        }

        binding.tvCatergoriseall.setOnClickListener{
            loadFragment(productFragment)
        }
        binding.tvPopularSeeAll.setOnClickListener{
            loadFragment(productFragment)
        }
        binding.tvRecomendedSeeAll.setOnClickListener{
            loadFragment(productFragment)
        }

       binding.btnSmartphones.setOnClickListener{
           filterAndLoadFragment(CharCategory.SMARTPHONE, productFragment)
       }
        binding.btnLaptops.setOnClickListener{
            filterAndLoadFragment(CharCategory.LAPTOP, productFragment)
       }
        binding.btnComputers.setOnClickListener{
            filterAndLoadFragment(CharCategory.COMPUTER, productFragment)
       }

        binding.lyCart.setOnClickListener{
            Toast.makeText(context, "Fitur Belum Tersedia", Toast.LENGTH_SHORT).show()
        }

        binding.cvSearch.setOnClickListener {
            binding.cvSearch.isIconified = false
        }

        binding.cvSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener{

            override fun onQueryTextSubmit(query: String?): Boolean {

                return true
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                filterSearch(newText.toString())
                return true
            }
        })

        binding.cardsatu.setOnClickListener{
            customDialog()
        }
        binding.carddua.setOnClickListener{
            customDialog()
        }
        binding.cardtiga.setOnClickListener{
            customDialog()
        }
        binding.cardempat.setOnClickListener{
            customDialog()
        }
        binding.lyCart.setOnClickListener{
            dialogNotFound()
        }

        binding.rvHomeVertical.adapter = rvAdapter

    }

    companion object {
        val INTENT_PARCELABLE = "OBJECT_INTENT"
    }

    private fun loadFragment(fragment: Fragment) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.main, fragment)
            .commit()
    }


    private fun filterList(category: com.example.marketstore.model.CharCategory) {
        val productData = DataProduct()
        // fungsi filter untuk mencari data berdasarkan kategori
        val filteredList = productData.filter { it.kategory == category }
        binding.rvHomeVertical.adapter = rvProductAdapter(filteredList, ::onItemClick)
        if (filteredList.isEmpty()) {
            Toast.makeText(requireContext(), "Data tidak ditemukan", Toast.LENGTH_SHORT).show()
        }

    }

    private fun filterAndLoadFragment(category: com.example.marketstore.model.CharCategory, fragment: Fragment) {
        filterList(category)
        loadFragment(fragment)
    }


    private fun filterSearch(query: String) {
        val productData = DataProduct()
        // fungsi filter untuk mencari data berdasarkan query/ pencarian
        val filteredList = productData.filter { it.title.lowercase(Locale.ROOT).contains(query.lowercase(
            Locale.ROOT)) }
        if (filteredList.isEmpty()) {
            Toast.makeText(requireContext(), "Data tidak ditemukan", Toast.LENGTH_SHORT).show()
        } else {
            (binding.rvHomeVertical.adapter as? rvProductAdapter)?.setFilteredList(filteredList)
        }
    }

    private fun onItemClick(product: Product) {
        val intent = Intent(context, DetailProductActivity::class.java)
        intent.putExtra(INTENT_PARCELABLE, product)
        startActivity(intent)
    }

    private fun customDialog() {
        val dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE) // Ubah ke FEATURE_NO_TITLE
        dialog.setContentView(R.layout.dialog_product)
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

        val btnClose = dialog.findViewById<View>(R.id.btnClose)
        btnClose.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }
    private fun dialogNotFound() {
        val dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE) // Ubah ke FEATURE_NO_TITLE
        dialog.setContentView(R.layout.dialog_notfound)
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

        val btnClose = dialog.findViewById<View>(R.id.btnClose)
        btnClose.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }




}

