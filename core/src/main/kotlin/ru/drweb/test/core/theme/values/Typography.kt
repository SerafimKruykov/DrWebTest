package ru.drweb.test.core.theme.values

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import ru.drweb.test.core.theme.custom.BodyTypography
import ru.drweb.test.core.theme.custom.ButtonTypography
import ru.drweb.test.core.theme.custom.CaptionTypography
import ru.drweb.test.core.theme.custom.CustomTypography
import ru.drweb.test.core.theme.custom.TitleTypography

val AppTypography = CustomTypography(
    title = TitleTypography(
        regular = TextStyle(
            fontSize = 24.sp
        )
    ),
    body = BodyTypography(
        regular = TextStyle(
            fontSize = 16.sp
        )
    ),
    caption = CaptionTypography(
        regular = TextStyle(
            fontSize = 12.sp
        )
    ),
    button = ButtonTypography(
        bold = TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
    )
)