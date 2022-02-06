package az.movie.az_movie.ui.fragment.movies

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import az.movie.az_movie.databinding.FragmentMoviesBinding
import az.movie.az_movie.extensions.*
import az.movie.az_movie.ui.base.BaseFragment
import az.movie.az_movie.domain.response_handler.Resource
import az.movie.az_movie.util.typealiases.ClickIntCallBack
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
    }

    override fun listener() {
        binding.btnRetry.setOnClickListener {
            observeMovieData()
        }
    }

    override fun observer() {
        observeMovieData()
        observeMovieCollector()
    }

    private fun observeMovieData() {
        viewLifecycleOwner.lifecycleScope.launch {
            moviesViewModel.getMoviesTopData(series)
        }
    }

    private fun observeMovieCollector() {
        viewLifecycleOwner.lifecycleScope.launch {
            moviesViewModel.movieTopData.collectLatest {
                with(binding) {
                    when (it) {
                        is Resource.Error -> {
                            pbMovies.invisible()
                            tvNothingFound.visible()
                            tvNothingFound.text = getString(STRINGS.error)
                            btnRetry.visible()
                            root.showSnackBar(it.message!!)
                        }
                        is Resource.Loading -> {
                            pbMovies.visible()
                            btnRetry.invisible()
                        }
                        is Resource.Success -> {
                            pbMovies.invisible()
                            btnRetry.invisible()
                            it.data?.data?.let { value ->
                                moviesTopAdapter.submitList(value)
                                rvMovies.startLayoutAnimation()
                                tvNothingFound.invisible()
                            } ?: run {
                                tvNothingFound.visible()
                            }
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
            startLayoutAnimation()
        }
        moviesTopAdapter.clickIntCallBack = {
            clickIntCallBack?.invoke(it)
        }
    }

    override fun stop() {}
}