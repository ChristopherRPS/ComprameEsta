package com.sleepy.tiendavirtualapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sleepy.tiendavirtualapp.Cliente.LoginClienteActivity
import com.sleepy.tiendavirtualapp.Vendedor.LoginVendedorActivity
import com.sleepy.tiendavirtualapp.databinding.ActivitySeleccionarTipoBinding

class SeleccionarTipoActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySeleccionarTipoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySeleccionarTipoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tipoVendedor.setOnClickListener {
            startActivity(Intent(this@SeleccionarTipoActivity, LoginVendedorActivity::class.java))
        }

        binding.tipoCliente.setOnClickListener {
            startActivity(Intent(this@SeleccionarTipoActivity,LoginClienteActivity::class.java))
        }
    }
}