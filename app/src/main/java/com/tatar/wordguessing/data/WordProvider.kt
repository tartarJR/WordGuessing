package com.tatar.wordguessing.data

class WordProvider {

    private var wordList = listOf(
        "arrow",
        "basketball",
        "king",
        "football",
        "dog",
        "change",
        "snake",
        "kebab",
        "happy",
        "blade",
        "bat",
        "date",
        "chair",
        "piano",
        "subway",
        "house",
        "fish",
        "plane",
        "eagle",
        "exchange",
        "backpack",
        "ride",
        "sword",
        "sponge",
        "beer"
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