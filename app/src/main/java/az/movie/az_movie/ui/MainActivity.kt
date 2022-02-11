package az.movie.az_movie.ui

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
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
import java.util.*


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
                supportFragmentManager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
                navigationWithMotion(IDS.navigationIntro)
                prefsManager.setAnotherTimeLaunch()
            }
        }
    }

    private fun initSplashScreen() {
        val splashScreen = installSplashScreen()
        splashScreen.setOnExitAnimationListener { splashScreenView ->
            splashScreenView.iconView.getSplashIconRotationAnimation()
            splashScreenView.getSplashViewRotationAnimation()
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
        requestedOrientation = when (destination.id) {
            IDS.navigationSearchMovie, IDS.navigationMovie -> {
                goBack()
                ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
            }
            IDS.navigationPlayer ->{
                binding.btnBack.invisible()
                ActivityInfo.SCREEN_ORIENTATION_SENSOR
            }
            else -> {
                ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
            }
        }
    }

    private fun goBack() {
        binding.btnBack.apply {
            visible()
            setOnClickListener {
                getFabIconAnimation()
                supportFragmentManager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
                navigationWithMotion(IDS.navigationMain)
                binding.btnBack.invisible()
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
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