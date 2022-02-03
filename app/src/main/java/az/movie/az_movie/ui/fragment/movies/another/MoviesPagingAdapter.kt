package az.movie.az_movie.ui.fragment.movies.another

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import az.movie.az_movie.databinding.ItemMovieBinding
import az.movie.az_movie.extensions.setImageUrl
import az.movie.az_movie.model.moviesDataModel.Movie


typealias MovieIdCallBack = (movieId: Int) -> Unit

class MoviesPagingAdapter :
    PagingDataAdapter<Movie , MoviesPagingAdapter.ViewHolder>(diffCallback) {
    var clickMovieCallBack: MovieIdCallBack? = null

    override fun onCreateViewHolder(parent: ViewGroup , viewType: Int) =
        ViewHolder(
            ItemMovieBinding.inflate(
                LayoutInflater.from(parent.context) ,
                parent ,
                false
            )
        )

    override fun onBindViewHolder(
        holder: ViewHolder ,
        position: Int
    ) =
        holder.onBind(getItem(position)!!)

    inner class ViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(model: Movie) {
            binding.tvTitle.text = model.secondaryName ?: model.primaryName ?: model.originalName
            binding.imCover.setImageUrl(model.posters!!.data!!.size240)
            binding.root.setOnClickListener {
                clickMovieCallBack?.invoke(model.id!!)
            }
        }
    }


    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie , newItem: Movie): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Movie , newItem: Movie): Boolean =
                oldItem == newItem
        }
    }
}