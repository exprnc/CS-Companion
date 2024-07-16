package com.exprnc.cscompanion.presentation.features.maps

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.exprnc.cscompanion.architecture.Intent
import com.exprnc.cscompanion.architecture.LoadingState
import com.exprnc.cscompanion.domain.model.Map
import com.exprnc.cscompanion.presentation.navigation.ObserveEvents
import com.exprnc.cscompanion.presentation.ui.components.loading.LoadingPageBlocker
import kotlinx.coroutines.launch

@Composable
fun MapsView(
    navHostController: NavHostController,
    viewModel: MapsViewModel = hiltViewModel(),
) {
    val state = viewModel.viewState.collectAsState().value
    val loadingState = viewModel.loadingState.collectAsState().value

    ObserveEvents(navHostController = navHostController, viewModel = viewModel)

    when (state) {
        is MapsViewState.Error -> ErrorStateView()
        is MapsViewState.Success -> SuccessStateView(state, viewModel::obtainIntent)
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

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun SuccessStateView(
    state: MapsViewState.Success,
    onIntent: (Intent) -> Unit
) {
    val pagerState = rememberPagerState(pageCount = { MapsTabs.entries.size })
    Column {
        MapsTabsBlock(pagerState)
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.Top
        ) { page ->
            when (page) {
                0 -> Column {
                    state.competitiveMaps.forEach { map -> MapItemBlock(map, onIntent) }
                }
                1 -> Column {
                    state.wingmanMaps.forEach { map -> MapItemBlock(map, onIntent) }
                }
            }
        }
    }
}

@Composable
private fun MapItemBlock(map: Map, onIntent: (Intent) -> Unit) {
    val context = LocalContext.current
    val iconResId = context.resources.getIdentifier(map.icon, "drawable", context.packageName)
    val imageResId = context.resources.getIdentifier(map.image, "drawable", context.packageName)
    Card(
        modifier = Modifier.fillMaxWidth().height(160.dp).padding(8.dp, 8.dp, 8.dp, 0.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        onClick = { onIntent.invoke(MapsViewIntent.OnMapClicked(map)) }
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            Image(
                painter = painterResource(id = imageResId),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(8.dp))
            )
            Text(
                modifier = Modifier.align(Alignment.Center).padding(16.dp),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                text = map.name,
                color = MaterialTheme.colorScheme.background
            )
            Image(
                painter = painterResource(id = iconResId),
                contentDescription = null,
                modifier = Modifier.size(56.dp).align(Alignment.TopEnd).padding(8.dp)
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun MapsTabsBlock(pagerState: PagerState) {
    val selectedTabIndex = remember {
        derivedStateOf { pagerState.currentPage }
    }
    val scope = rememberCoroutineScope()
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        TabRow(
            selectedTabIndex = selectedTabIndex.value,
            modifier = Modifier.fillMaxWidth()
        ) {
            MapsTabs.entries.forEachIndexed { index, currentTab ->
                Tab(
                    selected = selectedTabIndex.value == index,
                    onClick = { scope.launch { pagerState.animateScrollToPage(currentTab.ordinal) } },
                    text = { Text(text = currentTab.text) }
                )
            }
        }
    }
}

enum class MapsTabs(val text: String) {
    COMPETITIVE("Competitive"),
    WINGMAN("Wingman")
}