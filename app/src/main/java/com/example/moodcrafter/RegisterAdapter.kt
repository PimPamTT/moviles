package com.example.moodcrafter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moodcrafter.databinding.RegisterLayoutBinding

class RegisterAdapter (val registers: List<Register>, val context: Context) : RecyclerView.Adapter<RegisterAdapter.ItemViewHolder>()
{
    private val layoutInflater = LayoutInflater.from(context)

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder
    {
        return ItemViewHolder(
            layoutInflater.inflate(R.layout.register_layout, null)
        )
    }

    override fun getItemCount(): Int
    {
        return registers.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int)
    {
        val register = registers[position]
        val binding = RegisterLayoutBinding.bind(holder.itemView)

        binding.titulo.text = register.titulo
        binding.otraeleccion1.text = register.eleccion1
        binding.otraeleccion2.text = register.eleccion2
        binding.otraeleccion3.text = register.eleccion3
    }
}