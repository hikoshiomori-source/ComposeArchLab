package uz.ttpu.composearchlab

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uz.ttpu.composearchlab.ui.taste.TastePickerRoute
import uz.ttpu.composearchlab.ui.theme.ComposeArchLabTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeArchLabTheme {
                // NameScreen()   // task 2 and 3
                // NewsScreen()   // task 4
                // SignInRoute()  // task 5 - 9
                TastePickerRoute()
            }
        }
    }
}

@Composable
fun NameField(
    name: String,
    onNameChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = name,
        onValueChange = onNameChange,
        label = { Text("Name") },
        modifier = modifier
    )
}

@Composable
fun NameScreen(modifier: Modifier = Modifier) {
    // rememberSaveable puts the text in a Bundle, so after rotation it is still here
    var name by rememberSaveable { mutableStateOf("") }

    Column(modifier.padding(24.dp)) {
        NameField(name = name, onNameChange = { name = it })
        Text("Hello, $name!")
    }
}

@Preview(showBackground = true)
@Composable
fun NameFieldPreview() {
    NameField(name = "Amin", onNameChange = {})
}

data class News(val title: String, val subtitle: String, val views: Int)

@Composable
fun Header(news: News) {
    SideEffect { Log.d("Recompose", "Header(news)") }
    Column {
        Text(news.title)
        Text(news.subtitle)
    }
}

// this one is better: it takes only the two strings it shows, so when only
// views changes Compose skips it (in Logcat it is printed 1 time, the first one 6 times)
@Composable
fun Header(title: String, subtitle: String) {
    SideEffect { Log.d("Recompose", "Header(title, subtitle)") }
    Column {
        Text(title)
        Text(subtitle)
    }
}

// if there are a lot of parameters and they always change together (for example all
// the fields of one form), then it is better to put them in one class
@Composable
fun NewsScreen(modifier: Modifier = Modifier) {
    var news by remember {
        mutableStateOf(News("Compose is declarative", "State in, UI out", views = 0))
    }
    Column(modifier.padding(24.dp)) {
        Header(news)
        Header(news.title, news.subtitle)
        Text("Views: ${news.views}")
        Button(onClick = { news = news.copy(views = news.views + 1) }) {
            Text("Add view")
        }
    }
}
