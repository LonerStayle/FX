package kr.grandoption.fx.ui.dest.main

import androidx.navigation.fragment.findNavController
import kr.grandoption.fx.R
import kr.grandoption.fx.databinding.FragmentNoticeboardUploadBinding
import kr.grandoption.fx.db.entity.NoticeBoard
import kr.grandoption.fx.ui.base.BaseFragment
import kr.grandoption.fx.ui.util.Contents
import kr.grandoption.fx.viewmodel.MainViewModel

class NoticeBoardUpLoadFragment :
    BaseFragment<FragmentNoticeboardUploadBinding>(
        R.layout.fragment_noticeboard_upload,
        MainViewModel::class.java
    ) {

    override fun FragmentNoticeboardUploadBinding.setDataBind() {
        vm!!.userData.observe(viewLifecycleOwner,{
            setUploadClickEvent(it.name!!)
        })
    }

    private fun FragmentNoticeboardUploadBinding.setUploadClickEvent(name:String) {
        btnBoardUpload.setOnClickListener {
            val title = etUploadTitle.text.toString()
            val content = etUploadContent.text.toString()
            val randomId = Contents.noticeBoardRandomId()
            vm!!.setNoticeBoard(
                randomId,
                NoticeBoard(
                    randomId, name,
                    title, content
                )
            )
            findNavController().popBackStack()
            toastShort("게시물이 작성되었습니다.")
        }
    }
}