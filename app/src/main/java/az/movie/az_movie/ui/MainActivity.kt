package az.movie.az_movie.ui

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.os.Bundle
import android.util.Log.d
import android.view.View
import android.view.animation.AnticipateInterpolator
import androidx.activity.viewModels
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.*
import az.movie.az_movie.databinding.ActivityMainBinding
import az.movie.az_movie.extensions.*
import az.movie.az_movie.ui.base.BaseActivity
import az.movie.az_movie.ui.fragment.network.NetworkFragment
import az.movie.az_movie.util.network.NetworkStatus
import az.movie.az_movie.ui.fragment.network.NetworkViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MainActivity : BaseActivity() , NavController.OnDestinationChangedListener {

    private val networkViewModel: NetworkViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    private val navController by lazy { findNavController(IDS.navHostFragment) }

    private val currentNavigationFragment: Fragment?
        get() = supportFragmentManager.findFragmentById(IDS.navHostFragment)
            ?.childFragmentManager
            ?.fragments
            ?.first()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initSplashScreen()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initMain()
    }

    @ExperimentalCoroutinesApi
    private fun initMain() {
        initIntro()
        initBTN()
        initNetwork()
    }


    @ExperimentalCoroutinesApi
    private fun initNetwork() {
        lifecycleScope.launch {
            networkViewModel.networkStatusActive.collectLatest {
                if (it is NetworkStatus.Unavailable) {
                    navigationWithMotion(IDS.navigationNetwork)
                } else {
                    if (currentNavigationFragment is NetworkFragment) {
                        binding.btnBack.visible()
                        supportFragmentManager.popBackStackImmediate()
                    }
                }
            }
        }

    }

    private fun initIntro() {
        lifecycleScope.launch {
            val isFirstTimeLaunch = prefsManager.isFirstTimeLaunch()
            if (isFirstTimeLaunch) {
                navigationWithMotion(IDS.navigationIntro)
                prefsManager.setAnotherTimeLaunch()
            }
        }
    }

    private fun initSplashScreen() {
        val splashScreen = installSplashScreen()
        splashScreen.setOnExitAnimationListener { splashScreenView ->

            val iconAnimator =
                ObjectAnimator.ofFloat(splashScreenView.iconView , View.ROTATION , -360f , 0f)
            iconAnimator.apply {
                duration = 600L
                start()
            }
            val splashScreenAnimator = ObjectAnimator.ofFloat(
                splashScreenView.view ,
                View.TRANSLATION_Y ,
                0f ,
                splashScreenView.view.height.toFloat()
            )
            splashScreenAnimator.apply {
                interpolator = AnticipateInterpolator()
                duration = 600L
                doOnEnd { splashScreenView.remove() }
                start()
            }
        }
    }


    private fun initBTN() {
        binding.run {
            navController.addOnDestinationChangedListener(
                this@MainActivity
            )
        }
    }

    override fun onDestinationChanged(
        controller: NavController ,
        destination: NavDestination ,
        arguments: Bundle?
    ) {
        when (destination.id) {
            IDS.navigationMovie , IDS.navigationSearchMovie -> goBack()
            else -> {
                binding.btnBack.invisible()
            }
        }
    }

    private fun goBack() {
        binding.btnBack.apply {
            visible()
            setOnClickListener {
                navController.popBackStack(IDS.navigationMain, false)
                binding.btnBack.invisible()
            }
        }
    }

    override fun onBackPressed() {
        if (currentNavigationFragment is NetworkFragment) {
            MaterialAlertDialogBuilder(this)
                .setMessage(getString(STRINGS.check_network))
                .setPositiveButton(getString(STRINGS.ok)) { _ , _ ->
                    binding.root.showSnackBar(getString(STRINGS.ok))
                }
                .show()
        } else {
            super.onBackPressed()
        }
    }

    private fun navigationWithMotion(navigation: Int) {
        navController.navigate(navigation)
    }

    override fun finish() {
        super.finish()
        ActivityNavigator.applyPopAnimationsToPendingTransition(this)
    }
}