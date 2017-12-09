package com.imploded.kotlingameapp.model

import com.google.gson.annotations.SerializedName

data class Game(
        @SerializedName("Id") val id: String,
        @SerializedName("Description")val description: String,
        @SerializedName("Name")val name: String,
        @SerializedName("Publisher")val publisher: String,
        @SerializedName("ReleaseYear")val releaseYear: Int,
        @SerializedName("Picture")val picture: String,
        @SerializedName("Platforms")val platforms: List<String>
)