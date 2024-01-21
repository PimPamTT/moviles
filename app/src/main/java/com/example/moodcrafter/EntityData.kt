package com.example.moodcrafter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moodcrafter.databinding.ActivityEntityDataBinding

class EntityData : AppCompatActivity()
{
    enum class Params
    {
        registroC,
        p1,
        p2,
        p3
    }

    private lateinit var binding: ActivityEntityDataBinding

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityEntityDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val tit = "REGISTRO #" + intent.getStringExtra(MainActivity.Params.registroC.name)
        val el1 = intent.getStringExtra(MainActivity.Params.p1.name)
        val el2 = intent.getStringExtra(MainActivity.Params.p2.name)
        val el3 = intent.getStringExtra(MainActivity.Params.p3.name)

        val register = listOf<Register>(
            Register(
                tit,
                el1,
                el2,
                el3
            )
        )

        binding.registro.layoutManager =
            GridLayoutManager(this, 1, RecyclerView.VERTICAL, false)

        binding.registro.adapter = RegisterAdapter(
            register, this
        )

        binding.volver.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}