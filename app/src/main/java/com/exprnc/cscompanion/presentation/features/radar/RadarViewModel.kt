package com.exprnc.cscompanion.presentation.features.radar

import com.exprnc.cscompanion.architecture.BaseViewModel
import com.exprnc.cscompanion.architecture.Intent
import com.exprnc.cscompanion.architecture.ViewEvent
import com.exprnc.cscompanion.domain.model.Grenade
import com.exprnc.cscompanion.domain.model.Position
import com.exprnc.cscompanion.domain.repository.GrenadeRepository
import com.exprnc.cscompanion.domain.repository.PositionRepository
import com.exprnc.cscompanion.presentation.navigation.NavigationArgsStore
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RadarViewModel @Inject constructor(
    private val grenadeRepository: GrenadeRepository,
    private val positionRepository: PositionRepository,
) : BaseViewModel() {

    private val args: RadarArgs by lazy { NavigationArgsStore.getArgs(RadarScreen.ROUTE) }
    private var grenades: List<Grenade> = emptyList()
    private var positions: List<Position> = emptyList()

    private var selectedGrenade: Grenade? = null
    private var errorMessage: String? = null

    init {
        setInitialState()
    }

    private fun setInitialState() {
        launchCoroutine {
            runCatching {
                grenades = grenadeRepository.getGrenadesByMapId(args.map.mapId)
                positions = positionRepository.getPositionsByMapId(args.map.mapId)
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
            is RadarViewIntent.OnBackPressed -> {
                emitEvent(ViewEvent.PopBackStack())
            }

            is RadarViewIntent.OnGrenadeClicked -> {
                selectedGrenade = intent.grenade.takeIf { it != selectedGrenade }
                setState(defineState())
            }

            is RadarViewIntent.OnPositionClicked -> TODO()
        }
    }

    private fun defineState(): RadarViewState {
        errorMessage?.let {
            return RadarViewState.Error(it)
        }
        return if (selectedGrenade == null) {
            RadarViewState.DefaultState(
                map = args.map,
                grenades = grenades
            )
        } else {
            val grenadePositions = positions.filter { position ->
                position.grenadeId == selectedGrenade!!.grenadeId
            }
            RadarViewState.GrenadeSelectedState(
                map = args.map,
                grenade = selectedGrenade!!,
                positions = grenadePositions
            )
        }
    }

    override fun onCleared() {
        super.onCleared()
        NavigationArgsStore.removeArgs(RadarScreen.ROUTE)
    }
}