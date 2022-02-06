package az.movie.az_movie.ui.fragment.movies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.ListAdapter
import az.movie.az_movie.databinding.ItemMovieBinding
import az.movie.az_movie.extensions.setImageUrl
import az.movie.az_movie.domain.model.moviesDataModel.Movie
import az.movie.az_movie.util.typealiases.ClickIntCallBack


class MoviesTopAdapter : ListAdapter<Movie , MoviesTopAdapter.ViewHolder>(DiffCallback()) {

    var clickIntCallBack: ClickIntCallBack? = null

    override fun onCreateViewHolder(parent: ViewGroup , viewType: Int) =
        ViewHolder(
            ItemMovieBinding.inflate(
                LayoutInflater.from(parent.context) ,
                parent ,
                false
            )
        )

    override fun onBindViewHolder(holder: MoviesTopAdapter.ViewHolder , position: Int) {
        holder.onBind(getItem(position))
    }

    inner class ViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(model: Movie) = with(binding) {
            tvTitle.text = model.title
            imCover.setImageUrl(model.poster)
            root.setOnClickListener {
                clickIntCallBack?.invoke(model.id)
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie , newItem: Movie): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Movie , newItem: Movie): Boolean =
            oldItem == newItem
    }
}
