package com.exprnc.cscompanion.architecture

import android.net.Uri
import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.NavType
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

abstract class Screen(private val args: Bundle = Bundle.EMPTY, private val route: String) {
    fun getRoute() : String {
        val json = Uri.encode(Gson().toJson(args))
        return "$route/$json"
    }
}

class AssetParamType<T: Parcelable> : NavType<T>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): T? {
        return bundle.getParcelable(key)
    }

    override fun parseValue(value: String): T {
        return Gson().fromJson(value, object : TypeToken<T>() {}.type)
    }

    override fun put(bundle: Bundle, key: String, value: T) {
        bundle.putParcelable(key, value)
    }
}