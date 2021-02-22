package kr.grandoption.fx.ui.activity

import kr.grandoption.fx.viewmodel.NoticeBoardViewModel
import kr.grandoption.fx.R
import kr.grandoption.fx.ui.base.BaseActivity
import kr.grandoption.fx.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main, NoticeBoardViewModel::class.java) {


    override fun ActivityMainBinding.onCreate() {}



}