package az.movie.az_movie.ui.base

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import androidx.viewbinding.ViewBinding
import az.movie.az_movie.extensions.INTEGERS
import com.google.android.material.transition.MaterialFadeThrough

typealias  DialogInflate<T> = (LayoutInflater , ViewGroup? , Boolean) -> T

typealias ClickCallBack = (string: String) -> Unit

abstract class BaseFragmentDialog<VB : ViewBinding>(private val inflate: DialogInflate<VB>) :
    DialogFragment() {

    var clickCallBack: ClickCallBack? = null

    private var _binding: VB? = null
    val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater ,
        container: ViewGroup? ,
        savedInstanceState: Bundle?
    ): View? {
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        _binding = inflate.invoke(inflater , container  , false)
        return binding.root
    }

    override fun onViewCreated(view: View , savedInstanceState: Bundle?) {
        super.onViewCreated(view , savedInstanceState)
        init()
    }

    open fun init() {
        initRV()
        observer()
    }

    open fun initRV() {}

    open fun observer() {}

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT ,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        enterTransition = MaterialFadeThrough().apply {
            duration = resources.getInteger(INTEGERS.reply_motion_duration_large).toLong()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}