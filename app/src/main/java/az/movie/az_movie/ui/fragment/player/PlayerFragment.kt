package az.movie.az_movie.ui.fragment.player

import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import az.movie.az_movie.databinding.FragmentPlayerBinding
import az.movie.az_movie.ui.base.BaseFragment

import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

//@AndroidEntryPoint
class PlayerFragment : BaseFragment<FragmentPlayerBinding>(FragmentPlayerBinding::inflate) {

    private val args: PlayerFragmentArgs by navArgs()

    private lateinit var player: SimpleExoPlayer
    private var playWhenReady = true
    private var currentWindow = 0
    private var playerBackPosition: Long = 0

    override fun init() {
        initPlayer()
    }

    private fun initPlayer() {
        player = SimpleExoPlayer.Builder(requireContext()).build().also {
            binding.exoPlayer.player = it
            val mediaItem = MediaItem.fromUri(args.url)
            it.setMediaItem(mediaItem)
        }
    }

    override fun stop() {
        super.stop()
        player.stop()
    }

}