package com.xfinity.characterviewer.ui.character.list

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.transition.Fade
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.xfinity.characterviewer.App
import com.xfinity.characterviewer.R
import com.xfinity.characterviewer.adapter.OnItemClickListener
import com.xfinity.characterviewer.adapter.character.CharacterAdapter
import com.xfinity.characterviewer.architecture.BaseMvpFragment
import com.xfinity.characterviewer.models.Character
import com.xfinity.characterviewer.ui.character.detail.CharacterDetailFragment
import com.xfinity.characterviewer.ui.character.detail.DetailTransition


/**
 * Created by Mert Vurgun on 2/2/2018.
 */

class CharacterListFragment : BaseMvpFragment<CharacterListView, CharacterListPresenter>(),
        CharacterListView, OnItemClickListener<CharacterAdapter.ViewHolder, Character> {

    private var recyclerView: RecyclerView? = null

    private val adapter: CharacterAdapter? by lazy { context?.let { CharacterAdapter(it, null, this) } }


    override var mPresenter: CharacterListPresenter = CharacterListPresenter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_character_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(view.findViewById<RecyclerView>(R.id.recycler)) {
            adapter = this@CharacterListFragment.adapter
            this@CharacterListFragment.recyclerView = this
            regulateGridManager()
        }
        //After rotation, retain details
        savedInstanceState?.let { mPresenter.performTabletClick { onItemClick(null, it) } }
    }

    //Presenter loads items when it is ready
    override fun loadItems(list: List<Character>) {
        adapter?.setItems(list)
        mPresenter.performTabletClick { onItemClick(null, it) }
    }


    override fun onItemClick(viewholder: CharacterAdapter.ViewHolder?, item: Character) {

        mPresenter.selectedCharacter = item

        activity?.let {

            with(it.supportFragmentManager.findFragmentById(R.id.fragment_details) as CharacterDetailFragment?
                    ?: CharacterDetailFragment(item)) {


                if (App.appInstance.isTablet) {
                    this.updateCharacter(item)
                } else {

                    this@CharacterListFragment.exitTransition = Fade()


                    //Details fragment transitions
                    setSharedElementEnterTransition(DetailTransition())
                    enterTransition = Fade()
                    sharedElementReturnTransition = DetailTransition()

                    it.supportFragmentManager.beginTransaction()
                            .addSharedElement(viewholder?.title, it.getString(R.string.character_title_transition_name))
                            .replace(R.id.container, this)
                            .addToBackStack(null)
                            .commit()
                }
            }
        }
    }

    /**
     * @param isCreate : On creation or re-creation of recycler, this parameter will help
     * to retain the layout manager's column state from presenter which will be alive when
     * pressed back after details
     */
    fun regulateGridManager(isCreate: Boolean = true) {
        recyclerView?.layoutManager = GridLayoutManager(context, mPresenter.getColumns(isCreate))
    }

}