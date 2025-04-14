package ru.mygames.gym_xml

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView
import com.squareup.picasso.Picasso
import ru.mygames.gym_xml.domain.Workout

class WorkoutAdapter(
    private val onItemClick: (Workout) -> Unit
) : RecyclerView.Adapter<WorkoutAdapter.ViewHolder>() {

    private var items = emptyList<Workout>()

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image: ShapeableImageView = view.findViewById(R.id.pic)
        val title: TextView = view.findViewById(R.id.titleTxt)
        val muscles: TextView = view.findViewById(R.id.musclesTxt)
        val muscles_secondary: TextView = view.findViewById(R.id.muscles_secondaryTxt)
        val equipment: TextView = view.findViewById(R.id.equipment)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.viewholder_workout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]

        // Загрузка изображения
        if (item.images.isNotEmpty()) {
            Picasso.get()
                .load(item.images.first())
                .placeholder(R.drawable.add)
                .into(holder.image)
        }
        if (item.category.isNotEmpty()) holder.title.text = item.category
        if (item.equipment!=null) holder.equipment.text = item.equipment.toString()
        if (item.muscles.isNotEmpty())holder.muscles.text = item.muscles.toString()
        if (item.muscles_secondary.isNotEmpty())holder.muscles_secondary.text = item.muscles_secondary.first()

        holder.itemView.setOnClickListener { onItemClick(item) }
    }

    override fun getItemCount() = items.size

    fun submitList(newItems: List<Workout>) {
        items = newItems
        notifyDataSetChanged()
    }
}