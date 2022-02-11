package az.movie.az_movie.ui.fragment.trailer

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import az.movie.az_movie.databinding.ItemTrailerLayoutBinding
import az.movie.az_movie.extensions.setImageUrl
import az.movie.az_movie.domain.model.trailerDataModel.Trailer
import az.movie.az_movie.util.typealiases.ClickIntCallBack

class TrailerAdapter() : RecyclerView.Adapter<TrailerAdapter.ViewHolder>() {

    private var trailerSlides = mutableListOf<Trailer>()

    var clickIntCallBack: ClickIntCallBack? = null

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
            tvTitle.text = model.title
            icon.setImageUrl(model.cover)
            root.setOnClickListener {
                clickIntCallBack?.invoke(model.id)
            }

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