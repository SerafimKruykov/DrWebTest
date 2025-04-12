package ru.drweb.test.features.home.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.arkivanov.decompose.extensions.compose.stack.Children
import ru.drweb.test.core.theme.AppTheme
import ru.drweb.test.features.details.presentation.AppDetailsUi
import ru.drweb.test.features.list.presentation.ListUi

@Composable
fun HomeUi(
    component: HomeComponent,
    modifier: Modifier = Modifier
) {
    val childStack by component.childStack.collectAsState()

    Children(childStack, modifier) { child ->
        when (val instance = child.instance) {
            is HomeComponent.Child.List -> ListUi(instance.component)
            is HomeComponent.Child.Details -> AppDetailsUi(instance.component)
        }
    }
}

@Preview
@Composable
private fun Preview() {
    AppTheme {
        HomeUi(FakeHomeComponent())
    }
}