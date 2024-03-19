package pl.mobilespot.vehiclecomparison.presentation.starship

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import pl.mobilespot.vehiclecomparison.R
import pl.mobilespot.vehiclecomparison.presentation.desigsystem.icon.LocalIcon
import pl.mobilespot.vehiclecomparison.presentation.desigsystem.icon.MSIcon
import pl.mobilespot.vehiclecomparison.presentation.desigsystem.theme.padding

@Composable
fun StarshipAttribute(
    modifier: Modifier = Modifier,
    @StringRes placeholder: Int? = null,
    value: String,
    image: ImageVector
) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = modifier) {
        Icon(
            imageVector = image,
            contentDescription = null,
            modifier = Modifier.size(LocalIcon.current.normal),
            tint = colorResource(id = R.color.body)
        )
        placeholder?.let {
            Text(stringResource(id = placeholder), fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.size(MaterialTheme.padding.extraSmall))
        }
        Text(value, overflow = TextOverflow.Ellipsis, textAlign = TextAlign.Center)
    }
}

@Preview
@Composable
fun StarshipAttributePreview() {
    StarshipAttribute(value = "Hashtag", image = MSIcon.Tag)
}