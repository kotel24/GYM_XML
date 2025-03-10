package ru.mygames.gym_xml.presentation

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.mygames.gym_xml.R

class ExerciesViewHolder(view: View):RecyclerView.ViewHolder(view) {
    val tvName = view.findViewById<TextView>(R.id.tv_name)
    val tvCount = view.findViewById<TextView>(R.id.tv_count)
}