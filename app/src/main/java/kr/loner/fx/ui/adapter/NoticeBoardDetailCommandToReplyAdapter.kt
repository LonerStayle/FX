package kr.loner.fx.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import kr.loner.fx.R
import kr.loner.fx.databinding.ItemNoticeboardDetailCommandBinding
import kr.loner.fx.databinding.ItemNoticeboardDetailCommandToReplyBinding
import kr.loner.fx.db.entity.Reply

class NoticeBoardDetailCommandToReplyAdapter(
    var replyList: List<Reply> = listOf(),
    val clickEvent: () -> Unit
) :
    RecyclerView.Adapter<NoticeBoardDetailCommandToReplyAdapter.ViewHolder>() {

    inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val binding = DataBindingUtil.bind<ItemNoticeboardDetailCommandToReplyBinding>(v)

        init {
            v.setOnClickListener {
                clickEvent()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_noticeboard_detail_command_to_reply, parent, false)
        )

    override fun getItemCount(): Int = replyList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


        holder.binding?.apply {
            visible = position == 0
            reply = replyList[position]
        }
    }
}