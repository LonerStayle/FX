package kr.loner.fx.viewmodel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kr.loner.fx.db.entity.NoticeBoard
import kr.loner.fx.db.entity.Reply

class MainViewModel():ViewModel() {

   private val _muckDataList = MutableLiveData<List<NoticeBoard>>()
    val muckDataList:LiveData<List<NoticeBoard>>
    get() = _muckDataList

    fun getMuckDataList(){
            val muckReplyList: MutableList<Reply> = mutableListOf()
            val muckDataList: MutableList<NoticeBoard> = mutableListOf()
            for (i in 0..5) {
                muckDataList.add(
                    NoticeBoard(
                        "${i}_key",
                        "테스트${i}",
                        "테스트 게시글${i}",
                        "테스트 내용${i}\t테스트 내용${i}\t테스트 내용${i}\n테스트 내용${i}\t테스트 내용${i}\t",
                        null,
                        muckReplyList,
                        i
                    )
                )
            }
        _muckDataList.postValue(muckDataList)
    }


}