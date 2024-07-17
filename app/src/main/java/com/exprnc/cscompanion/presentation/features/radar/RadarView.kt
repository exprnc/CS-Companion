package com.exprnc.cscompanion.presentation.features.radar

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.exprnc.cscompanion.architecture.LoadingState
import com.exprnc.cscompanion.domain.model.Grenade
import com.exprnc.cscompanion.presentation.navigation.ObserveEvents
import com.exprnc.cscompanion.presentation.ui.components.loading.LoadingPageBlocker
import de.mr_pine.zoomables.ZoomableImage
import de.mr_pine.zoomables.rememberZoomableState

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
private fun SuccessStateView(
    state: RadarViewState.Success,
) {
    val minScale = 1f
    val maxScale = 5f
    var size by remember { mutableStateOf(IntSize.Zero) }
    val zoomableState = rememberZoomableState(
        onTransformation = { zoomChange, panChange, rotationChange ->
            scale.value = maxOf(minScale, minOf(scale.value * zoomChange, maxScale))
            val maxX = (size.width * (scale.value - 1)) / 2
            val maxY = (size.height * (scale.value - 1)) / 2
            val minX = -maxX
            val minY = -maxY
            val offsetX = maxOf(minX, minOf(maxX, offset.value.x + panChange.x))
            val offsetY = maxOf(minY, minOf(maxY, offset.value.y + panChange.y))
            offset.value = Offset(offsetX, offsetY)
            rotation.value = (rotation.value + rotationChange + 360 + 180) % 360 - 180
        }
    )
    val context = LocalContext.current
    val radarImageResId = context.resources.getIdentifier(state.map.radarImage, "drawable", context.packageName)
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize().onSizeChanged { size = it }
    ) {
        ZoomableImage(
            coroutineScope = rememberCoroutineScope(),
            zoomableState = zoomableState,
            painter = painterResource(id = radarImageResId),
            modifier = Modifier
                .fillMaxSize(),
        )
        GrenadesScheme(zoomableState.scale.value, zoomableState.offset.value, state.grenades)
    }
}

@Composable
private fun GrenadesScheme(scale: Float, offset: Offset, grenades: List<Grenade>) {
    val res = LocalContext.current.resources
    Box(modifier = Modifier
        .drawWithContent {
            drawContent()
            grenades.forEach { grenade ->
                val grenadeImageBitmap = ImageBitmap.imageResource(res, id = grenade.type.resId)
                val markerX = grenade.offsetX * scale + offset.x
                val markerY = grenade.offsetY * scale + offset.y
                val imageCenter = Offset(markerX, markerY)

                drawImage(
                    image = grenadeImageBitmap,
                    topLeft = Offset(
                        imageCenter.x - grenadeImageBitmap.width / 2,
                        imageCenter.y - grenadeImageBitmap.height / 2,
                    )
                )
            }
        }
        .border(3.dp, Color.Green))
}
