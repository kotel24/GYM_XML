package ru.mygames.gym_xml.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import ru.mygames.gym_xml.R
import ru.mygames.gym_xml.domain.Exercies

class ExerciesAdapter: ListAdapter<Exercies, ExerciesViewHolder>(ExerciesDiffCallBack()) {

    var onExerciesItemLongClickListener:((Exercies)-> Unit)? = null
    var onExerciesItemClickListener:((Exercies)-> Unit)? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciesViewHolder {
        val layout = when (viewType) {
            VIEW_TYPE_ENABLED-> R.layout.item_exercies_enabled
            VIEW_TYPE_DISABLED->R.layout.item_exercies_disabled
            else -> throw RuntimeException("Unknow view type: $viewType")
        }
        val view = LayoutInflater.from(parent.context).inflate(
            layout,
            parent,
            false
        )
        return ExerciesViewHolder(view)
    }

    override fun onBindViewHolder(holder: ExerciesViewHolder, position: Int) {
        val item = getItem(position)
        holder.tvName.text = item.name
        holder.tvCount.text = item.durationOrCount
        holder.itemView.setOnLongClickListener{
            onExerciesItemLongClickListener?.invoke(item)
            true
        }
        holder.itemView.setOnClickListener {
            onExerciesItemClickListener?.invoke(item)
        }

    }

    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        if (item.enabled) return VIEW_TYPE_ENABLED
        else return VIEW_TYPE_DISABLED
    }

    companion object {
        const val VIEW_TYPE_ENABLED = 1
        const val VIEW_TYPE_DISABLED = 0
        const val MAX_POOL_SIZE = 30
    }
}