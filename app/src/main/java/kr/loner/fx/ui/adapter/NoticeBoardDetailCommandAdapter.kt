package kr.loner.fx.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import kr.loner.fx.R
import kr.loner.fx.databinding.ItemNoticeboardDetailCommandBinding
import kr.loner.fx.db.entity.Reply

class NoticeBoardDetailCommandAdapter(val clickEvent: (Reply) -> Unit) :
    RecyclerView.Adapter<NoticeBoardDetailCommandAdapter.ViewHolder>() {
    var replyList: List<Reply> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val binding = DataBindingUtil.bind<ItemNoticeboardDetailCommandBinding>(v)

        init {
            itemView.setOnClickListener {
                val reply = replyList[adapterPosition]
                clickEvent(reply)
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
            reply!!.replyToReply?: return@apply
            rvToReply.adapter = NoticeBoardDetailCommandToReplyAdapter {reply->
                clickEvent(reply)
            }.apply {
                replyList = reply!!.replyToReply?.values?.toList()!!
            }

        }
    }

    override fun getItemId(position: Int): Long {
        return replyList[position].idx!!.toLong()
    }
}
