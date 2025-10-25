package com.example.dam_clubdeportivo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class GestorClientePagos : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_gestor_cliente_pagos)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val btnMembresia = findViewById<Button>(R.id.btnMembresia)
        btnMembresia.setOnClickListener {
            val intent = Intent(this, GestorClienteMembresia::class.java)
            startActivity(intent)
        }
        val btnIdentificacion = findViewById<Button>(R.id.btnIdentificacion)
        btnIdentificacion.setOnClickListener {
            val intent = Intent(this, GestorClienteIdentificacion::class.java)
            startActivity(intent)

        }
        val btnSalir = findViewById<Button>(R.id.btnSalir)
        btnSalir.setOnClickListener {
            val intent = Intent(this, MenuPrincipal::class.java)
            startActivity(intent)
        }
}
}