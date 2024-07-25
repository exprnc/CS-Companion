package com.exprnc.cscompanion.presentation.features.detailgrenade

import com.exprnc.cscompanion.architecture.Args
import com.exprnc.cscompanion.domain.model.Position

data class ThrowingGrenadeArgs(
    val position: Position
) : Args