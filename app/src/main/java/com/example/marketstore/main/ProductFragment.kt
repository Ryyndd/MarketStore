package com.example.marketstore.main

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.marketstore.R
import com.example.marketstore.controller.rvProductAdapter
import com.example.marketstore.databinding.FragmentProductBinding
import com.example.marketstore.model.CharCategory
import com.example.marketstore.model.DataProduct
import com.example.marketstore.model.Product
import java.util.Locale


class ProductFragment : Fragment() {

    private lateinit var binding: FragmentProductBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_product, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentProductBinding.bind(view)
        binding.rvProductHorizontal.layoutManager = GridLayoutManager(context,2)
        val productList = DataProduct()
       binding.rvProductHorizontal.adapter = rvProductAdapter(productList ,::onItemClick)


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

        binding.btnAll.setOnClickListener {
            buttonAll()
        }

        binding.btnSmartphones.setOnClickListener {
            filterList(CharCategory.SMARTPHONE)
        }
        binding.btnLaptops.setOnClickListener {
            filterList(CharCategory.LAPTOP)
        }
        binding.btnComputers.setOnClickListener {
            filterList(CharCategory.COMPUTER)
        }

        val rvAdapter = rvProductAdapter(productList) {
            val intent = Intent(context, DetailProductActivity::class.java)
            intent.putExtra(HomeFragment.INTENT_PARCELABLE, it)
            startActivity(intent)
        }


        binding.rvProductHorizontal.adapter = rvAdapter

    }

    private fun filterList(category: CharCategory) {
        val productData = DataProduct()
        val filteredList = productData.filter { it.kategory == category }
       binding.rvProductHorizontal.adapter = rvProductAdapter(filteredList, ::onItemClick)
        if (filteredList.isEmpty()) {
            Toast.makeText(requireContext(), "Data tidak ditemukan", Toast.LENGTH_SHORT).show()
        }
    }

    private fun filterSearch(query: String) {
        val productData = DataProduct()
        val filteredList = productData.filter { it.title.lowercase(Locale.ROOT).contains(query.lowercase(
            Locale.ROOT)) }
        if (filteredList.isEmpty()) {
            Toast.makeText(requireContext(), "Data tidak ditemukan", Toast.LENGTH_SHORT).show()
        } else {
            (binding.rvProductHorizontal.adapter as? rvProductAdapter)?.setFilteredList(filteredList)
        }
    }

    private fun onItemClick(product: Product) {
        val intent = Intent(context, DetailProductActivity::class.java)
        intent.putExtra(HomeFragment.INTENT_PARCELABLE, product)
        startActivity(intent)
    }

    private val  original = DataProduct()

    private fun buttonAll() {
        binding.rvProductHorizontal.adapter = rvProductAdapter(original, ::onItemClick)
    }


}
