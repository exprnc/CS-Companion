package com.exprnc.cscompanion.presentation.features.maps

import com.exprnc.cscompanion.R
import com.exprnc.cscompanion.architecture.BaseViewModel
import com.exprnc.cscompanion.architecture.Intent
import com.exprnc.cscompanion.architecture.ViewEvent
import com.exprnc.cscompanion.domain.model.Grenade
import com.exprnc.cscompanion.domain.model.Map
import com.exprnc.cscompanion.domain.model.Position
import com.exprnc.cscompanion.domain.repository.GrenadeRepository
import com.exprnc.cscompanion.domain.repository.MapRepository
import com.exprnc.cscompanion.domain.repository.PositionRepository
import com.exprnc.cscompanion.presentation.features.radar.RadarArgs
import com.exprnc.cscompanion.presentation.features.radar.RadarScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MapsViewModel @Inject constructor(
    private val mapRepository: MapRepository,
    private val grenadeRepository: GrenadeRepository,
    private val positionRepository: PositionRepository,
) : BaseViewModel() {

    init {
        loadMaps()
    }

    private fun loadMaps() {
        launchCoroutine {
            runCatching {
                mapRepository.getMapsByType("competitive") to mapRepository.getMapsByType("wingman")
            }.onSuccess { (competitiveMaps, wingmanMaps) ->
                setState(MapsViewState.Success(competitiveMaps, wingmanMaps))
            }.onFailure { e ->
                e.printStackTrace()
                setState(MapsViewState.Error(e.message ?: "Error not found"))
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
}