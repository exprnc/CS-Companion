package com.exprnc.cscompanion.presentation.features.radar

import com.exprnc.cscompanion.architecture.BaseViewModel
import com.exprnc.cscompanion.architecture.ViewEvent
import com.exprnc.cscompanion.domain.repository.GrenadeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RadarViewModel @Inject constructor(
    private val grenadeRepository: GrenadeRepository
) : BaseViewModel<RadarViewState, RadarViewIntent>(RadarViewState.EmptyState) {

    init {
        loadGrenades()
    }

    private fun loadGrenades() {
        launchCoroutine {
            runCatching {
                grenadeRepository.getGrenadesByMapId("ТУТ MAPID")
            }.onSuccess { grenades ->
                setState(RadarViewState.Success(grenades))
            }.onFailure { e ->
                e.printStackTrace()
                setState(RadarViewState.Error(e.message ?: "Error not found"))
            }
        }
    }

    override fun obtainIntent(intent: RadarViewIntent) {
        when (intent) {
            RadarViewIntent.OnBackPressed -> emitEvent(ViewEvent.PopBackStack())
            RadarViewIntent.OnGrenadeClicked -> TODO()
            RadarViewIntent.OnPositionClicked -> TODO()
        }
    }
}