package kr.loner.fx.viewmodel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch
import kr.loner.fx.db.entity.NoticeBoard

class NoticeBoardViewModel():ViewModel() {

    private val db = FirebaseFirestore.getInstance()

    private val _it = MutableLiveData<Any>()
    val it:LiveData<Any>
        get() = _it

    fun roominsert(it:String){
        viewModelScope.launch {

        }
    }


}