package kr.loner.fx.ui.dest.main

import android.content.Intent
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainer
import androidx.fragment.app.FragmentContainerView
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import kr.loner.fx.R
import kr.loner.fx.databinding.FragmentMainBinding
import kr.loner.fx.databinding.FragmentNoticeboardBinding
import kr.loner.fx.db.entity.NoticeBoard
import kr.loner.fx.ui.activity.NoticeBoardActivity
import kr.loner.fx.ui.adapter.NoticeBoardAdapter
import kr.loner.fx.ui.base.BaseFragment
import kr.loner.fx.viewmodel.MainViewModel

class NoticeBoardFragment : BaseFragment<FragmentNoticeboardBinding>(
    R.layout.fragment_noticeboard,
    MainViewModel::class.java
) {
    override fun FragmentNoticeboardBinding.setDataBind() {
        setHasOptionsMenu(true)
        vm!!.getNoticeBoardList()
        vm!!.noticeBoardList.observe(requireActivity(), {
            vpNoticeBoardNotification.adapter = NoticeBoardAdapter(it, NOTIFICATION_ADAPTER)
            rvPostList.adapter = NoticeBoardAdapter(it, LIST_ADAPTER)
        })
    }

    //이 프레그먼트에서만 게시판 글쓰기 버튼 활성화 설정
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.notice_board_create, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_goToTheNoticeBoardUpload)
            Navigation.findNavController(requireActivity(),R.id.nav_host_fragment_container_start).
            navigate(R.id.action_mainFragment_to_noticeBoardUpLoadFragment)

        return super.onOptionsItemSelected(item)
    }

    companion object {
        const val LIST_ADAPTER = 0
        const val NOTIFICATION_ADAPTER = 1
    }
}