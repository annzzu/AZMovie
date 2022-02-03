package az.movie.az_movie.ui.fragment.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import az.movie.az_movie.R
import az.movie.az_movie.databinding.FragmentNetworkBinding
import az.movie.az_movie.databinding.FragmentSearchMovieBinding
import az.movie.az_movie.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchMovieFragment : BaseFragment<FragmentSearchMovieBinding>(FragmentSearchMovieBinding::inflate)