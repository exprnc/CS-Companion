package com.exprnc.cscompanion.presentation.features.maps

import com.exprnc.cscompanion.architecture.BaseViewModel
import com.exprnc.cscompanion.architecture.Intent
import com.exprnc.cscompanion.architecture.ViewEvent
import com.exprnc.cscompanion.domain.model.Map
import com.exprnc.cscompanion.domain.model.MapType
import com.exprnc.cscompanion.domain.repository.GrenadeRepository
import com.exprnc.cscompanion.domain.repository.MapRepository
import com.exprnc.cscompanion.domain.repository.PositionRepository
import com.exprnc.cscompanion.presentation.features.radar.RadarArgs
import com.exprnc.cscompanion.presentation.features.radar.RadarScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MapsViewModel @Inject constructor(
    private val mapRepository: MapRepository,
//    private val grenadeRepository: GrenadeRepository,
//    private val positionRepository: PositionRepository,
) : BaseViewModel() {

    private var competitiveMaps: List<Map> = emptyList()
    private var wingmanMaps: List<Map> = emptyList()

    private var errorMessage: String? = null

    init {
        setInitialState()
    }

    private fun setInitialState() {
        launchCoroutine {
            runCatching {
                competitiveMaps = mapRepository.getMapsByType(MapType.COMPETITIVE)
                wingmanMaps = mapRepository.getMapsByType(MapType.WINGMAN)
            }.onSuccess {
                errorMessage = null
                setState(defineState())
            }.onFailure { e ->
                e.printStackTrace()
                errorMessage = e.message
                setState(defineState())
            }
        }
    }

    override fun obtainIntent(intent: Intent) {
        when (intent) {
            is MapsViewIntent.OnBackPressed -> {
                emitEvent(ViewEvent.PopBackStack())
            }

            is MapsViewIntent.OnMapClicked -> {
                val args = RadarArgs(intent.map)
                emitEvent(ViewEvent.Navigation(RadarScreen(args)))
            }
        }
    }

    private fun defineState() : MapsViewState {
        errorMessage?.let {
            return MapsViewState.Error(it)
        }
        return MapsViewState.Success(
            competitiveMaps,
            wingmanMaps
        )
    }
}