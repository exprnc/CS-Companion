package com.exprnc.cscompanion.presentation.features.radar

import com.exprnc.cscompanion.architecture.BaseViewModel
import com.exprnc.cscompanion.architecture.Intent
import com.exprnc.cscompanion.architecture.ViewEvent
import com.exprnc.cscompanion.domain.repository.GrenadeRepository
import com.exprnc.cscompanion.presentation.navigation.NavigationArgsStore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import javax.inject.Inject

@HiltViewModel
class RadarViewModel @Inject constructor(
    private val grenadeRepository: GrenadeRepository
) : BaseViewModel() {

    init {
        loadGrenades()
    }

    private fun loadGrenades() {
        launchCoroutine {
            runCatching {
                val args = NavigationArgsStore.getArgs<RadarArgs>(RadarScreen.ROUTE)
                args to grenadeRepository.getGrenadesByMapId(args.map.mapId)
            }.onSuccess { (args, grenades) ->
                setState(RadarViewState.Success(args.map, grenades))
            }.onFailure { e ->
                e.printStackTrace()
                setState(RadarViewState.Error(e.message ?: "Error not found"))
            }
        }
    }

    override fun obtainIntent(intent: Intent) {
        when (intent) {
            RadarViewIntent.OnBackPressed -> TODO()
            RadarViewIntent.OnGrenadeClicked -> TODO()
            RadarViewIntent.OnPositionClicked -> TODO()
        }
    }

    override fun onCleared() {
        super.onCleared()
        NavigationArgsStore.removeArgs(RadarScreen.ROUTE)
    }
}