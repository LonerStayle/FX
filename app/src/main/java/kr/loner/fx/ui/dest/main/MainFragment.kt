package kr.loner.fx.ui.dest.main

import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import kr.loner.fx.R
import kr.loner.fx.databinding.FragmentMainBinding
import kr.loner.fx.ui.base.BaseFragment
import kr.loner.fx.viewmodel.MainViewModel

class MainFragment :
    BaseFragment<FragmentMainBinding>(R.layout.fragment_main, MainViewModel::class.java) {
    override fun FragmentMainBinding.setDataBind() {
        setBottomNavi()
    }
    private fun setBottomNavi() {
        (parentFragmentManager.findFragmentById(R.id.nav_host_fragment_container_main)
                as NavHostFragment?).also {
            NavigationUI.setupWithNavController(binding.bnvMain, it!!.navController)
            binding.bnvMain.itemIconTintList = null
        }
    }
}