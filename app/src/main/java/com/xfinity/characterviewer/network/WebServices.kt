package com.xfinity.characterviewer.network

import com.xfinity.characterviewer.network.services.CharacterService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Mert Vurgun on 1/31/2018.
 */

class WebServices {

    companion object {
        val characterService = ServiceGenerator.generate(CharacterService::class.java)

        fun <S> request(call: Call<S>, callback: Callback<S>) {
            call.enqueue(callback)
        }

        fun <S> request(call: Call<S>, onSuccess: (S) -> Unit, onFail: (Throwable?) -> Unit) {
            request(call, object : Callback<S> {
                override fun onFailure(call: Call<S>, t: Throwable?) {
                    onFail(t)
                }

                override fun onResponse(call: Call<S>, response: Response<S>) {
                    if (response.isSuccessful) response.body()?.let { onSuccess(it) }
                    onFail(null)

                }

            })
        }
    }
}