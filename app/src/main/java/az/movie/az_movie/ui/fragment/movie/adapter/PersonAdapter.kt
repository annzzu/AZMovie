package az.movie.az_movie.ui.fragment.movie.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.ListAdapter
import az.movie.az_movie.databinding.ItemGenreBinding
import az.movie.az_movie.databinding.ItemPersonBinding
import az.movie.az_movie.extensions.setImageUrl
import az.movie.az_movie.model.moviesDataModel.Genres
import az.movie.az_movie.model.moviesDataModel.Person


class PersonAdapter : ListAdapter<Person , PersonAdapter.ViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup , viewType: Int) =
        ViewHolder(
            ItemPersonBinding.inflate(
                LayoutInflater.from(parent.context) ,
                parent ,
                false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder , position: Int) {
        holder.onBind(getItem(position))
    }

    inner class ViewHolder(private val binding: ItemPersonBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(model: Person) = with(binding) {
            tvName.text = model.name
            binding.imPerson.setImageUrl(model.poster)
        }
    }

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<Person>() {
            override fun areItemsTheSame(oldItem: Person , newItem: Person): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Person , newItem: Person): Boolean =
                oldItem == newItem
        }
    }

}


