package az.movie.az_movie.ui.fragment.movie.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.ListAdapter
import az.movie.az_movie.databinding.ItemGenreBinding
import az.movie.az_movie.model.moviesDataModel.Genres


class GenreAdapter : ListAdapter<Genres.Data , GenreAdapter.ViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup , viewType: Int) =
        ViewHolder(
            ItemGenreBinding.inflate(
                LayoutInflater.from(parent.context) ,
                parent ,
                false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder , position: Int) {
        holder.onBind(getItem(position))
    }

    inner class ViewHolder(private val binding: ItemGenreBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(model: Genres.Data) = with(binding) {
            tvTitle.text = model.secondaryName ?: model.primaryName ?: model.tertiaryName
        }
    }

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<Genres.Data>() {
            override fun areItemsTheSame(oldItem: Genres.Data , newItem: Genres.Data): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Genres.Data , newItem: Genres.Data): Boolean =
                oldItem == newItem
        }
    }

}


