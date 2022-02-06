package az.movie.az_movie.ui.fragment.movie.bottom_sheet

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import az.movie.az_movie.databinding.FragmentSeriesBottomSheetBinding
import az.movie.az_movie.extensions.STRINGS
import az.movie.az_movie.extensions.invisible
import az.movie.az_movie.extensions.visible
import az.movie.az_movie.ui.fragment.movie.PlayerViewModel
import az.movie.az_movie.ui.base.BaseBottomSheet
import az.movie.az_movie.ui.fragment.movie.adapter.SeasonAdapter
import az.movie.az_movie.ui.fragment.movie.adapter.SeriesAdapter
import az.movie.az_movie.util.response_handler.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class SeriesBottomSheet :
    BaseBottomSheet<FragmentSeriesBottomSheetBinding>(FragmentSeriesBottomSheetBinding::inflate) {

    private val args: SeriesBottomSheetArgs by navArgs()
    private val playerViewModel: PlayerViewModel by viewModels()
    private lateinit var seasonAdapter: SeasonAdapter
    private lateinit var seriesAdapter: SeriesAdapter

    override fun init() {
        setData()
        observe()
    }

    private fun observe() {
        viewLifecycleOwner.lifecycleScope.launch {
            observeSeriesRequest(args.movieId , MOVIE_SEASON)
            obServeSeriesCollector()
        }
    }

    private fun observeSeriesRequest(movieId: Int , movieSeason: Int) {
        viewLifecycleOwner.lifecycleScope.launch {
            playerViewModel.getMovie(movieId , movieSeason)
        }
    }

    private fun obServeSeriesCollector() {
        viewLifecycleOwner.lifecycleScope.launch {
            playerViewModel.movie.collectLatest { movie ->
                with(binding) {
                    when (movie) {
                        is Resource.Error -> {
                            tvSeason.text = getString(STRINGS.error)
                            pbMovie.invisible()
                        }
                        is Resource.Loading -> {
                            rvSeries.invisible()
                            pbMovie.visible()
                        }
                        is Resource.Success -> {
                            pbMovie.invisible()
                            movie.data?.let { episodes ->
                                if (episodes.data != null && episodes.data.isNotEmpty()) {
                                    binding.rvSeries.visible()
                                    binding.tvSeries.visible()
                                    seriesAdapter.submitList(episodes.data)
                                } else {
                                    binding.rvSeries.invisible()
                                    binding.tvSeries.invisible()
                                }
                            } ?: run {
                                tvSeason.visible()
                                tvSeason.text = getString(STRINGS.nothing_found)
                            }
                        }
                    }
                }
            }
        }
    }

    private fun setData() = with(binding) {
        tvTitle.text = args.title
        pbMovie.invisible()
        initRvSeason()
        initRvSeries()
    }

    private fun initRvSeason() {
        binding.rvSeason.apply {
            seasonAdapter = SeasonAdapter()
            adapter = seasonAdapter
            layoutManager =
                LinearLayoutManager(view?.context , LinearLayoutManager.HORIZONTAL , false)
            args.seasons.clickSeason(1)
            seasonAdapter.submitList(args.seasons.data!!)
            seasonAdapter.clickSeasonCallBack = { season , position ->
                if (season != args.seasons.lastNum()) {
                    val lastIndex = args.seasons.lastIndex()
                    args.seasons.clickSeason(season)
                    lastIndex?.let { seasonAdapter.clickNotify(it) }
                    seasonAdapter.clickNotify(position)
                    observeSeriesRequest(args.movieId , season)
                }
            }
            startLayoutAnimation()
        }
    }

    private fun initRvSeries() {
        binding.rvSeries.apply {
            seriesAdapter = SeriesAdapter()
            adapter = seriesAdapter
            layoutManager =
                LinearLayoutManager(view?.context , LinearLayoutManager.HORIZONTAL , false)
            seriesAdapter.clickStringsCallBack = { file , subtitle -> openMovie(file , subtitle) }
        }
    }

    private fun openMovie(url: String , subtitle: String?) {
        findNavController().navigate(
            SeriesBottomSheetDirections.actionSeriesBottomSheetToPlayerFragment(url , subtitle)
        )
    }

}