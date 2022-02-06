package az.movie.az_movie.extensions

import android.os.Build
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*

// String

fun String.getNextLine() =
    this.replace(" " , "\n")


