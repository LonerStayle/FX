package kr.loner.fx.ui.dest.game

import android.content.Intent
import androidx.activity.OnBackPressedDispatcher
import androidx.activity.addCallback
import kr.loner.fx.R
import kr.loner.fx.databinding.FragmentGameRecordBinding
import kr.loner.fx.ui.activity.MainActivity
import kr.loner.fx.ui.adapter.GameRecordAdapter
import kr.loner.fx.ui.base.BaseFragment
import kr.loner.fx.viewmodel.GameViewModel

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

