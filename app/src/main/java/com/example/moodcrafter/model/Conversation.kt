package com.example.moodcrafter.model

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.moodcrafter.R
import com.example.moodcrafter.databinding.ActivityConversationBinding

class Conversation : AppCompatActivity()
{
    private lateinit var binding: ActivityConversationBinding

    enum class Params {
        // registroC,
        p1,
        p2,
        p3
    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConversationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.tlConversation)

        resetColors()

        val el1 = intent.getStringExtra(MainActivity.Params.p1.name)
        val el2 = intent.getStringExtra(MainActivity.Params.p2.name)
        val el3 = intent.getStringExtra(MainActivity.Params.p3.name)

        binding.R2.text = "Eleccion 1: $el1"
        binding.R4.text = "Eleccion 2: $el2"
        binding.R6.text = "Eleccion 3: $el3"

        /*
            binding.siguiente.setOnClickListener {
                // Iniciar la nueva actividad
                val intent = Intent(this, EntityData::class.java)
                startActivity(intent)
            }
        */
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_conversation, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId)
        {
            R.id.mRegistersConversation -> {
                val intent = Intent(
                    this, EntityData::class.java
                )
                startActivity(intent)
            }
            R.id.mInsertsConversation -> {
                val intent = Intent(
                    this, MainActivity::class.java
                )
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun resetColors() {
        binding.EJ1.setBackgroundColor(Color.parseColor("#9B9B9B"))
        binding.EJ2.setBackgroundColor(Color.parseColor("#9B9B9B"))
        binding.EJ3.setBackgroundColor(Color.parseColor("#9B9B9B"))
        binding.EJ4.setBackgroundColor(Color.parseColor("#9B9B9B"))
        binding.EJ5.setBackgroundColor(Color.parseColor("#9B9B9B"))
        binding.EJ6.setBackgroundColor(Color.parseColor("#9B9B9B"))
        binding.EJ7.setBackgroundColor(Color.parseColor("#9B9B9B"))

        binding.R1.setBackgroundColor(Color.parseColor("#9B9B9B"))
        binding.R2.setBackgroundColor(Color.parseColor("#9B9B9B"))
        binding.R3.setBackgroundColor(Color.parseColor("#9B9B9B"))
        binding.R4.setBackgroundColor(Color.parseColor("#9B9B9B"))
        binding.R5.setBackgroundColor(Color.parseColor("#9B9B9B"))
        binding.R6.setBackgroundColor(Color.parseColor("#9B9B9B"))
        binding.R7.setBackgroundColor(Color.parseColor("#9B9B9B"))
    }
}