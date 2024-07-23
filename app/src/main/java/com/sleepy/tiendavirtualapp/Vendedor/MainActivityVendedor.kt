package com.sleepy.tiendavirtualapp.Vendedor

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.sleepy.tiendavirtualapp.R
import com.sleepy.tiendavirtualapp.SeleccionarTipoActivity
import com.sleepy.tiendavirtualapp.Vendedor.Bottom_Nav_Fragments_Vendedor.FragmentMisProductosV
import com.sleepy.tiendavirtualapp.Vendedor.Bottom_Nav_Fragments_Vendedor.FragmentOrdenesV
import com.sleepy.tiendavirtualapp.Vendedor.Nav_Fragments_Vendedor.FragmentCategoriasV
import com.sleepy.tiendavirtualapp.Vendedor.Nav_Fragments_Vendedor.FragmentAboutV
import com.sleepy.tiendavirtualapp.Vendedor.Nav_Fragments_Vendedor.FragmentInicioV
import com.sleepy.tiendavirtualapp.Vendedor.Nav_Fragments_Vendedor.FragmentMiTiendaV
import com.sleepy.tiendavirtualapp.Vendedor.Nav_Fragments_Vendedor.FragmentReseniasV
import com.sleepy.tiendavirtualapp.databinding.ActivityMainVendedorBinding

class MainActivityVendedor : AppCompatActivity() , NavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding : ActivityMainVendedorBinding
    private var firebaseAuth : FirebaseAuth?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainVendedorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        firebaseAuth = FirebaseAuth.getInstance()
        comprobarSesion()

        binding.navigationView.setNavigationItemSelectedListener(this)

        val toggle = ActionBarDrawerToggle(
            this,
            binding.drawerLayout,
            toolbar,
            R.string.open_drawer,
            R.string.close_drawer
        )

        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        replaceFragment(FragmentInicioV())
        binding.navigationView.setCheckedItem(R.id.op_inicio_v)

    }

    private fun cerrarSesion(){
        firebaseAuth!!.signOut()
        startActivity(Intent(applicationContext, SeleccionarTipoActivity::class.java))
        finish()
        Toast.makeText(applicationContext, "Has cerrado sesión", Toast.LENGTH_SHORT).show()
    }

    private fun comprobarSesion() {
        /*Si el usuario no ha iniciado sesion lo envia a registrarse*/
        if(firebaseAuth!!.currentUser==null){
            startActivity(Intent(applicationContext, SeleccionarTipoActivity::class.java))
            Toast.makeText(applicationContext, "Vendedor no registrado o no logeado", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(applicationContext, "Vendedor en linea", Toast.LENGTH_SHORT).show()
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.navFragment, fragment)
            .commit()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.op_inicio_v->{
                replaceFragment(FragmentInicioV())
            }
            R.id.op_mi_tienda_v->{
                replaceFragment(FragmentMiTiendaV())
            }
            R.id.op_categorias_v->{
                replaceFragment(FragmentCategoriasV())
            }
            R.id.op_resenia_v->{
                replaceFragment(FragmentReseniasV())
            }
            R.id.op_about_v->{
                replaceFragment(FragmentAboutV())
            }
            R.id.op_cerrar_sesion_v->{
                cerrarSesion()
            }
            R.id.op_mis_productos_v->{
                replaceFragment(FragmentMisProductosV())
            }
            R.id.op_mis_ordenes_v->{
                replaceFragment(FragmentOrdenesV())
            }
        }
        binding.drawerLayout.closeDrawer(GravityCompat.START)
        return true

    }
}