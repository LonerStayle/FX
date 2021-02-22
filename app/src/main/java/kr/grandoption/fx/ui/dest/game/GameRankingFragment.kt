package kr.grandoption.fx.ui.dest.game

import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import kr.grandoption.fx.R
import kr.grandoption.fx.databinding.FragmentGameRankingBinding
import kr.grandoption.fx.ui.adapter.GameRankingAdapter
import kr.grandoption.fx.ui.base.BaseFragment
import kr.grandoption.fx.viewmodel.GameViewModel

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