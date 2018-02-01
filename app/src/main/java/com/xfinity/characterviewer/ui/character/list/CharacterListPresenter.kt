package com.xfinity.characterviewer.ui.character.list

import com.xfinity.characterviewer.architecture.BaseMvpPresenter
import com.xfinity.characterviewer.network.WebServices

/**
 * Created by Mert Vurgun on 1/31/2018.
 */

class CharacterListPresenter : BaseMvpPresenter<CharacterListView>() {

    private var grid = false

    /**
     * Initial network call
     */
    fun fetchCharacters() {
        WebServices.request(WebServices.characterService.characters(),
                { response -> mView?.loadCharacters(response.RelatedTopics) },
                { error -> error?.message?.let { it -> mView?.showError(it) } })
    }

    fun getColumns(): Int {
        grid = !grid
        return if (grid) 2 else 1
    }
}