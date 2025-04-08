package ru.mygames.gym_xml

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView
import ru.mygames.gym_xml.domain.Workout

class WorkoutAdapter(
    private val onItemClick: (Workout) -> Unit
) : RecyclerView.Adapter<WorkoutAdapter.ViewHolder>() {

    private var items = emptyList<Workout>()

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image: ShapeableImageView = view.findViewById(R.id.pic)
        val title: TextView = view.findViewById(R.id.titleTxt)
        val duration: TextView = view.findViewById(R.id.durationTxt)
        val exerciseType: TextView = view.findViewById(R.id.excerciseTxt)
        val kcal: TextView = view.findViewById(R.id.kcalTxt)
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
            Glide.with(holder.itemView)
                .load(item.images.first().image)
                .placeholder(R.drawable.add)
                .into(holder.image)
        }

        holder.title.text = item.name
        holder.duration.text = item.duration
        holder.exerciseType.text = item.category
        holder.kcal.text = item.calories

        holder.itemView.setOnClickListener { onItemClick(item) }
    }

    override fun getItemCount() = items.size

    fun submitList(newItems: List<Workout>) {
        items = newItems
        notifyDataSetChanged()
    }
}