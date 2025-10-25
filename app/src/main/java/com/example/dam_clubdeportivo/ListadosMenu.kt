package com.example.dam_clubdeportivo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ListadosMenu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_listados_menu)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val btnSalir = findViewById<Button>(R.id.btnSalir)
        btnSalir.setOnClickListener {
            val intent = Intent(this, MenuPrincipal::class.java)
            startActivity(intent)
        }

        val btnClientesHist = findViewById<Button>(R.id.btnClientesHist)
        btnClientesHist.setOnClickListener {
            val intent = Intent(this, ListadoClientesHistoricos::class.java)
            startActivity(intent)
        }
        val btnPagosHist = findViewById<Button>(R.id.btnPagosHist)
        btnPagosHist.setOnClickListener {
            val intent = Intent(this, ListadosPagosHistoricos::class.java)
            startActivity(intent)
        }
        val btnMorosos = findViewById<Button>(R.id.btnMorosos)
        btnMorosos.setOnClickListener {
            val intent = Intent(this, ListadoMorosos::class.java)
            startActivity(intent)
        }
    }
}