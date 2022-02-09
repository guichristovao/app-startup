package com.guichristovao.appstartup.theme.ui.modifier

import androidx.compose.foundation.layout.size
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.placeholder
import com.google.accompanist.placeholder.material.shimmer

fun Modifier.defaultPlaceholder(
    visible: Boolean,
    width: Dp? = null,
    height: Dp? = null
): Modifier = composed {
    if (visible && width != null && height != null) {
        Modifier.size(width, height)
    } else {
        Modifier
    }.placeholder(
        visible = visible,
        color = Color.LightGray,
        highlight = PlaceholderHighlight.shimmer()
    )
}
