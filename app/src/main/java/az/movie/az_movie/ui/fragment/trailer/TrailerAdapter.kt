package az.movie.az_movie.ui.fragment.trailer

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import az.movie.az_movie.data.remote.datasources.trailer.TrailerDataSource
import az.movie.az_movie.databinding.ItemTrailerLayoutBinding
import az.movie.az_movie.extensions.setImageUrl
import az.movie.az_movie.model.trailerDataModel.Trailer

//
class TrailerAdapter(private var trailerSlides: MutableList<Trailer>) : RecyclerView.Adapter<TrailerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup , viewType: Int) =
        ViewHolder(
            ItemTrailerLayoutBinding.inflate(
                LayoutInflater.from(parent.context) ,
                parent ,
                false
            )
        )

    override fun onBindViewHolder(
        holder: TrailerAdapter.ViewHolder ,
        position: Int
    ) = holder.onBind(trailerSlides[position])

    inner class ViewHolder(private val binding: ItemTrailerLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(model: Trailer) = with(binding) {
            tvTitle.text = model.secondaryName ?: model.primaryName ?: model.originalName
            icon.setImageUrl(model.posters?.data?.size240)
        }
    }

    override fun getItemCount(): Int {
        return trailerSlides.size
    }

    fun setData(trailers: List<Trailer>) {
        this.trailerSlides = trailers.toMutableList()
        notifyDataSetChanged()
    }
}