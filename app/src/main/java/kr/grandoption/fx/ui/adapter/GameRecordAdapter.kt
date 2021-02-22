package kr.grandoption.fx.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import kr.grandoption.fx.R
import kr.grandoption.fx.databinding.ItemGameRecordBinding
import kr.grandoption.fx.db.entity.GameModel

class GameRecordAdapter(
    var gameModelList: List<GameModel> = listOf()
) : RecyclerView.Adapter<GameRecordAdapter.ViewHolder>() {
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = DataBindingUtil.bind<ItemGameRecordBinding>(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_game_record, parent, false)
        )

    override fun getItemCount() = gameModelList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding?.apply {
            val gameResult =  gameModelList[holder.adapterPosition]

         gameModel = gameResult
        }
    }


}