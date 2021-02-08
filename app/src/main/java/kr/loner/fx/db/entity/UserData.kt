package kr.loner.fx.db.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class UserData(
    val idx:Long = 0L,
    val name:String,
    val image:String,
): Parcelable

