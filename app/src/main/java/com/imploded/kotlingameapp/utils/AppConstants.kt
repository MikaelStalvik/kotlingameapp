package com.imploded.kotlingameapp.utils

object AppConstants {
    val LoginUrl = "http://kotlinserver.azurewebsites.net/login"
    fun DetailUrl(id: String) = "http://kotlinserver.azurewebsites.net/games/$id"
    val GetGamesUrl = "http://kotlinserver.azurewebsites.net/games"
}