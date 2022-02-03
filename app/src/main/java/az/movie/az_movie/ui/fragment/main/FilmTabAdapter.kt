package az.movie.az_movie.ui.fragment.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import az.movie.az_movie.ui.fragment.intro.IntroSlideFragment
import az.movie.az_movie.ui.fragment.movies.MoviesFragment
import az.movie.az_movie.ui.fragment.network.NetworkFragment


class FilmTabAdapter(fragment: Fragment) :
    FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment = when (position) {
        0 -> MoviesFragment().apply {
            series = true
        }
        1 -> MoviesFragment()
        else ->  MoviesFragment().apply {
            series = true
        }
    }
}
