package kr.loner.fx.viewmodel


import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import kr.loner.fx.db.entity.GameModel
import kr.loner.fx.repository.GameRepository
import kotlin.properties.Delegates

class GameViewModel(private val gameRepo: GameRepository) : ViewModel() {

    var displayWidth by Delegates.notNull<Int>()
    private val uiScope = CoroutineScope(Dispatchers.Main + Job())
    val gameModelList: LiveData<List<GameModel>>
        get() = gameRepo.getAllList()

    fun insert(round: Int, row: Int, col: Int) {
        uiScope.launch {

            withContext(Dispatchers.IO) {
                gameRepo.insert(GameModel(round = round, row = row, col = col))
            }
        }

    }


    override fun onCleared() {
        super.onCleared()
        uiScope.cancel()
    }
}