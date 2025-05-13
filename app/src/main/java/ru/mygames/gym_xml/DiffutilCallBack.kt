package ru.mygames.gym_xml

import androidx.recyclerview.widget.DiffUtil

class DiffutilCallBack : DiffUtil.ItemCallback<Workout>() {
    override fun areItemsTheSame(oldItem: Workout, newItem: Workout): Boolean {
        // Например, сравнение по ID
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Workout, newItem: Workout): Boolean {
        // Можно просто сравнить весь объект
        return oldItem == newItem
    }
}