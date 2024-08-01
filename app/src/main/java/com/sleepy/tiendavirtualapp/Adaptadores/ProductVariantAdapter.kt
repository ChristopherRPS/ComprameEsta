package com.sleepy.tiendavirtualapp.Adaptadores

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sleepy.tiendavirtualapp.Modelos.responses.ProductVariant
import com.sleepy.tiendavirtualapp.R

class ProductVariantAdapter(
    private val context: Context,
    private val variantList: List<ProductVariant>,
    private val itemClickListener: OnItemClickListener
) : RecyclerView.Adapter<ProductVariantAdapter.VariantViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(variant: ProductVariant)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VariantViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.variant_item, parent, false)
        return VariantViewHolder(view)
    }

    override fun onBindViewHolder(holder: VariantViewHolder, position: Int) {
        val variant = variantList[position]
        holder.productNameTextView.text = variant.producto.nombre
        holder.productPriceTextView.text = "Costo: $${variant.costo}"
        Glide.with(context).load(variant.imageUrl).into(holder.productImageView)

        holder.itemView.setOnClickListener {
            itemClickListener.onItemClick(variant)
        }
    }

    override fun getItemCount(): Int {
        return variantList.size
    }

    class VariantViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val productNameTextView: TextView = itemView.findViewById(R.id.product_name)
        val productPriceTextView: TextView = itemView.findViewById(R.id.product_price)
        val productImageView: ImageView = itemView.findViewById(R.id.product_image)
    }
}
