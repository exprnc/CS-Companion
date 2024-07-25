package com.exprnc.cscompanion.presentation.features.detailgrenade

import android.net.Uri
import androidx.annotation.OptIn
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.media3.common.MediaItem
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.AspectRatioFrameLayout
import androidx.media3.ui.PlayerView
import androidx.navigation.NavHostController
import com.exprnc.cscompanion.architecture.Intent
import com.exprnc.cscompanion.architecture.LoadingState
import com.exprnc.cscompanion.presentation.navigation.ObserveEvents
import com.exprnc.cscompanion.presentation.ui.components.loading.LoadingPageBlocker

@Composable
fun ThrowingGrenadeView(
    navHostController: NavHostController,
    viewModel: ThrowingGrenadeViewModel = hiltViewModel(),
) {
    val state = viewModel.viewState.collectAsState().value
    val loadingState = viewModel.loadingState.collectAsState().value

    ObserveEvents(navHostController = navHostController, viewModel = viewModel)

    when(state) {
        is ThrowingGrenadeViewState.DefaultState -> DefaultStateView(state, viewModel::obtainIntent)
        is ThrowingGrenadeViewState.Error -> ErrorStateView()
    }

    when(loadingState) {
        is LoadingState.Enabled -> LoadingPageBlocker()
        else -> Unit
    }
}

@OptIn(UnstableApi::class)
@Composable
private fun DefaultStateView(
    state: ThrowingGrenadeViewState.DefaultState,
    onIntent: (Intent) -> Unit,
) {
    val context = LocalContext.current
    var exoPlayer by remember { mutableStateOf<ExoPlayer?>(null) }

    DisposableEffect(key1 = Unit) {
        exoPlayer = ExoPlayer.Builder(context).build().apply {
            val mediaItem = MediaItem.fromUri(state.position.videoURL)
            setMediaItem(mediaItem)
            prepare()
            playWhenReady = true
            repeatMode = ExoPlayer.REPEAT_MODE_ONE
        }
        onDispose {
            exoPlayer?.release()
            exoPlayer = null
        }
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        exoPlayer?.let {
            AndroidView(factory = {
                PlayerView(context).apply {
                    this.player = exoPlayer
                    this.resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FIT
                }
            })
        }
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