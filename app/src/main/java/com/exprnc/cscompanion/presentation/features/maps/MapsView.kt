package com.exprnc.cscompanion.presentation.features.maps

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.exprnc.cscompanion.architecture.Intent
import com.exprnc.cscompanion.architecture.LoadingState
import com.exprnc.cscompanion.domain.model.Map
import com.exprnc.cscompanion.presentation.navigation.ObserveEvents
import kotlinx.coroutines.launch

@Preview
@Composable
private fun Preview1() {
    MaterialTheme {
        SuccessStateView(
            state = MapsViewState.Success(
                listOf(
                    Map(
                        mapId = "de_dust2",
                        name = "Dust II",
                        type = "competitive",
                        activePool = true,
                        icon = "ic_de_dust2",
                        image = "img_de_dust2",
                        radarImage = "rdr_de_dust2",
                        radarImageWithCallouts = ""
                    ),
                    Map(
                        mapId = "de_dust2",
                        name = "Dust II",
                        type = "competitive",
                        activePool = true,
                        icon = "ic_de_dust2",
                        image = "img_de_dust2",
                        radarImage = "rdr_de_dust2",
                        radarImageWithCallouts = ""
                    )
                ),
                listOf()
            ),
            {}
        )
    }
}

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
        is LoadingState.Enabled -> {
            //todo FullScreen
        }

        else -> Unit
    }
}

@Composable
private fun ErrorStateView() {
    Text(text = "Error", modifier = Modifier.fillMaxSize())
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun SuccessStateView(state: MapsViewState.Success, onIntent: (Intent) -> Unit) {
    val pagerState = rememberPagerState(pageCount = { MapsTabs.entries.size })
    Column {
        MapsTabsBlock(pagerState)
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxWidth()
        ) { page ->
            when(page) {
                0 -> {
                    Column {
                        state.competitiveMaps.forEach { map ->
                            CompetitiveMapsPagerBlock(map, onIntent)
                        }
                    }
                }
                1 -> {
                    Column {
                        state.wingmanMaps.forEach { map ->
                            CompetitiveMapsPagerBlock(map, onIntent)
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun CompetitiveMapsPagerBlock(map: Map, onIntent: (Intent) -> Unit) {
    val context = LocalContext.current
    val iconResId = context.resources.getIdentifier(
        map.icon,
        "drawable",
        context.packageName
    )
    val imageResId = context.resources.getIdentifier(
        map.image,
        "drawable",
        context.packageName
    )
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(160.dp)
            .padding(8.dp, 8.dp, 8.dp, 0.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        onClick = { onIntent.invoke(MapsViewIntent.OnMapClicked(map.mapId)) }
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            Image(
                painter = painterResource(id = imageResId),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
            )
            Text(
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(16.dp),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                text = map.name,
                color = MaterialTheme.colorScheme.background
            )
            Image(
                painter = painterResource(id = iconResId),
                contentDescription = null,
                modifier = Modifier
                    .size(56.dp)
                    .align(Alignment.TopEnd)
                    .padding(8.dp)
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
                    onClick = {
                        scope.launch {
                            pagerState.animateScrollToPage(currentTab.ordinal)
                        }
                    },
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