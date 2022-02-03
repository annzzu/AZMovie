package az.movie.az_movie.ui.fragment.movies

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import az.movie.az_movie.databinding.FragmentMoviesBinding
import az.movie.az_movie.extensions.*
import az.movie.az_movie.ui.base.BaseFragment
import az.movie.az_movie.ui.fragment.trailer.ClickIntCallBack
import az.movie.az_movie.util.response_handler.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MoviesFragment : BaseFragment<FragmentMoviesBinding>(FragmentMoviesBinding::inflate) {
    var series = false

    private val moviesViewModel: MoviesViewModel by viewModels()
    private lateinit var moviesTopAdapter: MoviesTopAdapter
    var clickIntCallBack: ClickIntCallBack? = null

    override fun setInfo() {
        initRV()
        viewLifecycleOwner.lifecycleScope.launch {
            moviesViewModel.getMoviesTopData(series)
            moviesViewModel.movieTopData.collectLatest {
                when (it) {
                    is Resource.Error -> {
                        binding.pbMovies.invisible()
                        binding.tvNothingFound.visible()
                        binding.tvNothingFound.text = getString(STRINGS.error)
                        binding.root.showSnackBar(it.message!!)
                    }
                    is Resource.Loading -> {
                        binding.pbMovies.visible()
                    }
                    is Resource.Success -> {
                        binding.pbMovies.invisible()
                        it.data?.data?.let { value ->
                            moviesTopAdapter.submitList(value)
                            binding.tvNothingFound.invisible()
                        } ?: run {
                            binding.tvNothingFound.visible()
                        }
                    }
                }
            }
        }
    }

    private fun initRV() {
        binding.rvMovies.apply {
            moviesTopAdapter = MoviesTopAdapter()
            adapter = moviesTopAdapter
            layoutManager =
                LinearLayoutManager(view?.context , LinearLayoutManager.HORIZONTAL , false)
        }
        moviesTopAdapter.clickIntCallBack = {
            clickIntCallBack?.invoke(it)
        }
    }

}