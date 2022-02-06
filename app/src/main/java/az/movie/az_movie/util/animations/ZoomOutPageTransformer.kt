package az.movie.az_movie.util.animations

import android.view.View
import androidx.viewpager2.widget.ViewPager2
import az.movie.az_movie.extensions.transformPage
import kotlin.math.abs
import kotlin.math.max

class ZoomOutPageTransformer : ViewPager2.PageTransformer {

    override fun transformPage(view: View , position: Float) =
        view.transformPage(position)
}