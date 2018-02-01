package com.xfinity.characterviewer

import android.app.Application

/**
 * Created by Mert Vurgun on 1/31/2018.
 */
class App : Application() {

    companion object {
        lateinit var appInstance: App
    }

    override fun onCreate() {
        super.onCreate()
        appInstance = this
    }

    val isTablet: Boolean by lazy {
        this.resources.getBoolean(R.bool.is_tablet)
    }

    fun getCharacterType() = resources.getString(R.string.character_type)
}