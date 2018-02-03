package com.xfinity.characterviewer.ui.character.list

import com.xfinity.characterviewer.architecture.BaseMvpView
import com.xfinity.characterviewer.models.Character

/**
 * Created by Mert Vurgun on 2/2/2018.
 */

interface CharacterListView : BaseMvpView {

    fun loadItems(list: List<Character>)
}