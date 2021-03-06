package kr.loner.memorygame.ui.base

import android.os.Bundle
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kr.loner.memorygame.BR
import kr.loner.memorygame.db.database.FXDataBase
import kr.loner.memorygame.viewmodel.factory.ViewModelFactory

abstract class BaseActivity<VDB : ViewDataBinding>(
    @LayoutRes val layoutId: Int,
    protected val viewModelCls: Class<out ViewModel>
) : AppCompatActivity() {
    protected lateinit var binding: VDB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutId)
        getBinding {
            setVariable(BR.vm, ViewModelProvider(this@BaseActivity,
                ViewModelFactory(FXDataBase.getInstance(this@BaseActivity).userDao,
                FXDataBase.getInstance(this@BaseActivity).gameDao)
                )[viewModelCls])
        }
        binding.onCreate()

    }

    protected fun getBinding (action:VDB.()->Unit){
        binding.run(action)
    }

    abstract fun VDB.onCreate()
    protected fun VDB.toastShort(message:String){
        Toast.makeText(this@BaseActivity,message, Toast.LENGTH_SHORT).show()
    }
    protected fun VDB.toastShort(message:Int){
        Toast.makeText(this@BaseActivity,message, Toast.LENGTH_SHORT).show()
    }
    protected fun VDB.toastLong(message:Int){
        Toast.makeText(this@BaseActivity,message, Toast.LENGTH_SHORT).show()
    }
    protected fun VDB.toastLong(message:String){
        Toast.makeText(this@BaseActivity,message, Toast.LENGTH_SHORT).show()
    }
}