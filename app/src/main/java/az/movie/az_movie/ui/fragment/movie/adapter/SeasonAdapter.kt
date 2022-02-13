package az.movie.az_movie.ui.fragment.movie.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.ListAdapter
import az.movie.az_movie.databinding.ItemSeasonBinding
import az.movie.az_movie.extensions.COLORS
import az.movie.az_movie.model.moviesDataModel.Season
import az.movie.az_movie.util.typealiases.ClickSeasonCallBack


class SeasonAdapter :
    ListAdapter<Season , RecyclerView.ViewHolder>(DiffCallback()) {

    var clickSeasonCallBack: ClickSeasonCallBack? = null

    override fun onCreateViewHolder(parent: ViewGroup , viewType: Int) =
        if (viewType == CHOSEN) {
            ChosenViewHolder(
                ItemSeasonBinding.inflate(
                    LayoutInflater.from(parent.context) ,
                    parent ,
                    false
                )
            )
        } else {
            ChooseViewHolder(
                ItemSeasonBinding.inflate(
                    LayoutInflater.from(parent.context) ,
                    parent ,
                    false
                )
            )
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder , position: Int) {
        if (holder is ChooseViewHolder) {
            holder.onBind(getItem(position))
        } else if (holder is ChosenViewHolder) {
            holder.onBind(getItem(position))
        }
    }

    override fun getItemViewType(position: Int) =
        if (getItem(position).choose) CHOSEN else CHOOSE

    inner class ChooseViewHolder(private val binding: ItemSeasonBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(model: Season) = with(binding) {
            tvTitle.text = model.title
            root.setOnClickListener {
                clickSeasonCallBack?.invoke(model.number , bindingAdapterPosition)
            }
            root.apply {
                setOnClickListener {
                    clickSeasonCallBack?.invoke(model.number , bindingAdapterPosition)
                }
            }
        }
    }

    inner class ChosenViewHolder(private val binding: ItemSeasonBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(model: Season) = with(binding) {
            tvTitle.text = model.title
            root.setBackgroundColor(this@ChosenViewHolder.itemView.context.resources.getColor(COLORS.green_d))
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Season>() {
        override fun areItemsTheSame(
            oldItem: Season ,
            newItem: Season
        ): Boolean =
            oldItem.movieId == newItem.movieId && oldItem.choose == newItem.choose

        override fun areContentsTheSame(
            oldItem: Season ,
            newItem: Season
        ): Boolean =
            oldItem == newItem  && oldItem.choose == newItem.choose
    }

    fun clickNotify(index: Int) = notifyItemChanged(index)

    companion object {
        const val CHOOSE = 1
        const val CHOSEN = 2
    }

}


