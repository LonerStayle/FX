package kr.loner.fx.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class GameModel(
    @PrimaryKey(autoGenerate = true)
    val id:Long= 0L,
    val round:Int,
    val row:Int,
    val col:Int
)