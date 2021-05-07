package kr.loner.memorygame.ui.dest.main

import androidx.navigation.fragment.findNavController
import kr.loner.memorygame.R
import kr.loner.memorygame.databinding.FragmentNoticeboardUploadBinding
import kr.loner.memorygame.db.entity.NoticeBoard
import kr.loner.memorygame.ui.base.BaseFragment
import kr.loner.memorygame.ui.util.Contents
import kr.loner.memorygame.viewmodel.MainViewModel

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