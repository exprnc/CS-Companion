package com.exprnc.cscompanion.presentation.features.maps

import com.exprnc.cscompanion.architecture.BaseViewModel
import com.exprnc.cscompanion.architecture.ViewEvent
import com.exprnc.cscompanion.domain.repository.MapRepository
import com.exprnc.cscompanion.presentation.features.radar.RadarArgs
import com.exprnc.cscompanion.presentation.features.radar.RadarScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MapsViewModel @Inject constructor(
    private val mapRepository: MapRepository
) : BaseViewModel<MapsViewState, MapsViewIntent>(MapsViewState.EmptyState) {

    init {
        loadMaps()
    }

    private fun loadMaps() {
        launchCoroutine {
            runCatching {
                mapRepository.getMapsByActivePool(true) to mapRepository.getMapsByActivePool(false)
            }.onSuccess { (activeMaps, inactiveMaps) ->
                setState(MapsViewState.Success(activeMaps, inactiveMaps))
            }.onFailure { e ->
                e.printStackTrace()
                setState(MapsViewState.Error(e.message ?: "Error not found"))
            }
        }
    }

    override fun obtainIntent(intent: MapsViewIntent) {
        when(intent) {
            is MapsViewIntent.OnBackPressed -> emitEvent(ViewEvent.PopBackStack())
            is MapsViewIntent.OnMapClicked -> emitEvent(ViewEvent.Navigation(RadarScreen(RadarArgs("1"))))
        }
    }
}