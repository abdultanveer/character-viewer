package com.xfinity.characterviewer.models

import java.io.Serializable

/**
 * Created by Mert Vurgun on 1/31/2018.
 */

data class Characters(
        private val RelatedTopics: List<Character>
) {
    fun getCharacters() = RelatedTopics
}

data class Character(
        val Result: String,
        val Text: String,
        val Icon: Icon,
        var isFavorite: Boolean = false
) : Serializable {
    fun title(): String = Text.substringBefore(" - ")
    fun description(): String = Text.substringAfter(" - ")
}

data class Icon(
        val URL: String, //https://duckduckgo.com/i/99b04638.png
        val Width: String,
        val Height: String
) : Serializable