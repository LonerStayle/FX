package kr.loner.fx.ui.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import kr.loner.fx.R
import kr.loner.fx.databinding.ItemNoticeboardListBinding
import kr.loner.fx.databinding.ItemNoticeboardNotificationListBinding
import kr.loner.fx.db.entity.NoticeBoard
import kr.loner.fx.ui.activity.NoticeBoardActivity
import kr.loner.fx.ui.dest.main.NoticeBoardFragment.Companion.LIST_ADAPTER


class NoticeBoardAdapter(
    var noticeBoardtList: List<NoticeBoard> = listOf(),
    var adapterMode: Int
) : RecyclerView.Adapter<NoticeBoardAdapter.ViewHolder>() {


    inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var binding: ViewDataBinding? = null

        init {
            binding = if (adapterMode == LIST_ADAPTER)
                DataBindingUtil.bind<ItemNoticeboardListBinding>(v)
            else
                DataBindingUtil.bind<ItemNoticeboardNotificationListBinding>(v)

            binding?.root?.setOnClickListener { view ->
                Intent(view.context, NoticeBoardActivity::class.java).also {
                    it.putExtra("noticeBoard", noticeBoardtList[adapterPosition])
                    view.context.startActivity(it)
                }
            }
        }


    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        if (adapterMode == LIST_ADAPTER) {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_noticeboard_list, parent, false)
            ViewHolder(view)
        } else {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_noticeboard_notification_list, parent, false)
            ViewHolder(view)

        }


    override fun getItemCount(): Int = noticeBoardtList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        if (adapterMode == LIST_ADAPTER)
            (holder.binding as ItemNoticeboardListBinding).noticeBoard =
                noticeBoardtList[position]
        else
            (holder.binding as ItemNoticeboardNotificationListBinding).noticeBoard =
                noticeBoardtList[position]

    }


}
