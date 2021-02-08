package kr.loner.fx.ui.activity

import kr.loner.fx.R
import kr.loner.fx.databinding.ActivityNoticeboardBinding
import kr.loner.fx.ui.base.BaseActivity
import kr.loner.fx.viewmodel.NoticeBoardViewModel

class NoticeBoardActivity :
    BaseActivity<ActivityNoticeboardBinding>(R.layout.activity_noticeboard,
    NoticeBoardViewModel::class.java) {
    override fun ActivityNoticeboardBinding.onCreate() {}

}