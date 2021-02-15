package kr.loner.fx.ui.activity

import kr.loner.fx.R
import kr.loner.fx.databinding.ActivityGameBinding
import kr.loner.fx.ui.base.BaseActivity
import kr.loner.fx.viewmodel.GameViewModel
import kr.loner.fx.viewmodel.MainViewModel

class GameActivity :
    BaseActivity<ActivityGameBinding>(R.layout.activity_game, GameViewModel::class.java) {
    override fun ActivityGameBinding.onCreate() {
        supportActionBar!!.title = "기억력 게임"
    }
}