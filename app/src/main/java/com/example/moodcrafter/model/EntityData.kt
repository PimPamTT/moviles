package com.example.moodcrafter.model

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.moodcrafter.R
import com.example.moodcrafter.database.AppDatabase
import com.example.moodcrafter.databinding.ActivityEntityDataBinding

class EntityData : AppCompatActivity()
{
    private lateinit var binding: ActivityEntityDataBinding
    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityEntityDataBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.tlEntityData)

        /*
            val tit = "REGISTRO #" + intent.getStringExtra(MainActivity.Params.registroC.name)
            val el1 = intent.getStringExtra(MainActivity.Params.p1.name)
            val el2 = intent.getStringExtra(MainActivity.Params.p2.name)
            val el3 = intent.getStringExtra(MainActivity.Params.p3.name)

            val registers = listOf<Register>(
                Register(
                    tit,
                    el1,
                    el2,
                    el3
                )
            )
        */

        // Inicializamos y setup de la base de datos
        db = Room.databaseBuilder(this, AppDatabase::class.java, AppDatabase.DATABASE_NAME).allowMainThreadQueries().build()
        binding.registro.layoutManager = GridLayoutManager(this, 1, RecyclerView.VERTICAL, false)

        // Recogemos informacion de la base de datos
        val registers = db.registerDao().list()

        // Metemos el adapter
        binding.registro.adapter = RegisterAdapter(registers, this, db)

        /*
            // Volvemos a MainActivity
            binding.volver.setOnClickListener {
                // Iniciar la nueva actividad
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        */
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_entity, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId)
        {
            R.id.mInsertsEntityData -> {
                val intent = Intent(
                    this, MainActivity::class.java
                )
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onResume()
    {
        super.onResume()

        val adapter = binding.registro.adapter as? RegisterAdapter

        adapter?.registers = db.registerDao().list()

        adapter?.notifyDataSetChanged()
    }
}