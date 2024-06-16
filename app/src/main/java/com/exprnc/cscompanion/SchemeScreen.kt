package com.exprnc.cscompanion

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.exprnc.cscompanion.models.Nade

@Composable
fun SchemeScreen(navController: NavController, schemeViewModel: SchemeViewModel = viewModel()) {
    val scale = remember { mutableStateOf(1f) }
    val offset = remember { mutableStateOf(Offset.Zero) }

    val nades = schemeViewModel.nades

    Box(modifier = Modifier.fillMaxSize()) {
        Scheme(
            scale = scale.value,
            offset = offset.value,
            onScaleChange = { scale.value *= it },
            onOffsetChange = { offset.value += it },
            nades
        )
    }
}

@Composable
fun Scheme(
    scale: Float,
    offset: Offset,
    onScaleChange: (Float) -> Unit,
    onOffsetChange: (Offset) -> Unit,
    nades: List<Nade>,
    modifier: Modifier = Modifier,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTransformGestures { _, pan, zoom, _ ->
                    onScaleChange(zoom)
                    onOffsetChange(pan)
                }
            }
    ) {
        Image(
            painter = painterResource(id = R.drawable.dust2_radar),
            contentDescription = "Custom Scheme",
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer {
                    scaleX = scale
                    scaleY = scale
                    translationX = offset.x
                    translationY = offset.y
                }
        )
        SchemeNades(scale, offset, nades)
    }
}

@Composable
fun SchemeNades(scale: Float, offset: Offset, nades: List<Nade>) {
    val imageBitmap = ImageBitmap.imageResource(R.drawable.ic_smoke)

    Box(
        modifier = Modifier
            .drawWithContent {
                drawContent()
                drawNades(scale, offset, nades, imageBitmap)
            }
    )
}

private fun DrawScope.drawNades(
    scale: Float,
    offset: Offset,
    nades: List<Nade>,
    imageBitmap: ImageBitmap,
) {
    nades.forEach { nade ->
        val markerX = nade.offsetX * scale + offset.x
        val markerY = nade.offsetY * scale + offset.y

        val imageCenter = Offset(markerX, markerY)

        drawImage(
            image = imageBitmap,
            topLeft = Offset(
                imageCenter.x - imageBitmap.width / 2,
                imageCenter.y - imageBitmap.height / 2
            ),
            colorFilter = ColorFilter.tint(Color.White)
        )
    }
}