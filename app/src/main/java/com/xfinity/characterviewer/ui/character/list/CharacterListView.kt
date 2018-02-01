package com.xfinity.characterviewer.ui.character.list

import com.xfinity.characterviewer.architecture.BaseMvpView
import com.xfinity.characterviewer.models.Character

/**
 * Created by Mert Vurgun on 1/31/2018.
 */

interface CharacterListView : BaseMvpView {

    fun loadCharacters(list: List<Character>)

    fun updateCharacter(character: Character)
}