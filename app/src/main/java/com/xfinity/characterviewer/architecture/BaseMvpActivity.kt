package com.xfinity.characterviewer.architecture

import android.content.Context
import android.os.Bundle
import android.widget.Toast

/**
 * Created by Mert Vurgun on 1/31/2018.
 */

abstract class BaseMvpActivity<in V : BaseMvpView, T : IBaseMvpPresenter<V>>
    : BaseActivity(), BaseMvpView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mPresenter.attachView(this as V)
    }

    protected abstract var mPresenter: T

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.detachView()
    }

    override fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun setTitle(title: String) {
        this.title = title
    }

    override fun getContext(): Context = this
}