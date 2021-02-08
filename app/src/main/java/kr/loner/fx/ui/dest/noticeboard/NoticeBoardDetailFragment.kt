package kr.loner.fx.ui.dest.noticeboard

import kr.loner.fx.R
import kr.loner.fx.databinding.FragmentNoticeboardDetailBinding
import kr.loner.fx.ui.base.BaseFragment
import kr.loner.fx.viewmodel.NoticeBoardViewModel

class NoticeBoardDetailFragment : BaseFragment<FragmentNoticeboardDetailBinding>(
    R.layout.fragment_noticeboard_detail,
    NoticeBoardViewModel::class.java
) {
    override fun FragmentNoticeboardDetailBinding.setDataBind() {
        noticeBoard = requireActivity().intent.getParcelableExtra("noticeBoard")

    }
}