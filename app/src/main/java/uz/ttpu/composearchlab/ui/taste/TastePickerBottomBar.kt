package uz.ttpu.composearchlab.ui.taste

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun TastePickerBottomBar(
    likedCount: Int,
    required: Int,
    canContinue: Boolean,
    onContinueClick: () -> Unit,
    onSkipClick: () -> Unit
) {
    val progress = (likedCount.toFloat() / required).coerceAtMost(1f)

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .navigationBarsPadding()
            .padding(8.dp),
        shape = RoundedCornerShape(24.dp),
        tonalElevation = 3.dp
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(progress = { progress })
                    Text("$likedCount")
                }
                Text(
                    text = "Pick $required artists you like",
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                horizontalArrangement = Arrangement.End
            ) {
                TextButton(onClick = onSkipClick) {
                    Text("Later")
                }
                Button(onClick = onContinueClick, enabled = canContinue) {
                    Text("Continue")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BottomBarZeroPreview() {
    TastePickerBottomBar(
        likedCount = 0,
        required = REQUIRED_LIKES,
        canContinue = false,
        onContinueClick = {},
        onSkipClick = {}
    )
}

@Preview(showBackground = true)
@Composable
fun BottomBarTwoPreview() {
    TastePickerBottomBar(
        likedCount = 2,
        required = REQUIRED_LIKES,
        canContinue = false,
        onContinueClick = {},
        onSkipClick = {}
    )
}

@Preview(showBackground = true)
@Composable
fun BottomBarThreePreview() {
    TastePickerBottomBar(
        likedCount = 3,
        required = REQUIRED_LIKES,
        canContinue = true,
        onContinueClick = {},
        onSkipClick = {}
    )
}
