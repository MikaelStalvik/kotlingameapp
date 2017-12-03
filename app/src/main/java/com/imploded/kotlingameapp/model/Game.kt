package com.imploded.kotlingameapp.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Mikael on 2017-12-03.
 */
data class GameListResult(val list: List<Game>)
data class Game(
        @SerializedName("Id") val id: String,
        @SerializedName("Description")val description: String,
        @SerializedName("Name")val name: String,
        @SerializedName("Platform")val platform: String,
        @SerializedName("Publisher")val publisher: String,
        @SerializedName("ReleaseYear")val releaseYear: Int,
        @SerializedName("Picture")val picture: String
)