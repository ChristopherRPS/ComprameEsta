package com.sleepy.tiendavirtualapp.products

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.sleepy.tiendavirtualapp.Adaptadores.ProductAdapter
import com.sleepy.tiendavirtualapp.Modelos.responses.Product
import com.sleepy.tiendavirtualapp.R
import org.json.JSONArray
import org.json.JSONObject

class FragmentProductos : Fragment() {

    private lateinit var recyclerViewProductos: RecyclerView
    private lateinit var productAdapter: ProductAdapter
    private val productList = ArrayList<Product>()
    private lateinit var requestQueue: RequestQueue
    private var categoryId: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_productos, container, false)

        // Obtener el ID de la categoría de los argumentos
        categoryId = arguments?.getInt("CATEGORY_ID") ?: 0

        // Inicialización de RecyclerView
        recyclerViewProductos = view.findViewById(R.id.recyclerViewProductos)
        recyclerViewProductos.layoutManager = LinearLayoutManager(context)
        productAdapter = ProductAdapter(requireContext(), productList)
        recyclerViewProductos.adapter = productAdapter

        requestQueue = Volley.newRequestQueue(requireContext())
        loadProducts()

        return view
    }

    private fun loadProducts() {
        val url = "http://ec2-3-137-176-176.us-east-2.compute.amazonaws.com:8080/products/productos-by-category/$categoryId"

        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->
                val products: JSONArray = response.optJSONArray("data") ?: JSONArray()

                for (i in 0 until products.length()) {
                    val productObject: JSONObject = products.getJSONObject(i)
                    val id = productObject.optInt("id", 0)
                    val name = productObject.optString("nombre", "")
                    val description = productObject.optString("descripcion", "")
                    val imageUrl = productObject.optString("urlFoto", "")

                    val product = Product(id, name, description, imageUrl)
                    productList.add(product)
                }

                productAdapter.notifyDataSetChanged()
            },
            { error ->
                Log.e("FragmentProductos", "Error fetching products", error)
                Toast.makeText(requireContext(), "Error fetching products: ${error.message}", Toast.LENGTH_SHORT).show()
            }
        )

        requestQueue.add(jsonObjectRequest)
    }
}
