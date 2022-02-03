package az.movie.az_movie.ui.fragment.movies

import android.util.Log.d
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import az.movie.az_movie.databinding.FragmentMoviesBinding
import az.movie.az_movie.extensions.gone
import az.movie.az_movie.ui.base.BaseFragment
import az.movie.az_movie.ui.fragment.movies.another.MoviesLoadingAdapter
import az.movie.az_movie.ui.fragment.movies.another.MoviesPagingAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MoviesFragment : BaseFragment<FragmentMoviesBinding>(FragmentMoviesBinding::inflate) {
    var series = false

    private val moviesViewModel: MoviesViewModel by viewModels()
    private lateinit var moviesAdapter: MoviesPagingAdapter

    override fun setInfo() {
        initRV()
        viewLifecycleOwner.lifecycleScope.launch {
            moviesViewModel.moviesFlow(series).collectLatest { pagingData ->
                binding.pbMovies.gone()
                moviesAdapter.submitData(pagingData)
            }
        }
    }

    private fun initRV() {
        binding.rvMovies.apply {
            moviesAdapter = MoviesPagingAdapter().apply {
                clickMovieCallBack = {
                    openMovie(it)
                }
            }
            adapter = moviesAdapter.withLoadStateFooter(
                MoviesLoadingAdapter(moviesAdapter)
            )
            layoutManager =
                LinearLayoutManager(view?.context , LinearLayoutManager.HORIZONTAL , false)
        }
    }

    private fun openMovie(movieId: Int) {
//        findNavController().navigate(
//            MainFragmentDirections.openMovie(movieId)
//        )
    }
}