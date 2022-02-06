package az.movie.az_movie.ui.fragment.movie.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.*
import az.movie.az_movie.databinding.ItemPlayerBinding
import az.movie.az_movie.extensions.setImageUrl
import az.movie.az_movie.extensions.visible
import az.movie.az_movie.model.playerDataModel.EpisodePlayer
import az.movie.az_movie.ui.base.ClickCallBack

class SeriesAdapter :
    ListAdapter<EpisodePlayer , SeriesAdapter.ViewHolder>(DiffCallback()) {

    var clickCallBack: ClickCallBack? = null
    private lateinit var langPlayerAdapter: LangPlayerAdapter

    override fun onCreateViewHolder(parent: ViewGroup , viewType: Int) =
        ViewHolder(
            ItemPlayerBinding.inflate(
                LayoutInflater.from(parent.context) ,
                parent ,
                false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder , position: Int) {
        holder.onBind(getItem(position))
    }

    inner class ViewHolder(private val binding: ItemPlayerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(model: EpisodePlayer) = with(binding) {
            tvEpisodeTitle.text = model.episodeTitle
            cvItemPlayer.visible()
            tvEpisodeTitle.text = model.episodeTitle
            ivPoster.setImageUrl(model.cover)
            rvLang.apply {
                langPlayerAdapter = LangPlayerAdapter()
                adapter = langPlayerAdapter
                layoutManager =
                    GridLayoutManager(this@ViewHolder.itemView.context , 2, LinearLayoutManager.HORIZONTAL , false)
            }
            langPlayerAdapter.submitList(model.files)
            langPlayerAdapter.clickCallBack = {
                clickCallBack?.invoke(it)
            }
            rvLang.startLayoutAnimation()
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<EpisodePlayer>() {
        override fun areItemsTheSame(
            oldItem: EpisodePlayer ,
            newItem: EpisodePlayer
        ): Boolean =
            oldItem.episode == newItem.episode

        override fun areContentsTheSame(
            oldItem: EpisodePlayer ,
            newItem: EpisodePlayer
        ): Boolean =
            oldItem == newItem
    }

}