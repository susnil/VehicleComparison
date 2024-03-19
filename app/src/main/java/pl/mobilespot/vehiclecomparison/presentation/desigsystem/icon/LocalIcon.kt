package pl.mobilespot.vehiclecomparison.presentation.desigsystem.icon

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

val LocalIcon = compositionLocalOf { Icon() }
data class Icon(
    val small: Dp = 12.dp,
    val normal: Dp = 20.dp
)
val MaterialTheme.icon: Icon
    @Composable
    @ReadOnlyComposable
    get() = LocalIcon.current