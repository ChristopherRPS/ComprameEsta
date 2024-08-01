package com.sleepy.tiendavirtualapp.Adaptadores

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sleepy.tiendavirtualapp.Modelos.responses.CategoriaProducto
import com.sleepy.tiendavirtualapp.R

class CategoriaProductoAdapter(
    private val context: Context,
    private val categoriaList: List<CategoriaProducto>,
    private val itemClickListener: OnItemClickListener
) : RecyclerView.Adapter<CategoriaProductoAdapter.CategoriaViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(categoria: CategoriaProducto)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriaViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.category_item, parent, false)
        return CategoriaViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoriaViewHolder, position: Int) {
        val categoria = categoriaList[position]
        holder.nameTextView.text = categoria.nombreTipoProducto
        Glide.with(context).load(categoria.urlIcon).into(holder.iconImageView)

        holder.itemView.setOnClickListener {
            itemClickListener.onItemClick(categoria)
        }
    }

    override fun getItemCount(): Int {
        return categoriaList.size
    }

    class CategoriaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.category_name)
        val iconImageView: ImageView = itemView.findViewById(R.id.category_icon)
    }
}
