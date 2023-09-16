package com.hazem.boruto.presentation.screens.home.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.graphics.vector.PathParser
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hazem.boruto.R
import com.hazem.boruto.presentation.ui.theme.StarColor

@Composable
fun RatingWidget(
    modifier: Modifier,
    rating: Double
) {
    val starPathString = stringResource(id = R.string.star_path)
    val starPath = remember {
        PathParser().parsePathString(starPathString).toPath()
    }
    val starPathBounds = remember {
        starPath.getBounds()
    }
    FillStar(starPath = starPath, starPathBound = starPathBounds)
}

@Composable
fun FillStar(
    starPath: Path,
    starPathBound: Rect,
    scaleFactor: Float = 2f
) {
    Canvas(modifier = Modifier.size(24.dp)) {

        scale(scale=scaleFactor){val canvasSize = this.size
        val pathWidth = starPathBound.width
        val pathHeight = starPathBound.height
        val width = (canvasSize.width / 2f) - (pathWidth / 1.7f)
        val height = (canvasSize.height / 2f) - (pathHeight / 1.7f)
        translate(left = width, top = height) {
            drawPath(
                path = starPath,
                color = StarColor
            )
        }
    }
    }
}

@Composable
@Preview(showBackground = true)
fun FillStarPreview() {
    RatingWidget(modifier = Modifier, rating = 2.0)
}