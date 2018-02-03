package com.xfinity.characterviewer.ui.character

import android.view.MenuItem
import com.xfinity.characterviewer.App
import com.xfinity.characterviewer.R
import com.xfinity.characterviewer.architecture.BaseMvpActivity
import com.xfinity.characterviewer.ui.character.list.CharacterListFragment

/**
 * Created by Mert Vurgun on 1/31/2018.
 */

class CharacterActivity : BaseMvpActivity<CharacterView, CharacterPresenter>(), CharacterView {

    override var mPresenter: CharacterPresenter = CharacterPresenter()

    //Set content view
    override fun getLayoutId(): Int = R.layout.activity_character

    //Init
    override fun findViews() {
        if (!App.appInstance.isTablet) {

            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, CharacterListFragment(), "list")
                    .addToBackStack("list")
                    .commit()
        }
    }

    //Inflate menu
    override fun getMenu(): Int = R.menu.menu

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_search -> {
                showError("Search")
            }
            R.id.action_switch -> {

                with(supportFragmentManager.findFragmentById(R.id.fragment_list)
                        ?: supportFragmentManager.findFragmentByTag("list")) {
                    (this as CharacterListFragment).regulateGridManager(false)
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    /**
     * Because of two fragments used in single activity, title needs to rebase on phones.
     */
    override fun onBackPressed() {
        actionBar.setTitle(R.string.app_name)
        super.onBackPressed()
    }
}