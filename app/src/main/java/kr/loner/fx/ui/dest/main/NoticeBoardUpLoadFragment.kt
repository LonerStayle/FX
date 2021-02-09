package kr.loner.fx.ui.dest.main

import kr.loner.fx.R
import kr.loner.fx.databinding.FragmentNoticeboardUploadBinding
import kr.loner.fx.db.entity.NoticeBoard
import kr.loner.fx.ui.base.BaseFragment
import kr.loner.fx.ui.util.Contents
import kr.loner.fx.viewmodel.MainViewModel

class NoticeBoardUpLoadFragment :
    BaseFragment<FragmentNoticeboardUploadBinding>(
        R.layout.fragment_noticeboard_upload,
        MainViewModel::class.java
    ) {

    override fun FragmentNoticeboardUploadBinding.setDataBind() {

        btnBoardUpload.setOnClickListener {
            val title = etUploadTitle.text.toString()
            val content = etUploadContent.text.toString()
            val noticeBoard = vm.userData.value

            vm.setNoticeBoard(
                NoticeBoard(
                    Contents.noticeBoardRandomId, noticeBoard!!.name,
                    title, content
                )
            )
        }
    }
}