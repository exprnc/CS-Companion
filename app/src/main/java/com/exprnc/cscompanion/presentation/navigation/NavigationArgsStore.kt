package com.exprnc.cscompanion.presentation.navigation

import com.exprnc.cscompanion.architecture.Args
import com.exprnc.cscompanion.architecture.Result

object NavigationArgsStore {
    private val argsMap: MutableMap<String, Args> = mutableMapOf()
    val resultMap: MutableMap<String, Result> = mutableMapOf()

    fun putArgs(key: String, args: Args) {
        argsMap[key] = args
    }

    @Suppress("UNCHECKED_CAST")
    fun <T : Args> getArgs(key: String): T {
        return requireNotNull(argsMap[key] as T)
    }

    fun removeArgs(key: String) {
        argsMap.remove(key)
    }
}