package kr.grandoption.fx.db.entity

import android.os.Parcelable
import androidx.room.*
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class UserData(
    @PrimaryKey
    val idx:Long = 0L,
    val name:String? = null,
    @TypeConverters(UserDataLikeListConverter::class)
    val likeList:List<String> = listOf(),
): Parcelable
