package ru.mygames.gym_xml.presentation

import androidx.recyclerview.widget.DiffUtil
import ru.mygames.gym_xml.domain.workout.Workout

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