package az.movie.az_movie.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.viewbinding.ViewBinding
import az.movie.az_movie.databinding.FragmentMovieBottomSheetBinding
import az.movie.az_movie.model.playerDataModel.PlayerViewModel
import az.movie.az_movie.ui.fragment.movie.MovieBottomSheetArgs
import az.movie.az_movie.ui.fragment.movie.MovieBottomSheetDirections
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

abstract class BaseBottomSheet<VB : ViewBinding>(private val inflate: Inflate<VB>) :
    BottomSheetDialogFragment() {

    private var _binding: VB? = null
    val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater ,
        container: ViewGroup? ,
        savedInstanceState: Bundle?
    ): View? {
        _binding = inflate.invoke(inflater , container , false)
        return binding.root
    }

    override fun onViewCreated(view: View , savedInstanceState: Bundle?) {
        init()
    }

    open fun init() {}

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}