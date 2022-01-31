package az.movie.az_movie.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import az.movie.az_movie.data.local.PrefsManager
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

abstract class BaseActivity : AppCompatActivity() {

    @Inject
    lateinit var prefsManager: PrefsManager


}