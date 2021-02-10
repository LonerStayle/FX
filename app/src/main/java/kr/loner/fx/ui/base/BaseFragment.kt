package kr.loner.fx.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kr.loner.fx.BR
import kr.loner.fx.db.database.FXDataBase
import kr.loner.fx.viewmodel.factory.ViewModelFactory

abstract class BaseFragment<VDB : ViewDataBinding>(
    @LayoutRes val layoutRes: Int,
    protected val viewModelCls: Class<out ViewModel>,
) : Fragment() {

    lateinit var binding: VDB
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = DataBindingUtil.inflate<VDB>(layoutInflater, layoutRes, container, false).run {
        lifecycleOwner = this@BaseFragment
        binding = this
        getBinding {
            setVariable(
                BR.vm, ViewModelProvider(
                    this@BaseFragment,
                    ViewModelFactory(
                        FXDataBase.getInstance(requireContext()).userDao,
                        FXDataBase.getInstance(requireContext()).gameDao
                    )
                )[viewModelCls]
            )
        }
        setDataBind()
        setClickListener()
        root
    }

    protected fun getBinding(action: VDB.() -> Unit) {
        binding.run(action)
    }

    abstract fun VDB.setDataBind()
    open fun VDB.setClickListener() = Unit
    protected fun toastShort(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    protected fun toastShort(message: Int) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    protected fun toastLong(message: Int) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    protected fun toastLong(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

}

