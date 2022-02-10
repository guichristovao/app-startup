package com.guichristovao.appstartup.theme.ui.component

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.guichristovao.appstartup.theme.ui.modifier.defaultPlaceholder
import com.guichristovao.appstartup.theme.ui.theme.AppStartupTheme

@Immutable
data class User(
    val avatarUrl: String? = "",
    val name: String = "",
    val login: String = ""
)

@Composable
fun ProfileCard(
    user: User?,
    modifier: Modifier = Modifier,
    onClick: (String) -> Unit = {}
) {
    val hasPlaceholder = user == null

    Card(
        modifier = modifier
            .wrapContentSize()
            .padding(bottom = 16.dp)
            .clickable(
                enabled = !hasPlaceholder,
                onClick = { onClick(user?.login ?: "") }
            )
    ) {
        Column(
            modifier = Modifier
                .width(180.dp)
                .padding(16.dp)
        ) {
            ProfileImage(
                avatarUrl = user?.avatarUrl,
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .size(120.dp)
                    .defaultPlaceholder(hasPlaceholder, shape = CircleShape)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = user?.name ?: "",
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.h6,
                color = MaterialTheme.colors.onSurface,
                modifier = Modifier
                    .defaultPlaceholder(hasPlaceholder, 180.dp, 50.dp)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = user?.login ?: "",
                style = MaterialTheme.typography.subtitle1,
                color = MaterialTheme.colors.onSurface,
                modifier = Modifier
                    .defaultPlaceholder(hasPlaceholder, 100.dp, 20.dp)
            )
        }
    }
}

@OptIn(ExperimentalCoilApi::class)
@Composable
fun ProfileImage(
    avatarUrl: String?,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    elevation: Dp = 0.dp
) {
    Surface(
        color = Color.LightGray,
        border = BorderStroke(2.dp, Color(0xFF6a1b9a)),
        elevation = elevation,
        shape = CircleShape,
        modifier = modifier
    ) {
        Image(
            painter = rememberImagePainter(
                data = avatarUrl
            ),
            contentDescription = contentDescription,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
        )
    }
}

@Preview("default")
@Preview("dark theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview("large font", fontScale = 2f)
@Composable
fun UserCardPreview() {
    AppStartupTheme {
        ProfileCard(
            user = User(
                avatarUrl = "https://avatars.githubusercontent.com/u/35379633?v=4",
                name = "Guilherme de Sá Christovão",
                login = "guichristovao"
            )
        )
    }
}
