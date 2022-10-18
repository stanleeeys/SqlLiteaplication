package com.example.sqlliteaplication

import android.content.ContentValues
import android.content.Intent
import android.content.SharedPreferences
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var etn_codigo: EditText
    private lateinit var etDescripcion: EditText
    private lateinit var etPrecio: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        etn_codigo = findViewById(R.id.etn_codigo)
        etDescripcion = findViewById(R.id.etn_descripcion)
        etPrecio = findViewById(R.id.etn_precio)

    }


    //Funcion para registrar productos
    fun registrar(vista: View) {

        //Se crea la conexión a la base de datos
        val admin = AdminSQLite(this, "Tienda", null, 1)
        val baseDeDatos: SQLiteDatabase = admin.writableDatabase

        val codigo = etn_codigo.text.toString()
        val descripcion = etDescripcion.text.toString()
        val precio = etPrecio.text.toString()



        if (!codigo.isEmpty() && !descripcion.isEmpty() && !precio.isEmpty()) {
            val registro = ContentValues()
            registro.put("codigo", codigo)
            registro.put("descripcion", descripcion)
            registro.put("precio", precio)

            baseDeDatos.insert("articulos", null, registro)
            baseDeDatos.close()
            etn_codigo.setText(" ")
            etDescripcion.setText(" ")
            etPrecio.setText(" ")

            Toast.makeText(this, "Tu registro ha sido exitoso", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(
                this,
                "Debes llenar todos los campos para registrar",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    fun Buscar(vista: View) {

        val admin = AdminSQLite(this, "Tienda", null, 1)
        val baseDeDatos: SQLiteDatabase = admin.writableDatabase

        val codigo = etn_codigo.text.toString()

        if (!codigo.isEmpty()) {

            val fila = baseDeDatos.rawQuery(
                "select descripcion,precio from articulos where codigo = $codigo",
                null
            )
            if (fila.moveToFirst()) {
                etDescripcion.setText(fila.getString(0))
                etPrecio.setText(fila.getString(1))
                baseDeDatos.close()


            } else {
                Toast.makeText(this, "No existe el articulo", Toast.LENGTH_LONG).show()
                baseDeDatos.close()
            }
        } else {

            Toast.makeText(this, "Debe ingresar un codigo", Toast.LENGTH_LONG).show()
        }

    }

    fun Modificar(vista: View) {
        val admin = AdminSQLite(this, "Tienda", null, 1)
        val baseDeDatos: SQLiteDatabase = admin.writableDatabase

        val codigo = etn_codigo.text.toString()
        val descripcion = etDescripcion.text.toString()
        val precio = etPrecio.text.toString()

        if (!codigo.isEmpty() && !descripcion.isEmpty() && !precio.isEmpty()) {
            val registro = ContentValues()
            registro.put("codigo", codigo)
            registro.put("descripcion", descripcion)
            registro.put("precio", precio)

            val cantidad: Int = baseDeDatos.update("articulos", registro, "codigo=$codigo", null)
            baseDeDatos.close()

            if (cantidad == 1) {

                Toast.makeText(this, "Articulos modificado correctamente", Toast.LENGTH_LONG).show()
            } else {

                Toast.makeText(this, "No existe el articulo", Toast.LENGTH_LONG).show()
                baseDeDatos.close()
            }

            etn_codigo.setText(" ")
            etDescripcion.setText(" ")
            etPrecio.setText(" ")

            Toast.makeText(this, "Registro con éxito", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this, "Debes llenar todos los campos", Toast.LENGTH_LONG).show()
        }
    }
    fun eliminar (Vista: View){
    //Se crea la conexion a la base de datos
        val admin = AdminSQLite(this, "Tienda", null, 1)
        val baseDeDatos: SQLiteDatabase = admin.writableDatabase

    val codigo = etn_codigo.text.toString()

        if (!codigo.isEmpty()){
            val cantidad:Int = baseDeDatos.delete("articulos","codigo=${codigo}",null)
            etn_codigo.setText("")
            etDescripcion.setText("")
            etPrecio.setText("")

            if (cantidad==1) {
                Toast.makeText(this, "no existe el articulo", Toast.LENGTH_LONG).show()
                baseDeDatos.close()
            }else{
                Toast.makeText(this, "No existe el articulo", Toast.LENGTH_SHORT).show()
                baseDeDatos.close()
            }
            }else{
            Toast.makeText(this, "Debes ingresar in codigo", Toast.LENGTH_SHORT).show()
        }

    }
    fun siguiente(Vista: View){
        val ventana: Intent = Intent(applicationContext,Ventana2::class.java)
        startActivity(ventana)
    }

}
