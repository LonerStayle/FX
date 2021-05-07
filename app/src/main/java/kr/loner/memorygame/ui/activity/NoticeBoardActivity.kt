package kr.loner.memorygame.ui.activity

import kr.loner.memorygame.R
import kr.loner.memorygame.databinding.ActivityNoticeboardBinding
import kr.loner.memorygame.ui.base.BaseActivity
import kr.loner.memorygame.viewmodel.NoticeBoardViewModel

class NoticeBoardActivity :
    BaseActivity<ActivityNoticeboardBinding>(R.layout.activity_noticeboard,
    NoticeBoardViewModel::class.java) {
    override fun ActivityNoticeboardBinding.onCreate() {}

}