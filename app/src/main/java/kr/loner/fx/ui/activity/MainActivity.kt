package kr.loner.fx.ui.activity

import kr.loner.fx.viewmodel.NoticeBoardViewModel
import kr.loner.fx.R
import kr.loner.fx.ui.base.BaseActivity
import kr.loner.fx.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main, NoticeBoardViewModel::class.java) {


    override fun ActivityMainBinding.onCreate() {}



}