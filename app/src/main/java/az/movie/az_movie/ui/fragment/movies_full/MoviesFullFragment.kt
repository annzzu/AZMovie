package az.movie.az_movie.ui.fragment.movies_full

import android.graphics.Color
import android.widget.ArrayAdapter
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import az.movie.az_movie.R
import az.movie.az_movie.databinding.FragmentMoviesFullBinding
import az.movie.az_movie.extensions.getZoomAnimation
import az.movie.az_movie.extensions.showSnackBar
import az.movie.az_movie.model.enums.MoviePeriodType
import az.movie.az_movie.ui.base.BaseFragment
import az.movie.az_movie.ui.fragment.movies_full.adapters.MoviesLoadStateAdapter
import az.movie.az_movie.ui.fragment.movies_full.adapters.MoviesPagingAdapter
import com.google.android.material.textfield.TextInputLayout
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MoviesFullFragment :
    BaseFragment<FragmentMoviesFullBinding>(FragmentMoviesFullBinding::inflate) {
    private val args: MoviesFullFragmentArgs by navArgs()
    private val moviesFullViewModel: MoviesFullViewModel by viewModels()
    private lateinit var moviesPagingAdapter: MoviesPagingAdapter

    override fun setInfo() {
        initRV()
        setSearch()
        binding.imMovie.getZoomAnimation()
    }

    private fun setSearch() = with(binding) {
        mTIL.endIconMode = TextInputLayout.END_ICON_DROPDOWN_MENU
        etSearch.setBackgroundColor(Color.WHITE)

        etSearch.isClickable = false
        val adapter =
            ArrayAdapter(
                binding.root.context , R.layout.chooser_item ,
                enumValues<MoviePeriodType>().toList()
            )
        (etSearch).setAdapter(adapter)

        etSearch.doAfterTextChanged { text ->
            observeTrailersData(text.toString().lowercase())
        }
    }

    private fun initRV() {
        binding.rvMovies.apply {
            moviesPagingAdapter = MoviesPagingAdapter().apply {
                clickIntCallBack = { openMovie(it) }
            }
            adapter = moviesPagingAdapter.withLoadStateHeaderAndFooter(
                header = MoviesLoadStateAdapter { moviesPagingAdapter.retry() } ,
                footer = MoviesLoadStateAdapter { moviesPagingAdapter.retry() }
            )

            layoutManager =
                GridLayoutManager(view?.context , 2 , LinearLayoutManager.VERTICAL , false)
        }
    }


    private fun observeTrailersData(period: String) {
        binding.retryButton.setOnClickListener { moviesPagingAdapter.retry() }
        observeSearchMovies(period)
        observeLoadStateFlow()
    }

    private fun observeSearchMovies(period: String) = with(binding) {
        viewLifecycleOwner.lifecycleScope.launch {
            moviesFullViewModel.searchMovies(args.movie , period)
                .collectLatest { pagingData ->
                    moviesPagingAdapter.submitData(pagingData)
                    rvMovies.startLayoutAnimation()
                }
        }
    }

    private fun observeLoadStateFlow() = with(binding) {
        viewLifecycleOwner.lifecycleScope.launch {
            moviesPagingAdapter.loadStateFlow.collectLatest { loadState ->
                val isListEmpty =
                    loadState.refresh is LoadState.NotLoading && moviesPagingAdapter.itemCount == 0
                val loading =
                    loadState.source.refresh is LoadState.Loading || loadState.source.refresh is LoadState.Error
                tvNothingFound.isVisible = isListEmpty
                rvMovies.isVisible = !isListEmpty
                imMovie.apply {
                    isVisible = loading
                    isInvisible = !loading
                }
                pbMovies.isVisible = loadState.source.refresh is LoadState.Loading
                retryButton.isVisible = loadState.source.refresh is LoadState.Error
                val errorState = loadState.source.append as? LoadState.Error
                    ?: loadState.source.prepend as? LoadState.Error
                    ?: loadState.append as? LoadState.Error
                    ?: loadState.prepend as? LoadState.Error

                errorState?.let {
                    root.showSnackBar(it.toString())
                }
            }

        }
    }

    private fun openMovie(movieId: Int) {
        findNavController().navigate(
            MoviesFullFragmentDirections.actionNavigationMainToNavigationMovie(
                movieId
            )
        )
    }
}