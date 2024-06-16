package com.exprnc.cscompanion

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.exprnc.cscompanion.models.Map

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MapsScreen(navController: NavController, mapsViewModel: MapsViewModel = viewModel()) {
    val activeMaps = mapsViewModel.activeMaps
    val inactiveMaps = mapsViewModel.inactiveMaps

    val pagerState = rememberPagerState(pageCount = { 2 })

    Box(modifier = Modifier.fillMaxSize()) {
        HorizontalPager(state = pagerState, modifier = Modifier.fillMaxSize()) { page ->
            when (page) {
                0 -> Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(0.dp, 50.dp, 0.dp, 0.dp),
                ) {
                    ViewPagerItem(maps = activeMaps, navController)
                }
                1 -> Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(0.dp, 50.dp, 0.dp, 0.dp),
                ) {
                    ViewPagerItem(maps = inactiveMaps, navController)
                }
            }
        }
    }
    Row(
        Modifier
            .height(50.dp)
            .fillMaxWidth()
            .padding(10.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        repeat(pagerState.pageCount) { iteration ->
            val border = if (pagerState.currentPage == iteration) BorderStroke(
                2.dp,
                Color.Blue,
            ) else BorderStroke(2.dp, Color.White)
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(180.dp)
                    .background(Color.Gray)
                    .border(border),
            ) {
                val pageTitle = if (iteration == 0) "ACTIVE MAPS" else "OTHER MAPS"
                Text(
                    text = pageTitle,
                    color = Color.White,
                    modifier = Modifier.align(Alignment.Center),
                )
            }
        }
    }
}

@OptIn(ExperimentalCoilApi::class)
@Composable
fun ListItem(map: Map, navController: NavController) {
    Card(
        Modifier
            .fillMaxWidth()
            .height(135.dp)
            .padding(10.dp, 5.dp, 10.dp, 0.dp)
            .clickable { navController.navigate("schemeScreen") }
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            val imageUrl = map.image
            val painter = rememberImagePainter(data = imageUrl)

            Image(
                painter = painter,
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Text(
                text = map.name,
                color = Color.White,
                fontSize = 24.sp
            )
        }
    }
}

@Composable
fun ViewPagerItem(maps: List<Map>, navController: NavController) {
    LazyColumn {
        items(items = maps, itemContent = { map ->
            ListItem(map, navController)
        })
    }
}