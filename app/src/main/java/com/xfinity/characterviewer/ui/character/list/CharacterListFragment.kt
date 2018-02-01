package com.xfinity.characterviewer.ui.character.list

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.xfinity.characterviewer.R
import com.xfinity.characterviewer.adapter.CharacterAdapter
import com.xfinity.characterviewer.architecture.BaseMvpFragment
import com.xfinity.characterviewer.models.Character


/**
 * Created by Mert Vurgun on 1/31/2018.
 */

class CharacterListFragment : BaseMvpFragment<CharacterListView, CharacterListPresenter>(), CharacterListView {

    private lateinit var recyclerView: RecyclerView
    private lateinit var listener: OnCharacterSelectedListener

    override var mPresenter: CharacterListPresenter = CharacterListPresenter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        if (context is OnCharacterSelectedListener) {
            listener = context as OnCharacterSelectedListener
        }

        with(inflater.inflate(R.layout.fragment_character_list, container, false)) {
            recyclerView = this.findViewById(R.id.recycler)
            recyclerView.layoutManager = GridLayoutManager(context, 1)
            return this
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mPresenter.fetchCharacters()
    }

    override fun loadCharacters(list: List<Character>) {
        context?.let {
            recyclerView.adapter = CharacterAdapter(it, list, { updateCharacter(it) })
            if (mPresenter.isTablet() && list.isNotEmpty()) updateCharacter(list[0])
        }
    }

    override fun updateCharacter(character: Character) {
        listener.onCharacterSelected(character)
    }

    /**
     * Switch between list and grid view of list
     */
    fun switchLayout() {
        recyclerView.layoutManager = GridLayoutManager(context, mPresenter.getColumns())
    }

    interface OnCharacterSelectedListener {
        fun onCharacterSelected(character: Character)
    }
}