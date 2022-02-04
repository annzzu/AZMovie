package az.movie.az_movie.ui.fragment.movie

import android.os.Bundle
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import az.movie.az_movie.databinding.FragmentMovieBottomSheetBinding
import az.movie.az_movie.model.playerDataModel.PlayerViewModel


class MovieBottomSheet : BottomSheetDialogFragment() {

    private val args: MovieBottomSheetArgs by navArgs()
    private val playerViewModel: PlayerViewModel by viewModels()

    private var _binding: FragmentMovieBottomSheetBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater , container: ViewGroup? ,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentMovieBottomSheetBinding.inflate(inflater , container , false)
        return binding.root

    }

    override fun onViewCreated(view: View , savedInstanceState: Bundle?) {
        init()
    }

    private fun init() {
        listeners()
    }

    private fun listeners() = with (binding) {
        tvTitle.text = args.title.plus(" ${args.movieId}")
//        binding.btnTrailer.apply{
//            if (!args.trailer.isNullOrBlank()){
//                visible()
//                setOnClickListener {
//                    openMovie(args.trailer!!)
//                }
//            }
//
//        }
    }

    private fun openMovie(url: String) {
        findNavController().navigate(
            MovieBottomSheetDirections.actionMovieBottomSheetToPlayerFragment(
                url
            )
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}