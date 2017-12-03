package com.imploded.kotlingameapp.repository

import android.util.Log
import com.github.kittinunf.fuel.httpGet
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.imploded.kotlingameapp.model.Game
import com.imploded.kotlingameapp.model.GameListResult
import com.imploded.kotlingameapp.utils.fromJson
import java.net.URL
import javax.xml.transform.Result

/**
 * Created by Mikael on 2017-12-03.
 */
class MainRepository {

    fun getGames() : List<Game> {
        val url = "http://kotlinserver.azurewebsites.net/list"
        val json = URL(url).readText()
        return Gson().fromJson<List<Game>>(json)
        /*
        val json2 = url.httpGet().responseString { _, _, result ->
            val (data, error) = result
            Log.d("hej", result.toString())
            Log.d("hej2", data.toString())
            var json = Gson().toJson(data.toString())
            var obj = Gson().fromJson<List<Game>>(data.toString())
            val k = 123
        }*/
    }
}