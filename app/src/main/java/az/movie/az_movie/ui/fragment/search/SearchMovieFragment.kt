package az.movie.az_movie.ui.fragment.search

import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import az.movie.az_movie.databinding.FragmentSearchMovieBinding
import az.movie.az_movie.extensions.*
import az.movie.az_movie.ui.base.BaseFragment
import az.movie.az_movie.ui.fragment.movies.MoviesTopAdapter
import az.movie.az_movie.util.response_handler.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchMovieFragment :
    BaseFragment<FragmentSearchMovieBinding>(FragmentSearchMovieBinding::inflate) {
    private lateinit var searchAdapter: MoviesTopAdapter
    private val searchViewModel: SearchViewModel by viewModels()

    override fun listener() {
        initSearch()
        initRV()
        imageAnimation()
    }

    private fun imageAnimation() = with(binding) {
        imMovie.getZoomAnimation()

    }

    private fun initSearch() = with(binding) {
        etSearch.doAfterTextChanged {
            if (!it.isNullOrBlank() && it.length > 2)
                observeSearchRequest(it.toString())
        }
    }

    override fun observer() {
        observeSearchCollector()
    }

    private fun observeSearchRequest(keywords: String) {
        viewLifecycleOwner.lifecycleScope.launch {
            searchViewModel.getSearch(keywords)
        }
    }

    private fun observeSearchCollector() {
        viewLifecycleOwner.lifecycleScope.launch {
            searchViewModel.searchMovie.collectLatest {
                with(binding) {
                    when (it) {
                        is Resource.Error -> {
                            pbMovies.invisible()
                            imMovie.visible()
                            rvMovies.invisible()
                            tvNothingFound.visible()
                            tvNothingFound.text = getString(STRINGS.error)
                            root.showSnackBar(it.message!!)
                        }
                        is Resource.Loading -> {
                            imMovie.visible()
                            rvMovies.invisible()
                            pbMovies.visible()
                        }
                        is Resource.Success -> {
                            rvMovies.visible()
                            imMovie.invisible()
                            pbMovies.invisible()
                            it.data?.data?.let { value ->
                                searchAdapter.submitList(value)
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
            searchAdapter = MoviesTopAdapter()
            adapter = searchAdapter
            layoutManager =
                GridLayoutManager(view?.context , 2 , LinearLayoutManager.HORIZONTAL , false)
        }
        searchAdapter.clickIntCallBack = {
            openMovie(it)
        }
    }

    private fun openMovie(movieId: Int) {
        findNavController().navigate(
            SearchMovieFragmentDirections.actionSearchMovieFragmentToNavigationMovie(movieId)
        )
    }
}