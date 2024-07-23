package com.sleepy.tiendavirtualapp.Adaptadores

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.sleepy.tiendavirtualapp.Modelos.ModeloImagenSeleccionada
import com.sleepy.tiendavirtualapp.R
import com.sleepy.tiendavirtualapp.databinding.ItemImagenesSeleccionadasBinding

class AdaptadorImagenSeleccionada (

    private val context : Context,
    private val imagenesSelecArrayList : ArrayList<ModeloImagenSeleccionada>

):Adapter<AdaptadorImagenSeleccionada.HolderImagenSeleccionada>() {
    private lateinit var binding : ItemImagenesSeleccionadasBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderImagenSeleccionada {
        binding = ItemImagenesSeleccionadasBinding.inflate(LayoutInflater.from(context), parent, false)
        return HolderImagenSeleccionada(binding.root)
    }

    override fun getItemCount(): Int {
        return imagenesSelecArrayList.size
    }

    override fun onBindViewHolder(holder: HolderImagenSeleccionada, position: Int) {
        val modelo = imagenesSelecArrayList[position]

        val imagenUri = modelo.imageUri


        //leyendo la imagen
        try {
            Glide.with(context)
                .load(imagenUri)
                .placeholder(R.drawable.item_imagen)
                .into(holder.imagenItem)
        }catch (e:Exception){

        }

        //Evento para eliminar una imagen de lista
        holder.btn_borrar.setOnClickListener {
            imagenesSelecArrayList.remove(modelo)
            notifyDataSetChanged()
        }
    }
    inner class HolderImagenSeleccionada(itemView : View) : ViewHolder(itemView){
        var imagenItem = binding.imagenItem
        var btn_borrar = binding.borrarItem
    }


}