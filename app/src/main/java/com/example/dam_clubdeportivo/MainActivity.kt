package com.example.dam_clubdeportivo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    lateinit var dbHelper: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // -------------------------------
        // INICIALIZAR BASE DE DATOS
        // -------------------------------
        dbHelper = DBHelper(this)
        val db = dbHelper.readableDatabase
        db.execSQL("PRAGMA foreign_keys = ON;")

        // -------------------------------
        // REFERENCIAS DE UI
        // -------------------------------
        val etUsuario = findViewById<EditText>(R.id.etUsuario)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btnIngresar = findViewById<Button>(R.id.btnIngresar)

        // -------------------------------
        // BOTÓN INGRESAR
        // -------------------------------
        btnIngresar.setOnClickListener {
            val usuario = etUsuario.text.toString().trim()
            val clave = etPassword.text.toString().trim()

            if (usuario.isEmpty() || clave.isEmpty()) {
                Toast.makeText(this, "Ingrese usuario y contraseña", Toast.LENGTH_SHORT).show()
            } else {
                // CONSULTAR EN LA BASE DE DATOS
                val cursor = db.rawQuery(
                    "SELECT * FROM Usuarios WHERE usuario = ? AND clave = ?",
                    arrayOf(usuario, clave)
                )

                if (cursor.moveToFirst()) {
                    // LOGIN EXITOSO
                    Toast.makeText(this, "Bienvenido $usuario", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, MenuPrincipal::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    // LOGIN FALLIDO
                    Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show()
                }

                cursor.close()
            }
        }

        // -------------------------------
        // EDGE-TO-EDGE
        // -------------------------------
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

    }
}
