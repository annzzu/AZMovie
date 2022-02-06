package az.movie.az_movie.ui.fragment.intro

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import az.movie.az_movie.databinding.FragmentNetworkBinding
import az.movie.az_movie.domain.model.introModel.IntroSlide

class IntroSliderAdapter(private val introSlides: List<IntroSlide>) :
    RecyclerView.Adapter<IntroSliderAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup , viewType: Int) =
        ViewHolder(
            FragmentNetworkBinding.inflate(
                LayoutInflater.from(parent.context) ,
                parent ,
                false
            )
        )

    override fun onBindViewHolder(
        holder: IntroSliderAdapter.ViewHolder ,
        position: Int
    ) = holder.onBind(introSlides[position])

    inner class ViewHolder(private val binding: FragmentNetworkBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(introSlide: IntroSlide) = with(binding) {
            tvTitle.text = introSlide.title
            tvDescription.text = introSlide.description
            icon.setImageResource(introSlide.icon)
        }
    }

    override fun getItemCount(): Int {
        return introSlides.size
    }
}