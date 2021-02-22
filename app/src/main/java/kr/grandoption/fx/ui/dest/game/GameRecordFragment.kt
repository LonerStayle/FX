package kr.grandoption.fx.ui.dest.game

import android.content.Intent
import androidx.activity.addCallback
import kr.grandoption.fx.R
import kr.grandoption.fx.databinding.FragmentGameRecordBinding
import kr.grandoption.fx.ui.activity.MainActivity
import kr.grandoption.fx.ui.adapter.GameRecordAdapter
import kr.grandoption.fx.ui.base.BaseFragment
import kr.grandoption.fx.viewmodel.GameViewModel

class GameRecordFragment : BaseFragment<FragmentGameRecordBinding>(
    R.layout.fragment_game_record,
    GameViewModel::class.java
) {

    override fun FragmentGameRecordBinding.setDataBind() {
        rvGameFlow.adapter = GameRecordAdapter()
        vm!!.gameList.observe(viewLifecycleOwner, {
            (rvGameFlow.adapter as GameRecordAdapter).run {
                gameModelList = it
                notifyDataSetChanged()
            }
        })

        requireActivity().onBackPressedDispatcher.addCallback {
            startActivity(Intent(requireContext(), MainActivity::class.java))
        }
    }
}

