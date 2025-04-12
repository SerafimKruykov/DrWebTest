package ru.drweb.test.features.details.presentation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import ru.drweb.test.core.common_domain.DetailedApp
import ru.drweb.test.core.theme.AppTheme
import ru.drweb.test.core.theme.custom.ButtonColors
import ru.drweb.test.core.theme.custom.CustomTheme
import ru.drweb.test.core.utils.dispatchOnBackPressed
import ru.drweb.test.features.R

@Composable
fun AppDetailsUi(
    component: AppDetailsComponent,
    modifier: Modifier = Modifier
) {
    val appState by component.detailedAppState.collectAsState()
    val context = LocalContext.current

    Surface(
        modifier = modifier
            .fillMaxSize()
            .systemBarsPadding(),
        color = CustomTheme.colors.background.screen
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            IconButton(
                onClick = { dispatchOnBackPressed(context) }
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = null
                )
            }

            appState?.let {
                AppDetailsContent(
                    app = it,
                    onOpenAppClick = component::onOpenAppClick
                )
            } ?: AppDetailsEmpty()
        }
    }
}

@Composable
private fun AppDetailsContent(
    app: DetailedApp,
    onOpenAppClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            textAlign = TextAlign.Center,
            text = app.name,
            style = CustomTheme.typography.title.regular
        )

        AsyncImage(
            contentDescription = null,
            model = ImageRequest.Builder(LocalContext.current)
                .data(app.image)
                .crossfade(true)
                .build(),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(top = 24.dp)
                .size(200.dp)
                .clip(CircleShape)
                .background(color = CustomTheme.colors.background.screen)
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(top = 24.dp)
                .fillMaxWidth()
        ) {
            Text(
                textAlign = TextAlign.Center,
                text = stringResource(R.string.details_package),
                style = CustomTheme.typography.body.regular,
            )
            Text(
                textAlign = TextAlign.Center,
                text = app.packageName.value,
                style = CustomTheme.typography.body.regular,
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth()
        ) {
            Text(
                textAlign = TextAlign.Center,
                text = stringResource(R.string.details_version),
                style = CustomTheme.typography.body.regular,
            )
            Text(
                textAlign = TextAlign.Center,
                text = app.versionName,
                style = CustomTheme.typography.body.regular,
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth()
        ) {
            Text(
                textAlign = TextAlign.Center,
                text = stringResource(R.string.details_check_sum),
                style = CustomTheme.typography.body.regular,
            )
            Text(
                textAlign = TextAlign.Center,
                text = app.checkSum.toString(),
                style = CustomTheme.typography.body.regular,
            )
        }

        Spacer(Modifier.weight(1f))

        Button(
            onClick = onOpenAppClick,
            colors = androidx.compose.material3.ButtonColors(
                containerColor = CustomTheme.colors.button.secondary,
                contentColor = CustomTheme.colors.text.primary,
                disabledContentColor = CustomTheme.colors.button.secondary,
                disabledContainerColor = CustomTheme.colors.text.primary
            ),
            border = BorderStroke(
                width = 1.dp,
                color = CustomTheme.colors.border.primary
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
        ) {
            Text(stringResource(R.string.details_open_app).uppercase())
        }
    }
}

@Composable
private fun AppDetailsEmpty() {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(
            text = stringResource(ru.drweb.test.core.R.string.error_unexpected),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .align(Alignment.Center)
                .padding(horizontal = 32.dp)
        )
    }
}

@Preview(showSystemUi = true)
@Composable
private fun Preview() {
    AppTheme {
        AppDetailsUi(FakeAppDetailsComponent())
    }
}