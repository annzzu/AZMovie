package az.movie.az_movie.ui.fragment.movie

import android.content.Intent
import android.net.Uri
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import az.movie.az_movie.databinding.FragmentMovieBinding
import az.movie.az_movie.extensions.*
import az.movie.az_movie.model.moviesDataModel.Movie
import az.movie.az_movie.model.moviesDataModel.Seasons
import az.movie.az_movie.ui.base.BaseFragment
import az.movie.az_movie.ui.fragment.movie.adapter.GenreAdapter
import az.movie.az_movie.ui.fragment.movie.adapter.PersonAdapter
import az.movie.az_movie.model.enums.LangType
import az.movie.az_movie.domain.response_handler.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MovieFragment : BaseFragment<FragmentMovieBinding>(FragmentMovieBinding::inflate) {

    private val args: MovieFragmentArgs by navArgs()
    private val movieViewModel: MovieViewModel by viewModels()
    private lateinit var genreAdapter: GenreAdapter
    private lateinit var actorAdapter: PersonAdapter
    private lateinit var directorAdapter: PersonAdapter

    override fun setInfo() {
        binding.rvGenres.apply {
            genreAdapter = GenreAdapter()
            adapter = genreAdapter
            layoutManager =
                LinearLayoutManager(view?.context , LinearLayoutManager.HORIZONTAL , false)
        }
        binding.rvActors.apply {
            actorAdapter = PersonAdapter()
            adapter = actorAdapter
            layoutManager =
                LinearLayoutManager(view?.context , LinearLayoutManager.HORIZONTAL , false)
        }
        binding.rvDirectors.apply {
            directorAdapter = PersonAdapter()
            adapter = directorAdapter
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
        ivCover.setImageUrl(movie.coverR)
        ivPoster.setImageUrl(movie.poster)
        tvTitle.text = movie.title
        ivPoster.clipToOutline = true
        btnImdb.setOnClickListener{
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse( movie.imdbUrl))
            this@MovieFragment.context?.startActivity(intent)
        }

        movie.genres?.data?.let {
            genreAdapter.submitList(movie.genres.data)
            rvGenres.startLayoutAnimation()
        } ?: run {
            rvGenres.invisible()
        }
        movie.actors?.data?.let {
            actorAdapter.submitList(it)
            rvActors.startLayoutAnimation()
        } ?: run {
            lActors.invisible()
        }
        movie.directors?.data?.let {
            directorAdapter.submitList(it)
            rvDirectors.startLayoutAnimation()
        } ?: run {
            lDirectors.invisible()
        }

        setPlot(movie)

        binding.btnStart.apply {
            if (movie.isMovie != null && movie.isMovie == true) {
                visible()
                setOnClickListener {
                    openMovieBottomSheet(movie.id , movie.title)
                }
            }
            if (movie.isMovie != null && movie.isMovie == false){
                visible()
                setOnClickListener {
                    openSeriesBottomSheet(movie.id , movie.title, movie.seasons!!)
                }
            }
        }
    }

    private fun setPlot(movie:Movie) = with(binding){
        if (!movie.plotFirst.isNullOrBlank()) {
            tvDescriptionText.text = movie.plotFirst
            movie.plot(LangType.GEO)?.let { plot->
                btnGeo.setOnClickListener {
                    tvDescriptionText.text = plot
                }
            } ?: run {
                btnGeo.invisible()
            }
            movie.plot(LangType.ENG)?.let { plot->
                btnEng.setOnClickListener {
                    tvDescriptionText.text = plot
                }
            } ?: run {
                btnEng.invisible()
            }
            movie.plot(LangType.RUS)?.let { plot->
                btnRus.setOnClickListener {
                    tvDescriptionText.text = plot
                }
            } ?: run {
                btnRus.invisible()
            }
        }
    }

    private fun openMovieBottomSheet(movieId: Int , title: String) {
        findNavController().navigate(
            MovieFragmentDirections.actionNavigationMovieToMovieBottomSheet(
                movieId , title
            )
        )
    }

    private fun openSeriesBottomSheet(movieId: Int , title: String, seasons: Seasons) {
        findNavController().navigate(
            MovieFragmentDirections.actionNavigationMovieToSeriesBottomSheet(
                movieId , title, seasons
            )
        )
    }

}