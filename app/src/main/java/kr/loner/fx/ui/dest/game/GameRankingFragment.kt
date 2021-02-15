package kr.loner.fx.ui.dest.game

import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import kr.loner.fx.R
import kr.loner.fx.databinding.FragmentGameRankingBinding
import kr.loner.fx.ui.adapter.GameRankingAdapter
import kr.loner.fx.ui.base.BaseFragment
import kr.loner.fx.viewmodel.GameViewModel

class GameRankingFragment:BaseFragment<FragmentGameRankingBinding>(R.layout.fragment_game_ranking,
GameViewModel::class.java) {
    override fun FragmentGameRankingBinding.setDataBind() {
        (activity as AppCompatActivity?)!!.supportActionBar!!.title = "랭킹 보기"
        vm!!.getRankingList()
        vm!!.rankingList.observe(viewLifecycleOwner,{
            it.sortedByDescending { rank -> rank.score }.also { list ->
                rvRankingList.adapter = GameRankingAdapter(list)
            }
        })

        fabGoToTheGame.setOnClickListener {
            findNavController().navigate(R.id.action_gameRankingFragment_to_gameSettingFragment)
        }
    }
}