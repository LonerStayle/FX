package kr.loner.memorygame.ui.activity

import kr.loner.memorygame.R
import kr.loner.memorygame.databinding.ActivityGameBinding
import kr.loner.memorygame.ui.base.BaseActivity
import kr.loner.memorygame.viewmodel.GameViewModel

class GameActivity :
    BaseActivity<ActivityGameBinding>(R.layout.activity_game, GameViewModel::class.java) {
    override fun ActivityGameBinding.onCreate() {
        supportActionBar!!.title = "기억력 게임"
    }
}