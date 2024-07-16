package com.exprnc.cscompanion.presentation.features.radar

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.exprnc.cscompanion.R
import com.exprnc.cscompanion.architecture.LoadingState
import com.exprnc.cscompanion.domain.model.Grenade
import com.exprnc.cscompanion.domain.model.Map
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
        is RadarViewState.Success -> {
            SuccessStateView(state)
        }

        is RadarViewState.Error -> {
            ErrorStateView()
        }
    }

    when (loadingState) {
        is LoadingState.Enabled -> LoadingPageBlocker()
        else -> Unit
    }
}

@Composable
fun ErrorStateView() {

}

@Composable
private fun SuccessStateView(state: RadarViewState.Success) {
    val scale = remember { mutableFloatStateOf(1f) }
    val offset = remember { mutableStateOf(Offset.Zero) }

    Box(modifier = Modifier.fillMaxSize()) {
        RadarScheme(
            scale = scale.floatValue,
            offset = offset.value,
            onScaleChange = { scale.floatValue *= it },
            onOffsetChange = { offset.value += it },
            map = state.map,
            grenades = state.grenades
        )
    }
}

@Composable
private fun RadarScheme(
    scale: Float,
    offset: Offset,
    onScaleChange: (Float) -> Unit,
    onOffsetChange: (Offset) -> Unit,
    map: Map,
    grenades: List<Grenade>,
) {
    val context = LocalContext.current
    val radarImageResId =
        context.resources.getIdentifier(map.radarImage, "drawable", context.packageName)
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTransformGestures { centroid, pan, zoom, rotation ->
                    onScaleChange(zoom)
                    onOffsetChange(pan)
                }
            }
    ) {
        Image(
            painter = painterResource(id = radarImageResId),
            contentDescription = null,
            modifier = Modifier
                .size(200.dp)
                .graphicsLayer {
                    scaleX = scale
                    scaleY = scale
                    translationX = offset.x
                    translationY = offset.y
                }
        )
        GrenadesScheme(scale, offset, grenades)
    }
}

@Composable
private fun GrenadesScheme(scale: Float, offset: Offset, grenades: List<Grenade>) {
    val imageBitmap = ImageBitmap.imageResource(id = R.drawable.smoke)

    Box(
        modifier = Modifier
            .drawWithContent {
                drawContent()
                drawGrenades(scale, offset, grenades, imageBitmap)
            }
    )
}

private fun DrawScope.drawGrenades(
    scale: Float,
    offset: Offset,
    grenades: List<Grenade>,
    imageBitmap: ImageBitmap,
) {
    grenades.forEach { grenade ->
        val markerX = grenade.offsetX * scale + offset.x
        val markerY = grenade.offsetY * scale + offset.y
        val imageCenter = Offset(markerX, markerY)

        drawImage(
            image = imageBitmap,
            topLeft = Offset(
                imageCenter.x - imageBitmap.width / 2,
                imageCenter.y - imageBitmap.height / 2,
            )
        )
    }
}