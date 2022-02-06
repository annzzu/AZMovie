package az.movie.az_movie.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import az.movie.az_movie.extensions.INTEGERS
import com.google.android.material.transition.MaterialElevationScale
import dagger.hilt.android.AndroidEntryPoint

typealias  Inflate<T> = (LayoutInflater , ViewGroup? , Boolean) -> T

abstract class BaseFragment<VB : ViewBinding>(private val inflate: Inflate<VB>) : Fragment() {

    private var _binding: VB? = null
    val binding
        get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initOnCreate()
    }

    open fun initOnCreate() {}

    override fun onCreateView(
        inflater: LayoutInflater ,
        container: ViewGroup? ,
        savedInstanceState: Bundle?
    ): View? {
        _binding = inflate.invoke(inflater , container , false)
        return _binding!!.root
    }

    override fun onViewCreated(view: View , savedInstanceState: Bundle?) {
        super.onViewCreated(view , savedInstanceState)
        motions()
        init()
    }

    open fun motions() {
        exitTransition = MaterialElevationScale(false).apply {
            duration = resources.getInteger(INTEGERS.reply_motion_duration_large).toLong()
        }
        reenterTransition = MaterialElevationScale(true).apply {
            duration = resources.getInteger(INTEGERS.reply_motion_duration_large).toLong()
        }
    }

    open fun init() {
        setInfo()
        listener()
        observer()
    }

    open fun setInfo() {}

    open fun listener() {}

    open fun observer() {}

    override fun onDestroyView() {
        super.onDestroyView()
        stop()
    }

    open fun stop() {
        _binding = null
    }
}