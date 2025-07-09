package com.example.aula01lari1711

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.aula01lari1711.fragments.BuscarFragment
import com.example.aula01lari1711.fragments.CriarFragment
import com.example.aula01lari1711.fragments.HomeFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var toolbar: Toolbar

    override fun onResume() {
        super.onResume()
        updateUserHeaderAndMenu()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawerLayout = findViewById(R.id.drawer_layout)
        navigationView = findViewById(R.id.navigation_view)
        bottomNavigationView = findViewById(R.id.bottom_navigation)
        toolbar = findViewById(R.id.toolbar)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(android.R.drawable.ic_menu_sort_by_size)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, HomeFragment())
                .commit()
        }

        bottomNavigationView.setOnItemSelectedListener { item ->
            val fragment = when (item.itemId) {
                R.id.nav_home -> HomeFragment()
                R.id.nav_criar -> CriarFragment()
                R.id.nav_buscar -> BuscarFragment()
                else -> null
            }
            fragment?.let {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, it)
                    .commit()
                true
            } ?: false
        }

        navigationView.setNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_login -> {
                    startActivity(Intent(this, LoginActivity::class.java))
                    true
                }
                R.id.menu_logout -> {
                    performLogout()
                    true
                }
                R.id.menu_sobre -> {
                    showSobreDialog()
                    true
                }
                else -> false
            }.also {
                drawerLayout.closeDrawer(GravityCompat.START)
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        drawerLayout.openDrawer(GravityCompat.START)
        return true
    }

    private fun updateUserHeaderAndMenu() {
        val prefs = getSharedPreferences("user", Context.MODE_PRIVATE)
        val nome = prefs.getString("nome", null)
        val cargo = prefs.getString("cargo", null)

        val headerView = navigationView.getHeaderView(0)
        val nomeTextView = headerView.findViewById<TextView>(R.id.nav_header_nome)
        val cargoTextView = headerView.findViewById<TextView>(R.id.nav_header_cargo)

        val isLoggedIn = !nome.isNullOrEmpty()

        nomeTextView.text = nome ?: "Nome do Usuário"
        cargoTextView.text = cargo ?: "Cargo"

        // Altera visibilidade do menu
        val menu = navigationView.menu
        menu.findItem(R.id.menu_login).isVisible = !isLoggedIn
        menu.findItem(R.id.menu_logout).isVisible = isLoggedIn
    }

    private fun performLogout() {
        val prefs = getSharedPreferences("user", Context.MODE_PRIVATE)
        prefs.edit().clear().apply()

        Toast.makeText(this, "Logout realizado com sucesso.", Toast.LENGTH_SHORT).show()

        val headerView = navigationView.getHeaderView(0)
        headerView.findViewById<TextView>(R.id.nav_header_nome).text = "Nome do Usuário"
        headerView.findViewById<TextView>(R.id.nav_header_cargo).text = "Cargo"

        navigationView.menu.clear()
        navigationView.inflateMenu(R.menu.drawer_menu)

        updateUserHeaderAndMenu()

        /*
         login meramente simbolico
         */
    }



    private fun showSobreDialog() {
        AlertDialog.Builder(this)
            .setTitle("Sobre")
            .setMessage("Aplicativo para controle de Atividades Complementares.\n\nDesenvolvido por: Mel & Lari.\nVersão 1.0")
            .setPositiveButton("Fechar", null)
            .show()
    }
}
