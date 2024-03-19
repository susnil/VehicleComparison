package pl.mobilespot.vehiclecomparison.presentation.starship

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import pl.mobilespot.vehiclecomparison.R
import pl.mobilespot.vehiclecomparison.presentation.desigsystem.icon.LocalIcon
import pl.mobilespot.vehiclecomparison.presentation.desigsystem.icon.MSIcon

@Composable
fun StarshipAttribute(text: String, image: ImageVector, modifier: Modifier = Modifier) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            imageVector = image,
            contentDescription = null,
            modifier = Modifier.size(LocalIcon.current.normal),
            tint = colorResource(id = R.color.body)
        )
        Text(text, overflow = TextOverflow.Ellipsis, textAlign = TextAlign.Center)
    }
}

@Preview
@Composable
fun StarshipAttributePreview() {
    StarshipAttribute(text = "Hashtag", image = MSIcon.Tag)
}