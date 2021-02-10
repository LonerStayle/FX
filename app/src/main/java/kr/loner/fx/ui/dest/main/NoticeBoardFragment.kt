package kr.loner.fx.ui.dest.main

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import kr.loner.fx.R
import kr.loner.fx.databinding.FragmentNoticeboardBinding
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
            val list = it.sortedBy { it.timestamp?.seconds }
            vpNoticeBoardNotification.adapter = NoticeBoardAdapter(list, NOTIFICATION_ADAPTER)
            rvPostList.adapter = NoticeBoardAdapter(list, LIST_ADAPTER)
        })
    }

    //이 프레그먼트에서만 게시판 글쓰기 버튼 활성화 설정
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.notice_board_create, menu)
        (activity as AppCompatActivity?)!!.supportActionBar!!.apply {
            show()
            title = "후기 게시판"
        }
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