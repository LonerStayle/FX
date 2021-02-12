package kr.loner.fx.ui.dest.noticeboard

import android.util.Log
import android.view.Gravity
import androidx.fragment.app.viewModels
import com.google.firebase.Timestamp
import kr.loner.fx.R
import kr.loner.fx.databinding.DialogCommandEditBinding
import kr.loner.fx.databinding.FragmentNoticeboardDetailBinding
import kr.loner.fx.db.database.FXDataBase
import kr.loner.fx.db.entity.Reply
import kr.loner.fx.db.entity.UserData
import kr.loner.fx.ui.adapter.NoticeBoardDetailCommandAdapter
import kr.loner.fx.ui.base.BaseDialog
import kr.loner.fx.ui.base.BaseFragment
import kr.loner.fx.ui.util.Contents
import kr.loner.fx.viewmodel.MainViewModel
import kr.loner.fx.viewmodel.NoticeBoardViewModel
import kr.loner.fx.viewmodel.factory.ViewModelFactory

class NoticeBoardDetailFragment : BaseFragment<FragmentNoticeboardDetailBinding>(
    R.layout.fragment_noticeboard_detail,
    NoticeBoardViewModel::class.java
) {

    private val mainVm by viewModels<MainViewModel> {
        ViewModelFactory(
            FXDataBase.getInstance(requireContext()).userDao,
            FXDataBase.getInstance(requireContext()).gameDao
        )
    }

    override fun FragmentNoticeboardDetailBinding.setDataBind() {
        setData()
        ibnCommandDialogOn.setOnClickListener {
            vm!!.userData ?: return@setOnClickListener
            dialogCommandEditShow(vm!!.userData!!, false)
        }
        ibnLikeSend.setOnClickListener {
            vm!!.userData ?: return@setOnClickListener

            if(noticeBoard!!.likeCountList?.contains(vm!!.userData!!.name) == false ) {
                vm!!.sendLike(vm!!.userData!!.name)
                noticeBoard!!.likeCountList?.toMutableList()?.add(vm!!.userData!!.name)
                toastShort("좋아요 버튼을 눌렀습니다.")
            }else {
                vm!!.deleteLike(vm!!.userData!!.name)
                noticeBoard!!.likeCountList?.toMutableList()?.remove(vm!!.userData!!.name)
                toastShort("좋아요를 취소했습니다.")
            }
        }
    }

    private fun FragmentNoticeboardDetailBinding.setData() {

        mainVm.userData.observe(viewLifecycleOwner, { vm!!.userData = it })
        vm!!.noticeBoardIdx = requireActivity().intent.getStringExtra("noticeBoardId")
        vm!!.getNoticeBoard()

        vm!!.noticedBoard.observe(viewLifecycleOwner, { noticeItem ->
            noticeBoard = noticeItem
            noticeBoard?.replyList ?: return@observe

            rvCommandList.adapter = NoticeBoardDetailCommandAdapter { reply ->

                    vm!!.selectReply = reply

                dialogCommandEditShow(vm!!.userData!!, true )
            }.apply {
                replyList = noticeItem.replyList?.values!!.toList()
            }
        })
    }

    private fun FragmentNoticeboardDetailBinding.dialogCommandEditShow(
        user: UserData,
        toReplyMode: Boolean,
    ) {
        val dialog =
            BaseDialog<DialogCommandEditBinding>(requireContext(), R.layout.dialog_command_edit)
        dialog.setWindowManager(Gravity.BOTTOM, 0.3f, true)

        dialog.binding.btnCommandEdit.setOnClickListener {


            val newReply = Reply(
                idx = Contents.replyRandomId(),
                parentIdx = vm!!.selectReply?.idx?:"0000",
                writeName = user.name,
                content = dialog.binding.etNoticeBoardCommand.text.toString(),
                replyToReply = hashMapOf(),
                likeCountList = listOf(),
                timestamp = Timestamp.now()
            )

            if (!toReplyMode)
                vm!!.setReply(newReply)
            else {
                //댓글의 대댓글을 클릭한 상태
                if(noticeBoard!!.replyList?.values?.find { it.idx == vm!!.selectReply?.idx } == null) {
                    newReply.parentIdx = vm!!.selectReply?.parentIdx

                }
                vm!!.setReplyToReply(newReply)
            }
            toastShort("댓글이 업로드 되었습니다.")
            dialog.dismiss()
        }
        dialog.show()
    }
}