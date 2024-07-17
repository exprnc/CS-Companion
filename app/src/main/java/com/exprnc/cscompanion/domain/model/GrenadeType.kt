package com.exprnc.cscompanion.domain.model

import com.exprnc.cscompanion.R

enum class GrenadeType(val resId: Int) {
    SMOKE(R.drawable.smoke),
    FLASHBANG(R.drawable.smoke), //todo R.drawable.flashbang
    HIGH_EXPLOSIVE(R.drawable.smoke), //todo R.drawable.high_explosive
    MOLOTOV(R.drawable.smoke), //todo R.drawable.molotov
    DECOY(R.drawable.smoke); //todo R.drawable.decoy
}