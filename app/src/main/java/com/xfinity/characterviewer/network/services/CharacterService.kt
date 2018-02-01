package com.xfinity.characterviewer.network.services

import com.xfinity.characterviewer.App
import com.xfinity.characterviewer.models.Characters
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Mert Vurgun on 1/31/2018.
 */

interface CharacterService {

    /**
     * Retrofit encodes all plus (+) signs to %2B by default. By enabling query encoding
     * we can use + sign.
     */
    @GET("/")
    fun characters(@Query("q", encoded = true) q: String = App.appInstance.getCharacterType(),
                   @Query("format") format: String = "json"): Call<Characters>
}