package az.movie.az_movie.ui.fragment.main

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import az.movie.az_movie.ui.fragment.movies.MoviesFragment
import az.movie.az_movie.util.typealiases.ClickIntCallBack


class FilmTabAdapter(fragment: Fragment) :
    FragmentStateAdapter(fragment) {

    var clickIntCallBack: ClickIntCallBack? = null

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        val fragment = when (position) {
            0 -> MoviesFragment().apply {
                series = true
            }
            1 -> MoviesFragment()
            else ->  MoviesFragment().apply {
                series = true
            }
        }
        fragment.clickIntCallBack = {
            clickIntCallBack?.invoke(it)
        }
        return fragment
    }
}
