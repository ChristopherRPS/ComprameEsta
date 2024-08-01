package com.sleepy.tiendavirtualapp.Modelos.responses

data class ProductVariant(
    val id: Int,
    val stock: Int,
    val talla: Talla,
    val color: Color,
    val producto: Producto,
    val imageUrl: String,
    val costo: Double,
    val porcentajeDescuento: Double,
    val promocionar: Boolean,
    val costoNew: Double
)

data class Talla(val id: Int, val nombre: String, val activo: Boolean, val eliminado: Boolean)
data class Color(val id: Int, val nombre: String, val activo: Boolean, val eliminado: Boolean, val classCss: String)
data class Producto(
    val id: Int,
    val nombre: String,
    val descripcion: String,
    val pais: Pais,
    val tipoProducto: TipoProducto,
    val genero: Genero,
    val marca: Marca,
    val activo: Boolean,
    val eliminado: Boolean
)
data class Pais(val id: Int, val nombre: String, val activo: Boolean, val eliminado: Boolean)
data class TipoProducto(val id: Int, val nombreTipoProducto: String, val activo: Boolean, val eliminado: Boolean, val categoriaProducto: CategoriaProducto)
data class CategoriaProducto(val id: Int, val nombreTipoProducto: String, val activo: Boolean, val eliminado: Boolean, val urlIcon: String)
data class Genero(val id: Int, val nombre: String, val activo: Boolean, val eliminado: Boolean)
data class Marca(val id: Int, val nombre: String, val activo: Boolean, val eliminado: Boolean)
