package com.xfinity.characterviewer.architecture

/**
 * Created by Mert Vurgun on 1/31/2018.
 */

interface IBaseMvpPresenter<in V : BaseMvpView> {

    fun attachView(view: V)

    fun detachView()
}