package com.tatar.wordguessing.data

class WordProvider {

    private var wordList = listOf(
        "basketball",
        "king",
        "football",
        "dog",
        "change",
        "snake",
        "kebab",
        "happy",
        "date",
        "chair",
        "piano",
        "subway",
        "house",
        "house",
        "fish",
        "plane",
        "eagle",
        "exchange",
        "backpack",
        "ride",
        "sponge"
    )

    private lateinit var words: MutableList<String>

    fun refreshList() {
        words = wordList.toMutableList()
        words.shuffle()
    }

    fun isWordListEmpty(): Boolean {
        return words.isEmpty()
    }

    fun getWord(): String {
        return words.removeAt(0)
    }
}