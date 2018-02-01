package com.xfinity.characterviewer.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * Created by Mert Vurgun on 1/31/2018.
 */

class ServiceGenerator {

    companion object {

        fun <T> generate(service: Class<T>): T = Retrofit.Builder()
                .baseUrl("http://api.duckduckgo.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(service)

    }
}