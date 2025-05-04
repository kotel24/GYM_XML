package ru.mygames.gym_xml

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView
class WorkoutAdapterFullScreen (
    private val context: Context,
    private val onItemClick: (Workout) -> Unit,
) : RecyclerView.Adapter<WorkoutAdapterFullScreen.ViewHolder>() {

    private var items = emptyList<Workout>()

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image: ShapeableImageView = view.findViewById(R.id.pic)
        val title: TextView = view.findViewById(R.id.titleTxt)
        val muscles: TextView = view.findViewById(R.id.musclesTxt)
        val bodyPart: TextView = view.findViewById(R.id.muscles_secondaryTxt)
        val equipment: TextView = view.findViewById(R.id.equipment)
        val info: TextView = view.findViewById(R.id.infoTxt)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.viewholder_otherworkout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        var s = ""
        for (i in item.instructions)
            s = s + i + "\n"
        // Загрузка изображения
        Glide.with(context)
            .load(item.gifUrl)
            .placeholder(R.drawable.add)
            .into(holder.image)
        holder.title.text = item.name
        holder.equipment.text = item.equipment
        holder.muscles.text = item.secondaryMuscles.first()
        holder.bodyPart.text = item.bodyPart
        holder.info.text = s
        holder.itemView.setOnClickListener { onItemClick(item) }

    }

    override fun getItemCount() = items.size

    fun submitList(newItems: List<Workout>) {
        items = newItems
        notifyDataSetChanged()
    }
}