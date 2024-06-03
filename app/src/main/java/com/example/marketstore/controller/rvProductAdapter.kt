package com.example.marketstore.controller


import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.marketstore.R
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.marketstore.databinding.CardLayoutBinding
import com.example.marketstore.main.DetailProductActivity
import com.example.marketstore.main.HomeFragment
import com.example.marketstore.main.HomeFragmentDirections
import com.example.marketstore.main.MainActivity
import com.example.marketstore.model.Product


class rvProductAdapter(private var productList: List<Product>, val listener: (Product) -> Unit ) : RecyclerView.Adapter<rvProductAdapter.ProductViewHolder>() {

    class ProductViewHolder(val binding: CardLayoutBinding) : RecyclerView.ViewHolder(binding.root){
        val imageView : ImageView = binding.rvimage
        val title : TextView = binding.rvtitle
        val price : TextView = binding.rvprice
        val rating : TextView = binding.rvrating

        fun bindView(product: Product, listener: (Product) -> Unit){
            imageView.setImageResource(product.image)
            title.text = product.title
            price.text = product.price.toString()
            rating.text = product.rating.toString()
            itemView.setOnClickListener { listener(product) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = CardLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bindView(productList[position], listener)
        val product = productList[position]
        holder.binding.rvimage.setImageResource(product.image)
        holder.binding.rvtitle.text = product.title
        holder.binding.rvprice.text = product.price.toString()
        holder.binding.rvrating.text = product.rating.toString()
        itemCount

    }

    fun setFilteredList(filteredList: List<Product>) {
        this.productList = filteredList
        notifyDataSetChanged()
    }
}


