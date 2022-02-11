package az.movie.az_movie.ui.fragment.player

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.util.Log.d
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import az.movie.az_movie.databinding.FragmentPlayerBinding
import az.movie.az_movie.extensions.STRINGS
import az.movie.az_movie.ui.base.BaseFragment
import az.movie.az_movie.ui.fragment.movie.MovieViewModel
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.util.Util
import com.google.common.collect.ImmutableList
import com.google.android.exoplayer2.C
import com.google.android.exoplayer2.MediaItem.SubtitleConfiguration
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.util.MimeTypes


class PlayerFragment : BaseFragment<FragmentPlayerBinding>(FragmentPlayerBinding::inflate) {

    private val args: PlayerFragmentArgs by navArgs()
    private val playerViewModel: PlayerViewModel by viewModels()

    private val playbackStateListener: Player.Listener = playbackStateListener()
    private var player: ExoPlayer? = null

    private var playWhenReadyBoolean = true
    private var currentWindow = 0
    private var playbackPosition = 0L


    private fun initPlayer() {
        player = ExoPlayer.Builder(requireContext()).build().also { exoPlayer ->
            binding.exoPlayer.player = exoPlayer
            val mediaItem = if (!args.subtitle.isNullOrBlank()) {
                MediaItem.Builder().setUri(Uri.parse(args.url)).setSubtitleConfigurations(
                    ImmutableList.of(
                        SubtitleConfiguration.Builder(Uri.parse(args.subtitle))
                            .setMimeType(MimeTypes.TEXT_VTT)
                            .setLanguage(getString(STRINGS.subtitle))
                            .build()
                    )
                ).build()
            } else {
                MediaItem.Builder().setUri(Uri.parse(args.url)).build()
            }

            exoPlayer.apply {
                setMediaItem(mediaItem)
                prepare()
                playWhenReady = playWhenReadyBoolean
                seekTo(currentWindow , playerViewModel.playerCurrentPositionL)
                addListener(playbackStateListener)
            }
        }
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
        playerViewModel.playerCurrentPositionL = player?.currentPosition ?: 0L
        super.onStop()
        if (Util.SDK_INT > 23) {
            releasePlayer()
        }
    }

    private fun releasePlayer() {
        player?.run {
            playbackPosition = playerViewModel.playerCurrentPositionL
            currentWindow = this.currentMediaItemIndex
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

private fun playbackStateListener(): Player.Listener = object : Player.Listener {
    override fun onPlaybackStateChanged(playbackState: Int) {
        val stateString: String = when (playbackState) {
            ExoPlayer.STATE_IDLE -> "ExoPlayer.STATE_IDLE      -"
            ExoPlayer.STATE_BUFFERING -> "ExoPlayer.STATE_BUFFERING -"
            ExoPlayer.STATE_READY -> "ExoPlayer.STATE_READY     -"
            ExoPlayer.STATE_ENDED -> "ExoPlayer.STATE_ENDED     -"
            else -> "UNKNOWN_STATE             -"
        }
    }
}

const val TAG = "Testing AZ"