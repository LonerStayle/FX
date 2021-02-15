package kr.loner.fx.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import kr.loner.fx.R
import kr.loner.fx.databinding.ItemGameRankingBinding
import kr.loner.fx.db.entity.GameModel

class GameRankingAdapter(
    var rankingList: List<GameModel> = listOf()
) :
    RecyclerView.Adapter<GameRankingAdapter.ViewHolder>() {

    inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val binding = DataBindingUtil.bind<ItemGameRankingBinding>(v)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_game_ranking, parent, false)
        )

    override fun getItemCount(): Int = rankingList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding?.apply {
          game = rankingList[position]
          ranking = position + 1
        }
    }
}