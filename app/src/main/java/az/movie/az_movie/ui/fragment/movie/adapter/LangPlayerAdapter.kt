package az.movie.az_movie.ui.fragment.movie.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.ListAdapter
import az.movie.az_movie.databinding.ItemLangBinding
import az.movie.az_movie.model.playerDataModel.EpisodePlayer
import az.movie.az_movie.ui.base.ClickCallBack

class LangPlayerAdapter :
    ListAdapter<EpisodePlayer.File , LangPlayerAdapter.ViewHolder>(diffCallback) {

    var clickCallBack: ClickCallBack? = null

    override fun onCreateViewHolder(parent: ViewGroup , viewType: Int) =
        ViewHolder(
            ItemLangBinding.inflate(
                LayoutInflater.from(parent.context) ,
                parent ,
                false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder , position: Int) {
        holder.onBind(getItem(position))
    }

    inner class ViewHolder(private val binding: ItemLangBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(model: EpisodePlayer.File) = with(binding) {
            tvTitle.text = model.lang
            if (!model.file.isNullOrBlank()) {
                root.setOnClickListener {
                    clickCallBack?.invoke(model.file!!)
                }
            }

        }
    }

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<EpisodePlayer.File>() {
            override fun areItemsTheSame(
                oldItem: EpisodePlayer.File ,
                newItem: EpisodePlayer.File
            ): Boolean =
                oldItem.lang == newItem.lang

            override fun areContentsTheSame(
                oldItem: EpisodePlayer.File ,
                newItem: EpisodePlayer.File
            ): Boolean =
                oldItem == newItem
        }
    }

}


