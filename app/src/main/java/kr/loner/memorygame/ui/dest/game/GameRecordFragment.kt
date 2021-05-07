package kr.loner.memorygame.ui.dest.game

import android.content.Intent
import androidx.activity.addCallback
import kr.loner.memorygame.R
import kr.loner.memorygame.databinding.FragmentGameRecordBinding
import kr.loner.memorygame.ui.activity.MainActivity
import kr.loner.memorygame.ui.adapter.GameRecordAdapter
import kr.loner.memorygame.ui.base.BaseFragment
import kr.loner.memorygame.viewmodel.GameViewModel

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

