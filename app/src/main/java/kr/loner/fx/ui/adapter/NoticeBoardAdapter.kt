package kr.loner.fx.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import kr.loner.fx.R
import kr.loner.fx.databinding.ItemNoticeboardListBinding
import kr.loner.fx.databinding.ItemNoticeboardNotificationListBinding
import kr.loner.fx.db.entity.NoticeBoard
import kr.loner.fx.ui.dest.main.NoticeBoardFragment.Companion.LIST_ADAPTER

class NoticeBoardAdapter(
    var noticeBoardtList: List<NoticeBoard> = listOf(),
    val clickEvent: (NoticeBoard) -> Unit,
) :
    RecyclerView.Adapter<NoticeBoardAdapter.ViewHolder>() {
    var AdapterMode: Int? = null


    inner class ViewHolder : RecyclerView.ViewHolder {
        var listBinding: ItemNoticeboardListBinding? = null
        var notificationBinding: ItemNoticeboardNotificationListBinding? = null

        constructor(binding: ItemNoticeboardListBinding) : super(binding.root) {
            listBinding = binding
        }

        constructor(binding: ItemNoticeboardNotificationListBinding) : super(binding.root) {
            notificationBinding = binding
        }

        init {
            listBinding?.root?.setOnClickListener {
                clickEvent(noticeBoardtList[adapterPosition]) }
            notificationBinding?.root?.setOnClickListener {
                clickEvent(noticeBoardtList[adapterPosition]) }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        if (AdapterMode == LIST_ADAPTER) {
            val binding = DataBindingUtil.inflate<ItemNoticeboardListBinding>(
                LayoutInflater.from(parent.context), R.layout.item_noticeboard_list, parent, false
            )
            ViewHolder(binding)
        } else {
            val binding = DataBindingUtil.inflate<ItemNoticeboardNotificationListBinding>(
                LayoutInflater.from(parent.context),
                R.layout.item_noticeboard_notification_list,
                parent,
                false
            )
            ViewHolder(binding)
        }


    override fun getItemCount(): Int = noticeBoardtList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        if (AdapterMode == LIST_ADAPTER)
            holder.listBinding?.noticeBoard = noticeBoardtList[position]
        else
            holder.notificationBinding?.noticeBoard = noticeBoardtList[position]

    }


}
