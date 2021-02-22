package kr.grandoption.fx.ui.activity

import kr.grandoption.fx.R
import kr.grandoption.fx.databinding.ActivityGameBinding
import kr.grandoption.fx.ui.base.BaseActivity
import kr.grandoption.fx.viewmodel.GameViewModel

class GameActivity :
    BaseActivity<ActivityGameBinding>(R.layout.activity_game, GameViewModel::class.java) {
    override fun ActivityGameBinding.onCreate() {
        supportActionBar!!.title = "기억력 게임"
    }
}