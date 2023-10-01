package com.hazem.boruto.presentation.screens.details

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.hazem.boruto.utils.Constants.BASE_URL
import com.hazem.boruto.utils.PaletteGenerator.convertImageUrlToBitmap
import com.hazem.boruto.utils.PaletteGenerator.extractColorFromBitmap
import kotlinx.coroutines.flow.collectLatest

@Composable
fun DetailsScreen(
    navController: NavHostController,
    detailsViewModel: DetailsViewModel = hiltViewModel()
) {
    val selectedHero = detailsViewModel.selectedHero.collectAsState()
    val colorPalette by detailsViewModel.colorPalette
    if(colorPalette.isNotEmpty()){
        DetailsContent(
            navController = navController,
            selectedHero = selectedHero.value,
            colors = colorPalette
        )
    }
    else{
        detailsViewModel.generateColorPalette()
    }

    val context= LocalContext.current

    LaunchedEffect(key1 = true){
        detailsViewModel.uiEvent.collectLatest {event->
            when(event){
                UiEvent.GeneratorColorPalette -> {
                    val bitmap= convertImageUrlToBitmap(
                        imageUrl = "$BASE_URL${selectedHero.value?.image}",
                        context=context
                    )
                    if(bitmap!=null){
                        detailsViewModel.setColorPalette(
                            colors=extractColorFromBitmap(
                                bitmap=bitmap
                            )
                        )
                    }
                }

            }

        }

    }

}