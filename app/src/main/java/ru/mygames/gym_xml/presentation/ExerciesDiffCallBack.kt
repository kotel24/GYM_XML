package ru.mygames.gym_xml.presentation

import androidx.recyclerview.widget.DiffUtil
import ru.mygames.gym_xml.domain.home_exercies.Exercies

class ExerciesDiffCallBack:DiffUtil.ItemCallback<Exercies> (){
    override fun areItemsTheSame(oldItem: Exercies, newItem: Exercies): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Exercies, newItem: Exercies): Boolean {
        return oldItem == newItem
    }
}