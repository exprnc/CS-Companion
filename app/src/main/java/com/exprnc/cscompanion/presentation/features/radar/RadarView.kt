package com.exprnc.cscompanion.presentation.features.radar

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.exprnc.cscompanion.R
import com.exprnc.cscompanion.architecture.Intent
import com.exprnc.cscompanion.architecture.LoadingState
import com.exprnc.cscompanion.domain.model.Grenade
import com.exprnc.cscompanion.domain.model.Position
import com.exprnc.cscompanion.presentation.navigation.ObserveEvents
import com.exprnc.cscompanion.presentation.ui.components.loading.LoadingPageBlocker

@Composable
fun RadarView(
    navHostController: NavHostController,
    viewModel: RadarViewModel = hiltViewModel(),
) {
    val state = viewModel.viewState.collectAsState().value
    val loadingState = viewModel.loadingState.collectAsState().value

    ObserveEvents(navHostController = navHostController, viewModel = viewModel)

    when (state) {
        is RadarViewState.DefaultState -> DefaultStateView(state, viewModel::obtainIntent)
        is RadarViewState.GrenadeSelectedState -> GrenadeSelectedStateView(state, viewModel::obtainIntent)
        is RadarViewState.Error -> ErrorStateView()
    }

    when (loadingState) {
        is LoadingState.Enabled -> LoadingPageBlocker()
        else -> Unit
    }
}

@Composable
private fun ErrorStateView() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Error", fontSize = 32.sp, fontWeight = FontWeight.Bold)
    }
}

@Composable
private fun DefaultStateView(
    state: RadarViewState.DefaultState,
    onIntent: (Intent) -> Unit,
) {
    var offset by remember { mutableStateOf(Offset.Zero) }
    var zoom by remember { mutableFloatStateOf(1f) }

    val context = LocalContext.current
    val radarImageResId = context.resources.getIdentifier(state.map.radarImage, "drawable", context.packageName)

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .pointerInput(Unit) {
                detectTransformGestures { centroid, pan, gestureZoom, _ ->
                    val oldScale = zoom
                    val newScale = zoom * gestureZoom
                    offset = (offset + centroid / oldScale) - (centroid / newScale + pan / oldScale)
                    zoom = newScale
                }
            }
            .graphicsLayer {
                translationX = -offset.x * zoom
                translationY = -offset.y * zoom
                scaleX = zoom
                scaleY = zoom
                transformOrigin = TransformOrigin(0f, 0f)
            }
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = radarImageResId),
            contentDescription = null,
            modifier = Modifier.size(400.dp)
        )
        GrenadesScheme(grenades = state.grenades, zoom, onIntent)
    }
}

@Composable
private fun GrenadesScheme(
    grenades: List<Grenade>,
    zoom: Float,
    onIntent: (Intent) -> Unit,
) {
    Box(
        modifier = Modifier
            .size(400.dp)
            .border(1.dp, Color.Blue)
    ) {
        grenades.forEach { grenade ->
            Box(
                modifier = Modifier
                    .offset(
                        x = with(LocalDensity.current) { grenade.offsetX.toDp() },
                        y = with(LocalDensity.current) { grenade.offsetY.toDp() }
                    )
                    .graphicsLayer {
                        scaleX = 1 / zoom
                        scaleY = 1 / zoom
                    }
                    .size(20.dp)
                    .clickable { onIntent(RadarViewIntent.OnGrenadeClicked(grenade)) }
            ) {
                Image(
                    painter = painterResource(id = grenade.type.resId),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}

@Composable
private fun GrenadeSelectedStateView(
    state: RadarViewState.GrenadeSelectedState,
    onIntent: (Intent) -> Unit,
) {
    var offset by remember { mutableStateOf(Offset.Zero) }
    var zoom by remember { mutableFloatStateOf(1f) }

    val context = LocalContext.current
    val radarImageResId =
        context.resources.getIdentifier(state.map.radarImage, "drawable", context.packageName)

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .pointerInput(Unit) {
                detectTransformGestures { centroid, pan, gestureZoom, _ ->
                    val oldScale = zoom
                    val newScale = zoom * gestureZoom
                    offset = (offset + centroid / oldScale) - (centroid / newScale + pan / oldScale)
                    zoom = newScale
                }
            }
            .graphicsLayer {
                translationX = -offset.x * zoom
                translationY = -offset.y * zoom
                scaleX = zoom
                scaleY = zoom
                transformOrigin = TransformOrigin(0f, 0f)
            }
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = radarImageResId),
            contentDescription = null,
            modifier = Modifier.size(400.dp)
        )
        PositionsScheme(state.positions, state.grenade, zoom, onIntent)
    }
}

@Composable
private fun PositionsScheme(
    positions: List<Position>,
    grenade: Grenade,
    zoom: Float,
    onIntent: (Intent) -> Unit,
) {
    Box(
        modifier = Modifier
            .size(400.dp)
            .border(1.dp, Color.Blue)
    ) {
        Box(
            modifier = Modifier
                .offset(
                    x = with(LocalDensity.current) { grenade.offsetX.toDp() },
                    y = with(LocalDensity.current) { grenade.offsetY.toDp() }
                )
                .graphicsLayer {
                    scaleX = 1 / zoom
                    scaleY = 1 / zoom
                }
                .size(20.dp)
                .clickable { onIntent(RadarViewIntent.OnGrenadeClicked(grenade)) }
        ) {
            Image(
                painter = painterResource(id = grenade.type.resId),
                contentDescription = null,
                modifier = Modifier.fillMaxSize()
            )
        }
        positions.forEach { position ->
            Box(
                modifier = Modifier
                    .offset(
                        x = with(LocalDensity.current) { position.offsetX.toDp() },
                        y = with(LocalDensity.current) { position.offsetY.toDp() }
                    )
                    .graphicsLayer {
                        scaleX = 1 / zoom
                        scaleY = 1 / zoom
                    }
                    .size(20.dp)
                    .clickable { onIntent(RadarViewIntent.OnPositionClicked(position)) }
            ) {
                Canvas(Modifier.fillMaxSize()) { drawCircle(color = Color.Red) }
            }
        }
    }
}