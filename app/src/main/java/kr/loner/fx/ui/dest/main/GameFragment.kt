package kr.loner.fx.ui.dest.main

import kr.loner.fx.R
import kr.loner.fx.ui.base.BaseFragment
import kr.loner.fx.databinding.FragmentGameBinding
import kr.loner.fx.viewmodel.MainViewModel

class GameFragment : BaseFragment<FragmentGameBinding>(R.layout.fragment_game, MainViewModel::class.java) {
    override fun FragmentGameBinding.setDataBind() {

    }
}