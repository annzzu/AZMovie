package az.movie.az_movie.ui.fragment.player

import android.annotation.SuppressLint
import android.util.Log
import android.view.View
import androidx.navigation.fragment.navArgs
import az.movie.az_movie.databinding.FragmentPlayerBinding
import az.movie.az_movie.ui.base.BaseFragment
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.util.Util

//@AndroidEntryPoint
class PlayerFragment : BaseFragment<FragmentPlayerBinding>(FragmentPlayerBinding::inflate) {

    private val args: PlayerFragmentArgs by navArgs()

    private val playbackStateListener: Player.EventListener = playbackStateListener()
    private var player: ExoPlayer? = null

    private var playWhenReady = true
    private var currentWindow = 0
    private var playbackPosition = 0L


    private fun initPlayer() {
        player = ExoPlayer.Builder(requireContext()).build().also { exoPlayer->
            binding.exoPlayer.player = exoPlayer
            val mediaItem = MediaItem.fromUri(args.url)
            exoPlayer.setMediaItem(mediaItem)
            exoPlayer.playWhenReady = playWhenReady
            exoPlayer.seekTo(currentWindow , playbackPosition)
            exoPlayer.addListener(playbackStateListener)
            exoPlayer.prepare()
        }
//        player = SimpleExoPlayer.Builder(requireContext())
//            .build()
//            .also { exoPlayer ->
//                binding.exoPlayer.player = exoPlayer
//                val mediaItem = MediaItem.Builder()
//                    .setUri(args.url)
//                    .setMimeType(MimeTypes.APPLICATION_MPD)
//                    .build()
//                exoPlayer.setMediaItem(mediaItem)
//                exoPlayer.playWhenReady = playWhenReady
//                exoPlayer.seekTo(currentWindow , playbackPosition)
//                exoPlayer.addListener(playbackStateListener)
//                exoPlayer.prepare()
//            }
    }

    override fun init() {
        if (Util.SDK_INT > 23) {
            initPlayer()
        }
    }

    override fun onStart() {
        super.onStart()
        hideSystemUi()
        if (Util.SDK_INT <= 23 || player == null) {
            initPlayer()
        }
    }

    override fun onPause() {
        super.onPause()
        if (Util.SDK_INT <= 23) {
            releasePlayer()
        }
    }

    override fun onStop() {
        super.onStop()
        if (Util.SDK_INT > 23) {
            releasePlayer()
        }
    }

    private fun releasePlayer() {
        player?.run {
            playbackPosition = this.currentPosition
            currentWindow = this.currentWindowIndex
            playWhenReady = this.playWhenReady
            removeListener(playbackStateListener)
            release()
        }
        player = null
    }

    @SuppressLint("InlinedApi")
    private fun hideSystemUi() {
        binding.exoPlayer.systemUiVisibility = (View.SYSTEM_UI_FLAG_LOW_PROFILE
                or View.SYSTEM_UI_FLAG_FULLSCREEN
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION)
    }

}

private fun playbackStateListener() = object : Player.EventListener {
    override fun onPlaybackStateChanged(playbackState: Int) {
        val stateString: String = when (playbackState) {
            ExoPlayer.STATE_IDLE -> "ExoPlayer.STATE_IDLE      -"
            ExoPlayer.STATE_BUFFERING -> "ExoPlayer.STATE_BUFFERING -"
            ExoPlayer.STATE_READY -> "ExoPlayer.STATE_READY     -"
            ExoPlayer.STATE_ENDED -> "ExoPlayer.STATE_ENDED     -"
            else -> "UNKNOWN_STATE             -"
        }
        Log.d(TAG , "changed state to $stateString")
    }
}

const val TAG = "Testing AZ"