package com.xfinity.characterviewer.architecture

import android.content.Context

/**
 * Created by Mert Vurgun on 1/31/2018.
 */

interface BaseMvpView {

    fun getContext(): Context?

    fun setTitle(title: String)

    fun showError(message: String)
}