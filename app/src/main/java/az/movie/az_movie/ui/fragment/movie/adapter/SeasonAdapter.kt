package az.movie.az_movie.ui.fragment.movie.adapter

import android.util.Log.d
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.ListAdapter
import az.movie.az_movie.databinding.ItemSeasonBinding
import az.movie.az_movie.extensions.COLORS
import az.movie.az_movie.model.moviesDataModel.Season

typealias ClickSeasonCallBack = (episode: Int, position:Int) -> Unit

class SeasonAdapter :
    ListAdapter<Season , SeasonAdapter.ViewHolder>(DiffCallback()) {

    var clickSeasonCallBack: ClickSeasonCallBack? = null

    override fun onCreateViewHolder(parent: ViewGroup , viewType: Int) =
        ViewHolder(
            ItemSeasonBinding.inflate(
                LayoutInflater.from(parent.context) ,
                parent ,
                false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder , position: Int) {
        holder.onBind(getItem(position))
    }

    inner class ViewHolder(private val binding: ItemSeasonBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(model: Season) = with(binding) {
            tvTitle.text = model.title
           d("testing az", "${model.title} ${model.choose}")

            root.apply {
                if (model.choose)
                    setBackgroundColor(this@ViewHolder.itemView.context.resources.getColor(COLORS.green_d))
                setOnClickListener {
                    clickNotify(absoluteAdapterPosition)
                    clickSeasonCallBack?.invoke(model.number, absoluteAdapterPosition)
                }
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Season>() {
        override fun areItemsTheSame(
            oldItem: Season ,
            newItem: Season
        ): Boolean =
            oldItem.movieId == newItem.movieId

        override fun areContentsTheSame(
            oldItem: Season ,
            newItem: Season
        ): Boolean =
            oldItem.choose == newItem.choose
    }

    fun clickNotify(position: Int){
        notifyItemChanged(position)
    }
}


