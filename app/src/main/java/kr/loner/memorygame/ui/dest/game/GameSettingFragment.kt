package kr.loner.memorygame.ui.dest.game

import androidx.navigation.fragment.findNavController
import kr.loner.memorygame.R
import kr.loner.memorygame.databinding.FragmentGameSettingBinding
import kr.loner.memorygame.ui.base.BaseFragment
import kr.loner.memorygame.viewmodel.GameViewModel

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