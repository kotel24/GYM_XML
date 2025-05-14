package ru.mygames.gym_xml.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView
import ru.mygames.gym_xml.presentation.DiffutilCallBack
import ru.mygames.gym_xml.data.FavoriteExercise
import ru.mygames.gym_xml.R
import ru.mygames.gym_xml.domain.workout.Workout

class FavoriteAdapter(
    private val context: Context,
    private val onLikeClick: (Workout, Boolean) -> Unit
) : ListAdapter<Workout, FavoriteAdapter.ViewHolder>(DiffutilCallBack()) {

    private val favoriteIds = mutableSetOf<Int>()

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image: ShapeableImageView = view.findViewById(R.id.pic)
        val title: TextView = view.findViewById(R.id.titleTxt)
        val muscles: TextView = view.findViewById(R.id.musclesTxt)
        val bodyPart: TextView = view.findViewById(R.id.muscles_secondaryTxt)
        val equipment: TextView = view.findViewById(R.id.equipment)
        val info: TextView = view.findViewById(R.id.infoTxt)
        val likeIcon: ImageView = view.findViewById(R.id.heartIcon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.viewholder_otherworkout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        val exerciseId = item.id

        Glide.with(context)
            .load(item.gifUrl)
            .placeholder(R.drawable.add)
            .into(holder.image)

        holder.title.text = item.name
        holder.equipment.text = item.equipment
        holder.muscles.text = item.secondaryMuscles
        holder.bodyPart.text = item.bodyPart
        holder.info.text = item.instructions

        val isFavorite = favoriteIds.contains(exerciseId)
        holder.likeIcon.setImageResource(
            if (isFavorite) R.drawable.baseline_favorite_24
            else R.drawable.baseline_favorite_border_24
        )

        holder.likeIcon.setOnClickListener {
            val newState = !isFavorite
            onLikeClick(item, newState)

            if (newState) {
                favoriteIds.add(exerciseId)
                holder.likeIcon.setImageResource(R.drawable.baseline_favorite_24)
            } else {
                favoriteIds.remove(exerciseId)
                holder.likeIcon.setImageResource(R.drawable.baseline_favorite_border_24)
            }
        }
    }

    fun updateFavorites(favorites: List<FavoriteExercise>) {
        favoriteIds.clear()
        favoriteIds.addAll(favorites.map { it.id })
        notifyDataSetChanged()
    }
}