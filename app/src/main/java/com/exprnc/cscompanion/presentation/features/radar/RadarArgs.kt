package com.exprnc.cscompanion.presentation.features.radar

import android.os.Parcelable
import androidx.core.os.bundleOf
import kotlinx.parcelize.Parcelize

@Parcelize
data class RadarArgs(
    val mapId: String
) : Parcelable {
    fun toBundle() = bundleOf(ARGS_KEY to this)

    companion object {
        const val ARGS_KEY = "ARGS_KEY"
    }
}