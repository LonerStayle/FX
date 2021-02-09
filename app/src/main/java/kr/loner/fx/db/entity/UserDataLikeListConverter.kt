package kr.loner.fx.db.entity

import androidx.room.TypeConverter
import com.google.gson.Gson

class UserDataLikeListConverter {
    @TypeConverter
    fun listToJson(likeList:List<String>) = Gson().toJson(likeList)

    @TypeConverter
    fun jsonToList(value:String)=
        Gson().fromJson(value,Array<String>::class.java).toList()


}