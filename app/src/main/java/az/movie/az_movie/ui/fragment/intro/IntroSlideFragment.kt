package az.movie.az_movie.ui.fragment.intro

import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import az.movie.az_movie.databinding.FragmentIntroSlideBinding
import az.movie.az_movie.extensions.DRAWABLES
import az.movie.az_movie.model.introModel.IntroSlide
import az.movie.az_movie.ui.base.BaseFragment
import az.movie.az_movie.util.animations.ZoomOutPageTransformer
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class IntroSlideFragment : BaseFragment<FragmentIntroSlideBinding>(
    FragmentIntroSlideBinding::inflate
) {

    override fun init() {
        initSliderAdapter()
    }

    private fun initSliderAdapter() {
        binding.introSliderViewPager.apply {
            adapter = introSliderAdapter
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
        initBTNs()
    }

    private fun initBTNs() = with(binding) {
        buttonNext.setOnClickListener {
            if (introSliderViewPager.currentItem + 1 < introSliderAdapter.itemCount) {
                introSliderViewPager.currentItem += 1
            } else {
                openHome()
            }
        }
        textSkipIntro.setOnClickListener {
            openHome()
        }

    }

    private fun openHome() {
        findNavController().apply {
            navigate(
                IntroSlideFragmentDirections.actionNavigationIntroToMainFragment()
            )
        }
    }

    private fun setupIndicators() {
        val indicators = arrayOfNulls<ImageView>(introSliderAdapter.itemCount)
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

            binding.indicatorContainer.addView(indicators[i])
        }
    }

    private fun setCurrentIndicator(index: Int) {
        val childCount = binding.indicatorContainer.childCount
        for (i in 0 until childCount) {
            val imageView = binding.indicatorContainer[i] as ImageView
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
        val introSliderAdapter = IntroSliderAdapter(
            listOf(
                IntroSlide(
                    "AZ\nMovie" ,
                    "For Your Fun" ,
                    DRAWABLES.ic_camera
                ) ,
                IntroSlide(
                    "AZ\nMovie" ,
                    "For Your Fun" ,
                    DRAWABLES.ic_film
                ) ,
                IntroSlide(
                    "AZ\nMovie" ,
                    "For Your Fun" ,
                    DRAWABLES.ic_popcorn
                )
            )
        )
    }
}