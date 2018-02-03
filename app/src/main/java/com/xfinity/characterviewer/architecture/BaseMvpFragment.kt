package com.xfinity.characterviewer.architecture

import android.os.Bundle
import android.support.v4.app.Fragment
import android.widget.Toast

/**
 * Created by Mert Vurgun on 1/31/2018.
 */

abstract class BaseMvpFragment<in V : BaseMvpView, T : IBaseMvpPresenter<V>> : Fragment(), BaseMvpView {

    protected abstract var mPresenter: T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mPresenter.attachView(this as V)
        retainInstance = true
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.detachView()
    }

    override fun showError(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    override fun setTitle(title: String) {
        activity?.actionBar?.title = title
    }

}