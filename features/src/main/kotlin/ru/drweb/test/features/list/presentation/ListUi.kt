package ru.drweb.test.features.list.presentation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.drweb.test.core.theme.AppTheme
import ru.drweb.test.core.theme.custom.CustomTheme
import ru.drweb.test.core.common_domain.AppModel
import ru.drweb.test.core.common_domain.PackageName
import ru.drweb.test.core.utils.toBitmap
import ru.drweb.test.features.R

@Composable
fun ListUi(
    component: ListComponent,
    modifier: Modifier = Modifier
) {
    val appsState by component.appsState.collectAsState()

    Surface(
        modifier = modifier
            .fillMaxSize()
            .systemBarsPadding(),
        color = CustomTheme.colors.background.screen
    ) {
        AppsListContent(
            apps = appsState,
            onAppClick = component::onAppClick
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun AppsListContent(
    apps: List<AppModel>,
    onAppClick: (PackageName) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(vertical = 12.dp)
    ) {
        stickyHeader {
            ListHeader(
                modifier = Modifier.fillMaxWidth()
            )
        }
        items(
            items = apps,
            key = { it.packageName.value }
        ) { app ->
            AppItem(
                appModel = app,
                onClick = { onAppClick(app.packageName) }
            )

            if (app !== apps.lastOrNull()) {
                HorizontalDivider()
            }
        }
    }
}

@Composable
fun ListHeader(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .background(CustomTheme.colors.background.screen)
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        Text(
            text = stringResource(R.string.list_title),
            style = CustomTheme.typography.title.regular
        )
    }
}

@Composable
private fun AppItem(
    appModel: AppModel,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 12.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        appModel.image?.let { iconDrawable ->
            Image(
                painter = BitmapPainter(iconDrawable.toBitmap().asImageBitmap()),
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
            )
            Spacer(Modifier.width(12.dp))
        }
        Text(
            text = appModel.name
        )
    }
}

@Preview(showSystemUi = true)
@Composable
private fun Preview() {
    AppTheme {
        ListUi(FakeListComponent())
    }
}
