package com.sleepy.tiendavirtualapp.Adaptadores

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sleepy.tiendavirtualapp.Modelos.responses.Product
import com.sleepy.tiendavirtualapp.R

class ProductAdapter(
    private val context: Context,
    private val productList: List<Product>
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.product_item, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = productList[position]
        holder.productNameTextView.text = product.nombre
        holder.productDescriptionTextView.text = product.descripcion
        Glide.with(context).load(product.urlFoto).into(holder.productImageView)

        holder.addToCartButton.setOnClickListener {
            // Aquí podrías manejar el evento de agregar al carrito
        }
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val productImageView: ImageView = itemView.findViewById(R.id.product_image)
        val productNameTextView: TextView = itemView.findViewById(R.id.product_name)
        val productDescriptionTextView: TextView = itemView.findViewById(R.id.product_description)
        val addToCartButton: Button = itemView.findViewById(R.id.add_to_cart_button)
    }
}
