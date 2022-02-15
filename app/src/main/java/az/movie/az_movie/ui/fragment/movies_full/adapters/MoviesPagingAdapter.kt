package az.movie.az_movie.ui.fragment.movies_full.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import az.movie.az_movie.databinding.ItemMovieGridBinding
import az.movie.az_movie.extensions.setImageUrl
import az.movie.az_movie.model.moviesDataModel.Movie
import az.movie.az_movie.util.typealiases.ClickIntCallBack


class MoviesPagingAdapter :
    PagingDataAdapter<Movie , MoviesPagingAdapter.ViewHolder>(diffCallback) {
    var clickIntCallBack: ClickIntCallBack? = null

    override fun onCreateViewHolder(parent: ViewGroup , viewType: Int) =
        ViewHolder(
            ItemMovieGridBinding.inflate(
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

    inner class ViewHolder(private val binding: ItemMovieGridBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(model: Movie) = with (binding) {
            tvTitle.text = model.title
            imCover.setImageUrl(model.poster)
            root.setOnClickListener {
                clickIntCallBack?.invoke(model.id)
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