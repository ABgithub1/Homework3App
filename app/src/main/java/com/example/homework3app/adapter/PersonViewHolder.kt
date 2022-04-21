package com.example.homework3app.adapter

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.homework3app.data.Person
import com.example.homework3app.databinding.ItemBinding

class PersonViewHolder(private val binding: ItemBinding, private val itemClick: (Person) -> Unit) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Person) {
        binding.name.text = item.name
        binding.nickname.text = item.nickname
        binding.personImage.load(item.img)
        binding.root.setOnClickListener {
            itemClick(item)
        }
    }
}