package kr.loner.memorygame.ui.activity

import kr.loner.memorygame.viewmodel.NoticeBoardViewModel
import kr.loner.memorygame.R
import kr.loner.memorygame.ui.base.BaseActivity
import kr.loner.memorygame.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main, NoticeBoardViewModel::class.java) {


    override fun ActivityMainBinding.onCreate() {}



}