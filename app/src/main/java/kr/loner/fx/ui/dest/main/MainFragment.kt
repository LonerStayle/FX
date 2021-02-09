package kr.loner.fx.ui.dest.main

import android.util.Log
import android.view.Gravity
import android.view.WindowManager
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import kr.loner.fx.R
import kr.loner.fx.databinding.DialogUsercreateBinding
import kr.loner.fx.databinding.FragmentMainBinding
import kr.loner.fx.db.entity.UserData
import kr.loner.fx.ui.base.BaseDialog
import kr.loner.fx.ui.base.BaseFragment
import kr.loner.fx.viewmodel.MainViewModel

class MainFragment :
    BaseFragment<FragmentMainBinding>(R.layout.fragment_main, MainViewModel::class.java) {
    override fun FragmentMainBinding.setDataBind() {
        vm!!.userData.observe(viewLifecycleOwner, {
            it ?: setUserCreateDialogShow()
        })
        setBottomNavi()

    }

    private fun setBottomNavi() {
        (childFragmentManager.findFragmentById(R.id.nav_host_fragment_container_main)
                as NavHostFragment?).also {
            NavigationUI.setupWithNavController(binding.bnvMain, it!!.navController)
        }
    }

    private fun setUserCreateDialogShow() {
            BaseDialog<DialogUsercreateBinding>(requireContext(), R.layout.dialog_usercreate).apply {
                setWindowManager(Gravity.NO_GRAVITY, 0.8f, false)
                setWindowLayoutControl(300,230)
                setCancelable(false)
                setCanceledOnTouchOutside(false)
                show()
                binding.also { bind ->
                    bind.tvAppExit.setOnClickListener { requireActivity().finish() }
                    bind.tvUserCreate.setOnClickListener {
                       this@MainFragment.binding.vm!!.userDataUpdate(
                            UserData(name = bind.etUserName.text.toString())
                        )
                        this.dismiss()
                    }
                }
            }



    }
}