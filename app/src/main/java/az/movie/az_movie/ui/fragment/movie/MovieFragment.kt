package az.movie.az_movie.ui.fragment.movie

import androidx.navigation.fragment.navArgs
import az.movie.az_movie.databinding.FragmentMovieBinding
import az.movie.az_movie.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MovieFragment : BaseFragment<FragmentMovieBinding>(FragmentMovieBinding::inflate) {
    private val args: MovieFragmentArgs by navArgs()

    override fun setInfo() {
        binding.tvID.text = args.movieId.toString()
    }
}