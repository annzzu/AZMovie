package az.movie.az_movie.ui.fragment.movie.bottom_sheet

import android.util.Log
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import az.movie.az_movie.databinding.FragmentMovieBottomSheetBinding
import az.movie.az_movie.extensions.STRINGS
import az.movie.az_movie.extensions.invisible
import az.movie.az_movie.extensions.setImageUrl
import az.movie.az_movie.extensions.visible
import az.movie.az_movie.domain.model.playerDataModel.EpisodePlayer
import az.movie.az_movie.ui.fragment.movie.PlayerViewModel
import az.movie.az_movie.ui.base.BaseBottomSheet
import az.movie.az_movie.ui.fragment.movie.adapter.LangPlayerAdapter
import az.movie.az_movie.ui.fragment.player.TAG
import az.movie.az_movie.domain.response_handler.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

const val MOVIE_SEASON = 0

@AndroidEntryPoint
class MovieBottomSheet :
    BaseBottomSheet<FragmentMovieBottomSheetBinding>(FragmentMovieBottomSheetBinding::inflate) {

    private val args: MovieBottomSheetArgs by navArgs()
    private val playerViewModel: PlayerViewModel by viewModels()
    private lateinit var langPlayerAdapter: LangPlayerAdapter

    override fun init() {
        observe()
        listeners()
        binding.rvLang.apply {
            langPlayerAdapter = LangPlayerAdapter()
            adapter = langPlayerAdapter
            layoutManager =
                LinearLayoutManager(view?.context , LinearLayoutManager.HORIZONTAL , false)
        }
    }

    private fun observe() {
        viewLifecycleOwner.lifecycleScope.launch {
            playerViewModel.getMovie(args.movieId , MOVIE_SEASON)
            playerViewModel.movie.collectLatest { movie ->
                with(binding) {
                    when (movie) {
                        is Resource.Error -> {
                            tvMovie.text = getString(STRINGS.error)
                            pbMovie.invisible()
                        }
                        is Resource.Loading -> {
                            pbMovie.visible()
                        }
                        is Resource.Success -> {
                            pbMovie.invisible()
                            movie.data?.firstEpisode?.let { movie ->
                                tvMovie.text = getString(STRINGS.movies)
                                setData(movie)
                            } ?: run {
                                tvMovie.visible()
                                tvMovie.text = getString(STRINGS.nothing_found)
                            }
                        }
                    }
                }
            }
        }
    }

    private fun setData(movie: EpisodePlayer) = with(binding) {
        cvItemPlayer.visible()
        tvEpisodeTitle.text = movie.title
        ivPoster.setImageUrl(movie.cover)
        langPlayerAdapter.submitList(movie.files)
        langPlayerAdapter.clickStringsCallBack = { file , subtitle ->
            Log.d(
                TAG ,
                "mgeli mtaze micocavs ${movie.subtitleUrl(subtitle)} - $subtitle - $file"
            )
            openMovie(file , movie.subtitleUrl(subtitle))
        }
        rvLang.startLayoutAnimation()
    }

    private fun listeners() = with(binding) {
        tvTitle.text = args.title
    }

    private fun openMovie(url: String , subtitle: String?) {
        findNavController().navigate(
            MovieBottomSheetDirections.actionMovieBottomSheetToPlayerFragment(
                url , subtitle
            )
        )
    }

}