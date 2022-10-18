package com.example.sqlliteaplication

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast

class Ventana2 : AppCompatActivity() {
    lateinit var etnNombre:EditText
    lateinit var etnApellido:EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ventana2)

        etnNombre = findViewById(R.id.etnNombre)
        etnApellido = findViewById(R.id.etnApellido)
        var pref = getSharedPreferences("datosPersona", Context.MODE_PRIVATE)
        var nombre = pref.getString("nombre","")
        var apellido = pref.getString("apellido","")


    }

    fun guardar(Vista: View){
        var pref = getSharedPreferences("datosPersonales", Context.MODE_PRIVATE)
        var editor = pref.edit()

        editor.putString("nombre",etnNombre.text.toString())
        editor.putString("apellido",etnApellido.text.toString())
        editor.commit()
        Toast.makeText(this, "Se ha guardado exitosamente",Toast.LENGTH_LONG).show()


    }



}
