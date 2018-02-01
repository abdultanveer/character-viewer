package com.xfinity.characterviewer.ui.character

import android.view.MenuItem
import com.xfinity.characterviewer.App
import com.xfinity.characterviewer.R
import com.xfinity.characterviewer.architecture.BaseMvpActivity
import com.xfinity.characterviewer.models.Character
import com.xfinity.characterviewer.replace
import com.xfinity.characterviewer.ui.character.detail.CharacterDetailFragment
import com.xfinity.characterviewer.ui.character.list.CharacterListFragment

/**
 * Created by Mert Vurgun on 1/31/2018.
 */

class CharacterActivity : BaseMvpActivity<CharacterView, CharacterPresenter>(), CharacterView,
        CharacterListFragment.OnCharacterSelectedListener {

    override var mPresenter: CharacterPresenter = CharacterPresenter()

    //Set content view
    override fun getLayoutId(): Int = R.layout.activity_character

    //Init
    override fun findViews() {
        if (!App.appInstance.isTablet) supportFragmentManager
                .replace(R.id.container, CharacterListFragment(), "list", false)
    }

    //Inflate menu
    override fun getMenu(): Int = R.menu.menu

    //List fragment communication
    override fun onCharacterSelected(character: Character) {

        if (App.appInstance.isTablet) {
            supportFragmentManager.findFragmentById(R.id.fragment_details)?.let {
                (it as CharacterDetailFragment).updateCharacter(character)
            }
        } else {
            with(CharacterDetailFragment(character)) {
                supportFragmentManager.replace(R.id.container, this, "details")
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_search -> {
                showError("Search")
            }
            R.id.action_switch -> {
                //Find list fragment based on device
                (if (App.appInstance.isTablet) supportFragmentManager.findFragmentById(R.id.fragment_list)
                else supportFragmentManager.findFragmentByTag("list"))?.let {
                    (it as CharacterListFragment).switchLayout()
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