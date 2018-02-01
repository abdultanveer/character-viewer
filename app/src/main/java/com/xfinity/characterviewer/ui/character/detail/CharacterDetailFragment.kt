package com.xfinity.characterviewer.ui.character.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.xfinity.characterviewer.App
import com.xfinity.characterviewer.R
import com.xfinity.characterviewer.architecture.BaseMvpFragment
import com.xfinity.characterviewer.loadImage
import com.xfinity.characterviewer.models.Character

/**
 * Created by Mert Vurgun on 1/31/2018.
 */
@SuppressLint("ValidFragment")
class CharacterDetailFragment(val character: Character? = null) : BaseMvpFragment<CharacterDetailView, CharacterDetailPresenter>() {

    override var mPresenter: CharacterDetailPresenter = CharacterDetailPresenter()

    private lateinit var icon: ImageView
    private lateinit var title: TextView
    private lateinit var description: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        with(inflater.inflate(R.layout.fragment_character_detail, container, false)) {
            icon = this.findViewById(R.id.icon)
            title = this.findViewById(R.id.tvtitle)
            description = this.findViewById(R.id.description)
            character?.let { updateCharacter(it) }
            return this
        }
    }

    fun updateCharacter(character: Character) {
        context?.let { icon.loadImage(it, character.Icon.URL, R.drawable.ic_launcher_foreground) }
        character.title().run {
            title.text = this
            if (!App.appInstance.isTablet) setTitle(this)
        }
        description.text = character.description()
    }
}