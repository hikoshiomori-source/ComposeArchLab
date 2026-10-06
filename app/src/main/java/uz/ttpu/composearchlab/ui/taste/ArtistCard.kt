package uz.ttpu.composearchlab.ui.taste

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ArtistCard(
    artist: Artist,
    liked: Boolean,
    onLikeClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(88.dp)
                .background(
                    color = Color.hsv((artist.id * 47 % 360).toFloat(), 0.5f, 0.8f),
                    shape = CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = artist.name.first().toString(),
                style = MaterialTheme.typography.headlineMedium,
                color = Color.White
            )
        }
        Text(
            text = artist.name,
            maxLines = 2,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyMedium
        )
        IconButton(onClick = onLikeClick) {
            if (liked) {
                Icon(Icons.Default.Favorite, contentDescription = "Unlike ${artist.name}")
            } else {
                Icon(Icons.Default.FavoriteBorder, contentDescription = "Like ${artist.name}")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ArtistCardLikedPreview() {
    ArtistCard(artist = seedArtists[0], liked = true, onLikeClick = {})
}

@Preview(showBackground = true)
@Composable
fun ArtistCardNotLikedPreview() {
    ArtistCard(artist = seedArtists[0], liked = false, onLikeClick = {})
}
