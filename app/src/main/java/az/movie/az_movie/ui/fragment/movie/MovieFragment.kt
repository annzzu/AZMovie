package az.movie.az_movie.ui.fragment.movie

import android.util.Log.d
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import az.movie.az_movie.databinding.FragmentMovieBinding
import az.movie.az_movie.extensions.STRINGS
import az.movie.az_movie.extensions.invisible
import az.movie.az_movie.extensions.setImageUrl
import az.movie.az_movie.extensions.visible
import az.movie.az_movie.model.moviesDataModel.Movie
import az.movie.az_movie.model.moviesDataModel.Plots
import az.movie.az_movie.ui.base.BaseFragment
import az.movie.az_movie.util.LangType
import az.movie.az_movie.util.response_handler.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MovieFragment : BaseFragment<FragmentMovieBinding>(FragmentMovieBinding::inflate) {

    private val args: MovieFragmentArgs by navArgs()
    private val movieViewModel: MovieViewModel by viewModels()
    private lateinit var genreAdapter: GenreAdapter

    override fun setInfo() {
        binding.rvGenres.apply {
            genreAdapter = GenreAdapter()
            adapter = genreAdapter
            layoutManager =
                LinearLayoutManager(view?.context , LinearLayoutManager.HORIZONTAL , false)
        }
    }

    override fun observer() {
        viewLifecycleOwner.lifecycleScope.launch {
            movieViewModel.getMovie(args.movieId)
        }
        viewLifecycleOwner.lifecycleScope.launch {
            movieViewModel.movie.collectLatest { movie ->
                with(binding) {
                    when (movie) {
                        is Resource.Error -> {
                            tvError.visible()
                            tvError.text = getString(STRINGS.error)
                            pbMovie.invisible()
                        }
                        is Resource.Loading -> {
                            tvError.invisible()
                            pbMovie.visible()
                        }
                        is Resource.Success -> {
                            pbMovie.invisible()
                            movie.data?.data?.let { movie ->
                                setMovie(movie)
                                tvError.invisible()
                            } ?: run {
                                tvError.visible()
                                tvError.text = getString(STRINGS.nothing_found)
                            }
                        }
                    }
                }
            }
        }
    }


    private fun setMovie(movie: Movie) = with(binding) {
        ivCover.setImageUrl(
            movie.covers?.data?.size1920 ?: movie.covers?.data?.size1050
            ?: movie.posters?.data?.size240
        )
        ivPoster.setImageUrl(
            movie.posters?.data?.size240 ?: movie.covers?.data?.size1920
            ?: movie.covers?.data?.size1050
        )
        tvTitle.text = movie.secondaryName ?: movie.primaryName ?: movie.originalName
        ivPoster.clipToOutline = true
        movie.genres?.data?.let {
            genreAdapter.submitList(movie.genres.data)
        } ?: run {
            rvGenres.invisible()
        }
        if (movie.plots?.data?.isNotEmpty() == true) {
            movie.plots.data.let { plots ->
                tvDescriptionText.text = plots[0].description
                anyPlots(plots , LangType.GEO)?.let {
                    btnGeo.setOnClickListener {
                        tvDescriptionText.text = filterPlots(plots , LangType.GEO)
                    }
                } ?: run {
                    btnGeo.invisible()
                }
                anyPlots(plots , LangType.ENG)?.let {
                    btnEng.setOnClickListener {
                        tvDescriptionText.text = filterPlots(plots , LangType.ENG)
                    }
                } ?: run {
                    btnEng.invisible()
                }
                anyPlots(plots , LangType.RUS)?.let {
                    btnRus.setOnClickListener {
                        tvDescriptionText.text = filterPlots(plots , LangType.RUS)
                    }
                } ?: run {
                    btnRus.invisible()
                }
            }
        }
    }

    private fun filterPlots(plots: List<Plots.Data> , lang: LangType) =
        plots.filter { plot -> plot.language == lang.name }[0].description

    private fun anyPlots(plots: List<Plots.Data> , lang: LangType) =
        if (plots.any { plot -> plot.language == lang.name })
            plots.any { plot -> plot.language == lang.name }
        else null
}