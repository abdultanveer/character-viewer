package com.xfinity.characterviewer.ui.character.list

import com.xfinity.characterviewer.App
import com.xfinity.characterviewer.architecture.BaseMvpPresenter
import com.xfinity.characterviewer.models.Character
import com.xfinity.characterviewer.network.WebServices

/**
 * Created by Mert Vurgun on 2/2/2018.
 */

class CharacterListPresenter : BaseMvpPresenter<CharacterListView>() {

    var grid = false

    private var characters: List<Character>? = null

    var selectedCharacter: Character? = null

    init {
        fetchCharacters()
    }

    private fun fetchCharacters() {

        characters?.let {
            mView?.loadItems(it)
        } ?: WebServices.request(WebServices.characterService.characters(),
                { response ->
                    with(response.getCharacters()) {
                        characters = this
                        if (this.isNotEmpty()) selectedCharacter = this[0]
                        mView?.loadItems(this)
                    }
                },
                { error -> error?.message?.let { it -> mView?.showError(it) } })
    }


    fun getColumns(isCreate: Boolean): Int {
        if (!isCreate) {
            grid = !grid
        }
        return if (grid) 2 else 1
    }

    fun performTabletClick(invoke: (Character) -> Unit) {
        if (App.appInstance.isTablet) selectedCharacter?.let { invoke(it) }
    }
}