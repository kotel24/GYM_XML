package ru.mygames.gym_xml

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

class WorkoutAdapterFullScreen(
    private val context: Context,
    private val onLikeClick: (Workout, Boolean) -> Unit,
    private var favoriteIds: Set<Int> = emptySet() // просто список ID в избранном
) : ListAdapter<Workout, WorkoutAdapterFullScreen.ViewHolder>(DiffutilCallBack()) {

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

        with(holder) {
            title.text = item.name
            equipment.text = item.equipment
            muscles.text = item.secondaryMuscles
            bodyPart.text = item.bodyPart
            info.text = item.instructions

            Glide.with(context)
                .load(item.gifUrl)
                .placeholder(R.drawable.add)
                .into(image)

            val isFavorite = favoriteIds.contains(item.id)
            likeIcon.setImageResource(
                if (isFavorite) R.drawable.baseline_favorite_24
                else R.drawable.baseline_favorite_border_24
            )

            likeIcon.setOnClickListener {
                onLikeClick(item, isFavorite)
            }
        }
    }

    fun updateFavorites(favorites: List<FavoriteExercise?>) {
        favoriteIds = favorites.map { it!!.id }.toSet()
        notifyDataSetChanged() // обновляем иконки избранного
    }
}