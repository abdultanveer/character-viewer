package com.xfinity.characterviewer.architecture

import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.view.Menu

/**
 * Created by Mert Vurgun on 1/31/2018.
 */

open class BaseActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (getLayoutId() != -1) setContentView(getLayoutId())
        findViews()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        if (getMenu() == 0) return false
        menuInflater.inflate(getMenu(), menu)
        return true
    }

    protected open fun findViews() {}

    /**
     * If more than one menu required, set this to 0 and override in child activities
     */
    open fun getMenu(): Int = -1

    open fun getLayoutId(): Int = -1

}