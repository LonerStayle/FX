package kr.grandoption.fx.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import kr.grandoption.fx.R
import kr.grandoption.fx.databinding.ItemNoticeboardDetailCommandToReplyBinding
import kr.grandoption.fx.db.entity.Reply

class NoticeBoardDetailCommandToReplyAdapter(
    val clickEvent: (Reply) -> Unit
) :
    RecyclerView.Adapter<NoticeBoardDetailCommandToReplyAdapter.ViewHolder>() {
    var replyList: List<Reply> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()

        }
    inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val binding = DataBindingUtil.bind<ItemNoticeboardDetailCommandToReplyBinding>(v)

        init {
            v.setOnClickListener {
                clickEvent(replyList[adapterPosition])
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
    override fun getItemId(position: Int): Long {
        return replyList[position].idx!!.toLong()
    }
}