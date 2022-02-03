package az.movie.az_movie.ui.fragment.main


import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.viewpager2.widget.ViewPager2
import az.movie.az_movie.databinding.FragmentMainBinding
import az.movie.az_movie.extensions.DRAWABLES
import az.movie.az_movie.extensions.STRINGS
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import az.movie.az_movie.extensions.invisible
import az.movie.az_movie.extensions.visible
import az.movie.az_movie.model.trailerDataModel.Trailer
import az.movie.az_movie.ui.base.BaseFragment
import az.movie.az_movie.ui.fragment.intro.IntroSlideFragment
import az.movie.az_movie.ui.fragment.intro.ZoomOutPageTransformer
import az.movie.az_movie.ui.fragment.trailer.TrailerAdapter
import az.movie.az_movie.util.response_handler.Resource
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MainFragment : BaseFragment<FragmentMainBinding>(FragmentMainBinding::inflate) {

    private lateinit var filmTabAdapter: FilmTabAdapter
    private val trailerViewModel: TrailerViewModel by viewModels()
//    private lateinit var trailerAdapter: TrailerAdapter

    override fun setInfo() {
        initTrailerSlider()
        initTabVP()
    }

    private fun initTabVP() {
        initFragmentVP()
        initTab()
    }

    private fun initFragmentVP() {
        filmTabAdapter = FilmTabAdapter(this)
        binding.vpTab.apply {
            adapter = filmTabAdapter
            setPageTransformer(ZoomOutPageTransformer())
        }
    }

    private fun initTab() {
        TabLayoutMediator(binding.tabLayout , binding.vpTab) { tab , position ->
            when (position) {
                0 -> tab.text = getString(STRINGS.movies)
                1 -> tab.text = getString(STRINGS.series)
            }
        }.attach()
    }

    private fun initTrailerSlider() {
//        initTrailerObservers()
        initTrailerVP()

    }

    private fun initTrailerObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            trailerViewModel.trailers.collectLatest { trailers ->
                with(binding) {
                    when (trailers) {
                        is Resource.Error -> {
                            tvError.visible()
                            tvError.text = getString(STRINGS.error)
                            pbTrailer.invisible()
                        }
                        is Resource.Loading -> {
                            tvError.invisible()
                            pbTrailer.visible()
                        }
                        is Resource.Success -> {
                            tvError.invisible()
                            trailers.data?.data?.let { value ->
                                trailerAdapter.setData(value)
                                tvError.invisible()
                            } ?: run {
                                tvError.visible()
                                tvError.text = getString(STRINGS.nothing_found)
                            }
                        }
                    }
                }
            }
        }
    }

    private fun initTrailerVP() {
        binding.vpTrailer.apply {
            adapter = trailerAdapter
            registerOnPageChangeCallback(object :
                ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    setCurrentIndicator(position)
                }
            })

            setPageTransformer(ZoomOutPageTransformer())
        }
        setupIndicators()
        setCurrentIndicator(0)
    }

    private fun setupIndicators() {
        val indicators = arrayOfNulls<ImageView>(IntroSlideFragment.introSliderAdapter.itemCount)
        val layoutParams: LinearLayout.LayoutParams =
            LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT ,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        layoutParams.setMargins(8 , 0 , 8 , 0)

        for (i in indicators.indices) {
            indicators[i] = ImageView(requireContext())
            indicators[i].apply {
                this?.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext() ,
                        DRAWABLES.indicator_inactive
                    )

                )
                this?.layoutParams = layoutParams
            }

            binding.lIndicatorContainer.addView(indicators[i])
        }
    }

    private fun setCurrentIndicator(index: Int) {
        val childCount = binding.lIndicatorContainer.childCount
        for (i in 0 until childCount) {
            val imageView = binding.lIndicatorContainer[i] as ImageView
            if (i == index) {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext() ,
                        DRAWABLES.indicator_active
                    )
                )
            } else {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext() ,
                        DRAWABLES.indicator_inactive
                    )
                )
            }
        }
    }

    companion object {
        val trailerAdapter = TrailerAdapter(
            mutableListOf(
                Trailer(
                    id = "1",
                    secondaryName = "oooo" ,
                ) ,
                Trailer(
                    id = "2",
                    secondaryName = "For Your Fun" ,
                ) ,
                Trailer(
                    id = "3",
                    secondaryName ="For Your Fun" ,
                )
            )
        )
    }
}