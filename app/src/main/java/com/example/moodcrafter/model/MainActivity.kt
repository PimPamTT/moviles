package com.example.moodcrafter.model

import android.content.*
import android.database.sqlite.SQLiteConstraintException
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Menu
import android.view.MenuItem
import com.example.moodcrafter.databinding.ActivityMainBinding
import android.widget.Toast
import androidx.room.Room
import com.example.moodcrafter.database.AppDatabase
import com.example.moodcrafter.R

class MainActivity : AppCompatActivity()
{
    enum class Params
    {
        // registroC,
        p1,
        p2,
        p3
    }

    private lateinit var binding: ActivityMainBinding

    private lateinit var db: AppDatabase

    // Contador para el título de registro
    // private var registroCounter = 1

    // Variables para el estado actual
    private var isEleccion1Clicked = false
    private var isEleccion2Clicked = false
    private var isEleccion3Clicked = false

    private val eleccionesList = mutableListOf<String>()

    private val emoticonToPalabraMap = mapOf(
        "/:)" to "FELICIDAD",
        ">:(" to "IRA",
        ":'(" to "TRISTEZA"
        // Agrega más mapeos según sea necesario
    )

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        db = Room.databaseBuilder(this, AppDatabase::class.java, AppDatabase.DATABASE_NAME).allowMainThreadQueries().build()
        setSupportActionBar(binding.tlMainActivity)

        // Configurar los colores iniciales
        resetColors()

        // Obtener el valor de registroCounter del Intent
        binding.nregistro.text = eleccionesList.toString()

        binding.felicidad.setOnClickListener {
            onButtonClicked(Color.parseColor("#00FF00"), "felicidad")
        }

        binding.ira.setOnClickListener {
            onButtonClicked(Color.parseColor("#FF0000"), "ira")
        }

        binding.tristeza.setOnClickListener {
            onButtonClicked(Color.parseColor("#0000FF"), "tristeza")
        }

        // Configurar clic del botón de reinicio
        binding.reiniciar.setOnClickListener { resetColors() }
    }

    private fun onButtonClicked(color: Int, choice: String)
    {
        when
        {
            !isEleccion1Clicked -> {
                binding.eleccion1.setBackgroundColor(color)
                binding.e1.setBackgroundColor(color)
                binding.e1.text = getEmoticonForChoice(choice)
                isEleccion1Clicked = true
                eleccionesList.add(emoticonToPalabraMap[binding.e1.text] ?: "")
                binding.nregistro.text = eleccionesList.toString()
            }

            !isEleccion2Clicked -> {
                binding.eleccion2.setBackgroundColor(color)
                binding.e2.setBackgroundColor(color)
                binding.e2.text = getEmoticonForChoice(choice)
                isEleccion2Clicked = true
                eleccionesList.add(emoticonToPalabraMap[binding.e2.text] ?: "")
                binding.nregistro.text = eleccionesList.toString()
            }

            !isEleccion3Clicked -> {
                binding.eleccion3.setBackgroundColor(color)
                binding.e3.setBackgroundColor(color)
                binding.e3.text = getEmoticonForChoice(choice)
                isEleccion3Clicked = true
                eleccionesList.add(emoticonToPalabraMap[binding.e3.text] ?: "")

                // Si todos los botones se han clicado, mostrar un Toast, reiniciar el estado y colores, e irse a la otra página.
                showToast("Crafting Moods")
                Handler(Looper.getMainLooper()).postDelayed({ resetColors() }, 1000)

                // Crear un Intent para pasar a EntityDataActivity
                // val tit = "REGISTRO #$registroCounter"

                val tit = eleccionesList.joinToString("/")
                val el1 = binding.e1.text.toString()
                val el2 = binding.e2.text.toString()
                val el3 = binding.e3.text.toString()

                try
                {
                    val register = Register(titulo = tit, eleccion1 = el1, eleccion2 = el2, eleccion3 = el3)
                    db.registerDao().save(register)
                }
                catch (e: SQLiteConstraintException)
                {
                    Toast.makeText(this, "Ya existe un registro con el mismo título.", Toast.LENGTH_SHORT).show()
                }

                // Iniciar la nueva actividad
                val intent = Intent(this, Conversation::class.java)

                intent.putExtra(Conversation.Params.p1.name, eleccionesList.getOrNull(0))
                intent.putExtra(Conversation.Params.p2.name, eleccionesList.getOrNull(1))
                intent.putExtra(Conversation.Params.p3.name, eleccionesList.getOrNull(2))

                startActivity(intent)

                // Sumar una al registro
                // registroCounter++

                binding.nregistro.text = eleccionesList.toString()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_activity, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.mRegistersMainActivity -> {
                val intent = Intent(
                    this, EntityData::class.java
                )
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getEmoticonForChoice(choice: String): String
    {
        return when (choice) {
            "felicidad" -> "/:)"
            "ira" -> ">:("
            "tristeza" -> ":'("
            else -> "" // Manejo por defecto
        }
    }

    private fun showToast(message: String)
    {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun resetColors()
    {
        binding.eleccion1.setBackgroundColor(Color.parseColor("#9B9B9B"))
        binding.eleccion2.setBackgroundColor(Color.parseColor("#9B9B9B"))
        binding.eleccion3.setBackgroundColor(Color.parseColor("#9B9B9B"))

        binding.eleccionActual.setBackgroundColor(Color.parseColor("#9B9B9B"))

        binding.e1.setBackgroundColor(Color.parseColor("#9B9B9B"))
        binding.e2.setBackgroundColor(Color.parseColor("#9B9B9B"))
        binding.e3.setBackgroundColor(Color.parseColor("#9B9B9B"))

        binding.e1.text = "..."
        binding.e2.text = "..."
        binding.e3.text = "..."

        binding.nregistro.setBackgroundColor(Color.parseColor("#9B9B9B"))

        // Reiniciar el estado
        isEleccion1Clicked = false
        isEleccion2Clicked = false
        isEleccion3Clicked = false

        eleccionesList.clear()
        binding.nregistro.text = eleccionesList.toString()
    }
}