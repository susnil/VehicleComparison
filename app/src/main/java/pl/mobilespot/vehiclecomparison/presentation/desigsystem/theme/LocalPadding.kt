package pl.mobilespot.vehiclecomparison.presentation.desigsystem.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

val LocalPadding = compositionLocalOf { Padding() }
data class Padding(
    val extraSmall: Dp = 6.dp,
    val small: Dp = 8.dp,
    val medium: Dp = 16.dp
)
val MaterialTheme.padding: Padding
    @Composable
    @ReadOnlyComposable
    get() = LocalPadding.current