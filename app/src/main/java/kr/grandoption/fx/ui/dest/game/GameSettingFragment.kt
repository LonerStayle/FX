package kr.grandoption.fx.ui.dest.game

import androidx.navigation.fragment.findNavController
import kr.grandoption.fx.R
import kr.grandoption.fx.databinding.FragmentGameSettingBinding
import kr.grandoption.fx.ui.base.BaseFragment
import kr.grandoption.fx.viewmodel.GameViewModel

class GameSettingFragment : BaseFragment<FragmentGameSettingBinding>(R.layout.fragment_game_setting,
GameViewModel::class.java){

    override fun FragmentGameSettingBinding.setDataBind() {
        goToTheRecordCheck()
        buttonGameStart.setOnClickListener {
            findNavController().navigate(
                GameSettingFragmentDirections.actionGameSettingFragmentToGameFlowFragment(
                    maxRound = numberPickerRound.value,
                    row = numberPickerRow.value,
                    col = numberPickerCol.value
                )
            )
        }
    }

    private fun goToTheRecordCheck(){
        requireActivity().intent.getBooleanExtra("goToTheRecord",false).also {
            if(it)
                findNavController().navigate(R.id.action_global_gameRecordFragment)
            else
                return
        }
    }
}