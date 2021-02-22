package kr.grandoption.fx.ui.activity

import kr.grandoption.fx.R
import kr.grandoption.fx.databinding.ActivityNoticeboardBinding
import kr.grandoption.fx.ui.base.BaseActivity
import kr.grandoption.fx.viewmodel.NoticeBoardViewModel

class NoticeBoardActivity :
    BaseActivity<ActivityNoticeboardBinding>(R.layout.activity_noticeboard,
    NoticeBoardViewModel::class.java) {
    override fun ActivityNoticeboardBinding.onCreate() {}

}