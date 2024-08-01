package com.sleepy.tiendavirtualapp.Cliente.Bottom_Nav_Fragments_Cliente

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.sleepy.tiendavirtualapp.Adaptadores.CategoryAdapter
import com.sleepy.tiendavirtualapp.Modelos.responses.Category
import com.sleepy.tiendavirtualapp.R
import com.sleepy.tiendavirtualapp.products.FragmentProductos
import org.json.JSONArray
import org.json.JSONObject

class FragmentTiendaC : Fragment(), CategoryAdapter.OnItemClickListener {

    private lateinit var recyclerViewCategories: RecyclerView
    private lateinit var categoryAdapter: CategoryAdapter
    private val categoryList = ArrayList<Category>()
    private val URL = "http://ec2-3-137-176-176.us-east-2.compute.amazonaws.com:8080/products/categorias-producto"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_tienda_c, container, false)

        // Inicialización de RecyclerView
        recyclerViewCategories = view.findViewById(R.id.recyclerViewCategories)
        recyclerViewCategories.layoutManager = GridLayoutManager(context, 2)
        categoryAdapter = CategoryAdapter(requireContext(), categoryList, this)
        recyclerViewCategories.adapter = categoryAdapter

        loadCategories()

        return view
    }

    private fun loadCategories() {
        val requestQueue: RequestQueue = Volley.newRequestQueue(requireContext())

        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, URL, null,
            { response ->
                if (isAdded) {
                    val categories: JSONArray = response.optJSONArray("data") ?: JSONArray()

                    for (i in 0 until categories.length()) {
                        val categoryObject: JSONObject = categories.getJSONObject(i)
                        val id = categoryObject.optInt("id", 0)
                        val name = categoryObject.optString("nombre", "")
                        val iconUrl = categoryObject.optString("urlIcon", "")

                        val category = Category(id, name, iconUrl)
                        categoryList.add(category)
                    }

                    categoryAdapter.notifyDataSetChanged()
                }
            },
            { error ->
                if (isAdded) {
                    Log.e("FragmentTiendaC", "Error fetching data", error)
                    Toast.makeText(requireContext(), "Error fetching data: ${error.message}", Toast.LENGTH_SHORT).show()
                }
            }
        )

        requestQueue.add(jsonObjectRequest)
    }

    override fun onItemClick(category: Category) {
        // Imprimir el ID de la categoría en el log
        Log.d("FragmentTiendaC", "Category ID clicked: ${category.id}")
        val fragment = FragmentProductos().apply {
            arguments = Bundle().apply {
                putInt("CATEGORY_ID", category.id)
            }
        }
        parentFragmentManager.beginTransaction()
            .replace(R.id.navFragment, fragment) // Reemplaza con el ID correcto del contenedor de fragmentos
            .addToBackStack(null)
            .commit()
    }
}
