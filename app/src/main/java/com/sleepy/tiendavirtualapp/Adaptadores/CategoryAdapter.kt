package com.sleepy.tiendavirtualapp.Adaptadores

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sleepy.tiendavirtualapp.Modelos.responses.Category
import com.sleepy.tiendavirtualapp.R

class CategoryAdapter(
    private val context: Context,
    private val categoryList: List<Category>,
    private val itemClickListener: OnItemClickListener // Añadir este parámetro
) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(category: Category)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.category_item, parent, false)
        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = categoryList[position]
        holder.nameTextView.text = category.name
        Glide.with(context).load(category.iconUrl).into(holder.iconImageView)

        holder.itemView.setOnClickListener {
            itemClickListener.onItemClick(category)
        }
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.category_name)
        val iconImageView: ImageView = itemView.findViewById(R.id.category_icon)
    }
}
