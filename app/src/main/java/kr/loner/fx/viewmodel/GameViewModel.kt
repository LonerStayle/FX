package kr.loner.fx.viewmodel


import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.*
import kr.loner.fx.db.entity.GameModel
import kr.loner.fx.repository.GameRepository
import kotlin.properties.Delegates
import androidx.lifecycle.MutableLiveData

class GameViewModel(private val gameRepo: GameRepository) : ViewModel() {

    val db = FirebaseFirestore.getInstance().collection("Game")
    var displayWidth by Delegates.notNull<Int>()

    private val uiScope = CoroutineScope(Dispatchers.Main + Job())

    val gameList: LiveData<List<GameModel>>
        get() = gameRepo.getAllList()


    private val _rankingList = MutableLiveData<List<GameModel>>()
    val rankingList: LiveData<List<GameModel>>
        get() = _rankingList

    var userName: String? = null
    var score: Int = 0

    fun insert(game: GameModel) {
        uiScope.launch {
            withContext(Dispatchers.IO) {
                gameRepo.insert(game)
            }
        }
    }

    fun insertRanking(game: GameModel) {
        uiScope.launch {
            db.document().set(game)
        }
    }

    fun getRankingList() {
        uiScope.launch {
            db.get().addOnSuccessListener {
                _rankingList.postValue(it.toObjects(GameModel::class.java).toList())
            }
        }
    }


    override fun onCleared() {
        super.onCleared()
        uiScope.cancel()
    }
}