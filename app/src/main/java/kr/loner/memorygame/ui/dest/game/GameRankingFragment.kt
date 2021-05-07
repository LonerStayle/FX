package kr.loner.memorygame.ui.dest.game

import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import kr.loner.memorygame.R
import kr.loner.memorygame.databinding.FragmentGameRankingBinding
import kr.loner.memorygame.ui.adapter.GameRankingAdapter
import kr.loner.memorygame.ui.base.BaseFragment
import kr.loner.memorygame.viewmodel.GameViewModel

class GameRankingFragment : BaseFragment<FragmentGameRankingBinding>(
    R.layout.fragment_game_ranking,
    GameViewModel::class.java
) {
    override fun FragmentGameRankingBinding.setDataBind() {
        (activity as AppCompatActivity?)!!.supportActionBar!!.apply {
            title = "랭킹 보기"
            setBackgroundDrawable(
                ColorDrawable(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.colorVeryWhite
                    )
                )
            )
        }

        vm!!.getRankingList()
        vm!!.rankingList.observe(viewLifecycleOwner, {
            it.sortedByDescending { rank -> rank.score }.also { list ->
                rvRankingList.adapter = GameRankingAdapter(list)
            }
        })

        fabGoToTheGame.setOnClickListener {
            findNavController().navigate(R.id.action_gameRankingFragment_to_gameSettingFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        (activity as AppCompatActivity?)!!.supportActionBar!!.apply {
            title = "기억력게임"
            setBackgroundDrawable(
                ColorDrawable(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.colorPrimary
                    )
                )
            )
        }
    }
}