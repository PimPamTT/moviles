package com.example.moodcrafter

import android.content.*
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.moodcrafter.databinding.ActivityMainBinding
import android.widget.Toast

class MainActivity : AppCompatActivity()
{
    enum class Params
    {
        registroC,
        p1,
        p2,
        p3
    }

    private lateinit var binding: ActivityMainBinding

    // Contador para el título de registro
    private var registroCounter = 1

    // Variables para el estado actual
    private var isEleccion1Clicked = false
    private var isEleccion2Clicked = false
    private var isEleccion3Clicked = false

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configurar los colores iniciales
        resetColors()

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
            }
            !isEleccion2Clicked -> {
                binding.eleccion2.setBackgroundColor(color)
                binding.e2.setBackgroundColor(color)
                binding.e2.text = getEmoticonForChoice(choice)
                isEleccion2Clicked = true
            }
            !isEleccion3Clicked -> {
                binding.eleccion3.setBackgroundColor(color)
                binding.e3.setBackgroundColor(color)
                binding.e3.text = getEmoticonForChoice(choice)
                isEleccion3Clicked = true

                // Si todos los botones se han clicado, mostrar un Toast, reiniciar el estado y colores, e irse a la otra página.
                showToast("Crafting Moods")
                Handler(Looper.getMainLooper()).postDelayed({ resetColors() }, 1000)

                // Crear un Intent para pasar a EntityDataActivity
                val intent = Intent(this, EntityData::class.java)
                intent.putExtra(EntityData.Params.registroC.name, registroCounter.toString())
                intent.putExtra(EntityData.Params.p1.name, binding.e1.text.toString())
                intent.putExtra(EntityData.Params.p2.name, binding.e2.text.toString())
                intent.putExtra(EntityData.Params.p3.name, binding.e3.text.toString())

                // Iniciar la nueva actividad
                startActivity(intent)

                // Sumar una al registro

                binding.nregistro.text = registroCounter.toString()
            }
        }
        registroCounter++
    }

    private fun getEmoticonForChoice(choice: String): String {
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

        binding.e1.setBackgroundColor(Color.parseColor("#9B9B9B"))
        binding.e2.setBackgroundColor(Color.parseColor("#9B9B9B"))
        binding.e3.setBackgroundColor(Color.parseColor("#9B9B9B"))

        binding.nregistro.setBackgroundColor(Color.parseColor("#9B9B9B"))

        // Reiniciar el estado
        isEleccion1Clicked = false
        isEleccion2Clicked = false
        isEleccion3Clicked = false
    }
}