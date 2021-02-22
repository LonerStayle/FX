package kr.grandoption.fx.ui.base

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.view.WindowManager
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

class BaseDialog<VDB : ViewDataBinding>(
    context: Context,
    @LayoutRes layoutId: Int,
) : Dialog(context) {

     val binding: VDB = DataBindingUtil.inflate<VDB>(layoutInflater, layoutId, null, false)
         .apply {
             setContentView(root)
         }

    @SuppressLint("ResourceAsColor")
    fun setWindowManager(gravity: Int, dimAmount: Float, transMode: Boolean) {
        WindowManager.LayoutParams().let {

            it.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND
            it.dimAmount = dimAmount
            it.gravity = gravity
            window?.attributes = it

            if (transMode) {
                window?.setBackgroundDrawableResource(android.R.color.transparent)
                binding.root.setBackgroundColor(android.R.color.transparent)
            }
        }
    }

    fun setWindowLayoutControl(width: Int, height: Int) {
        WindowManager.LayoutParams().let {
            val dp = context.resources.displayMetrics.density
            it.width = (width * dp).toInt()
            it.height = (height * dp).toInt()
            window?.setLayout(it.width,it.height)
        }
    }

}