package kr.loner.fx.ui.dest.noticeboard

import android.util.Log
import android.view.Gravity
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.LinearLayout
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
            vm!!.sendLike(vm!!.userData!!.name)
            noticeBoard.likeCountList?.toMutableList()?.add(vm!!.userData!!.name)
            toastShort("좋아요 버튼을 눌렀습니다.")
        }
    }

    private fun FragmentNoticeboardDetailBinding.setData() {

        mainVm.userData.observe(viewLifecycleOwner, { vm!!.userData = it })
        vm!!.noticeBoardIdx = requireActivity().intent.getStringExtra("noticeBoardId")
        vm!!.getNoticeBoard()

        vm!!.noticedBoard.observe(viewLifecycleOwner, { noticeItem ->
            noticeBoard = noticeItem
            noticeBoard?.replyList?:return@observe
            rvCommandList.adapter = NoticeBoardDetailCommandAdapter(noticeBoard!!.replyList!!.values.toList()) {
                vm!!.selectReplyIdx = it
                dialogCommandEditShow(vm!!.userData!!, true)
            }
        })
    }

    private fun FragmentNoticeboardDetailBinding.dialogCommandEditShow(
        user: UserData,
        toReplyMode: Boolean
    ) {
        val dialog =
            BaseDialog<DialogCommandEditBinding>(requireContext(), R.layout.dialog_command_edit)
        dialog.setWindowManager(Gravity.BOTTOM, 0.3f, true)

        dialog.binding.btnCommandEdit.setOnClickListener {
            toastShort("구현 대기중입니다.")
//            val reply = Reply(
//                Contents.replyRandomId, user.name,
//                dialog.binding.etNoticeBoardCommand.text.toString(),
//                listOf(), listOf(), Timestamp.now()
//            )
//            if (!toReplyMode)
//                vm!!.setReply(reply)
//            else
//                vm!!.setReplyToReply(reply)
        }
        dialog.show()
    }
}