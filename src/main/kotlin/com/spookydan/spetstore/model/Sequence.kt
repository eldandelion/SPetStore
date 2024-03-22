package com.spookydan.spetstore.model

import java.io.Serializable

class Sequence : Serializable {
    private var name: String? = null
    private var nextId: Int = 0

    constructor()

    constructor(name: String?, nextId: Int) {
        this.name = name
        this.nextId = nextId
    }

    fun getName(): String? {
        return name
    }

    fun setName(name: String?) {
        this.name = name
    }

    fun getNextId(): Int {
        return nextId
    }

    fun setNextId(nextId: Int) {
        this.nextId = nextId
    }
}