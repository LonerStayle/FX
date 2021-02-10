package kr.loner.fx.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import kr.loner.fx.R
import kr.loner.fx.databinding.ItemNoticeboardDetailCommandBinding
import kr.loner.fx.db.entity.Reply

class NoticeBoardDetailCommandAdapter(var replyList: List<Reply> = listOf(), val clickEvent:(String)->Unit ) :
    RecyclerView.Adapter<NoticeBoardDetailCommandAdapter.ViewHolder>() {

    inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val binding = DataBindingUtil.bind<ItemNoticeboardDetailCommandBinding>(v)

        init {
            itemView.setOnClickListener {
                clickEvent(replyList[adapterPosition].idx!!)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_noticeboard_detail_command, parent, false)
        )

    override fun getItemCount(): Int = replyList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.binding?.apply {
            reply = replyList[position]
        }
    }
}