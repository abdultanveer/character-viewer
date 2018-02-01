package com.xfinity.characterviewer.architecture

import com.xfinity.characterviewer.App

/**
 * Created by Mert Vurgun on 1/31/2018.
 */

open class BaseMvpPresenter<V : BaseMvpView> : IBaseMvpPresenter<V> {

    protected var mView: V? = null

    override fun attachView(view: V) {
        mView = view
    }

    override fun detachView() {
        mView = null
    }

    fun isTablet() = App.appInstance.isTablet
}