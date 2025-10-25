package com.example.dam_clubdeportivo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MenuPrincipal : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_menu_principal)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // 🔹 Botón CLIENTES → abre GestorCliente
        val btnClientes = findViewById<Button>(R.id.btnClientes)
        btnClientes.setOnClickListener {
            val intent = Intent(this, GestorCliente::class.java)
            startActivity(intent)
        }

        // 🔹 Botón PAGOS → abre GestorPagosNuevo
        val btnPagos = findViewById<Button>(R.id.btnPagos)
        btnPagos.setOnClickListener {
            val intent = Intent(this, GestorPagosNuevo::class.java)
            startActivity(intent)
        }

        // 🔹 Botón LISTADOS → abre ListadosMenu
        val btnListados = findViewById<Button>(R.id.btnListados)
        btnListados.setOnClickListener {
            val intent = Intent(this, ListadosMenu::class.java)
            startActivity(intent)
        }
    }
}
