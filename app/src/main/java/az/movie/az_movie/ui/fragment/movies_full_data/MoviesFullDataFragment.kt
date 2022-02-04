package az.movie.az_movie.ui.fragment.movies_full_data

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import az.movie.az_movie.MoviesFullDataFragmentArgs
import az.movie.az_movie.databinding.FragmentMoviesFullDataBinding
import az.movie.az_movie.extensions.gone
import az.movie.az_movie.ui.base.BaseFragment
import az.movie.az_movie.ui.fragment.movies.MoviesViewModel
import az.movie.az_movie.ui.fragment.movies_full_data.another.MoviesLoadingAdapter
import az.movie.az_movie.ui.fragment.movies_full_data.another.MoviesPagingAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MoviesFullDataFragment : BaseFragment<FragmentMoviesFullDataBinding>(FragmentMoviesFullDataBinding::inflate) {

    private val args: MoviesFullDataFragmentArgs by navArgs()
    private val moviesViewModel: MoviesViewModel by viewModels()
    private lateinit var moviesAdapter: MoviesPagingAdapter

    override fun setInfo() {
        initRV()
        viewLifecycleOwner.lifecycleScope.launch {
            moviesViewModel.moviesFlow(args.series).collectLatest { pagingData ->
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
            startLayoutAnimation()
        }
    }

    private fun openMovie(movieId: Int) {
//        findNavController().navigate(
//            MainFragmentDirections.openMovie(movieId)
//        )
    }
}