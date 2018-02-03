package com.xfinity.characterviewer.ui.character.detail

import android.transition.ChangeBounds
import android.transition.ChangeImageTransform
import android.transition.ChangeTransform
import android.transition.TransitionSet

/**
 * Created by Mert Vurgun on 2/2/2018.
 */

/**
 * Custom combined transitions
 */
class DetailTransition : TransitionSet() {

    init {
        ordering = TransitionSet.ORDERING_TOGETHER
        addTransition(ChangeBounds())
                .addTransition(ChangeTransform())
                .addTransition(ChangeImageTransform())
    }
}