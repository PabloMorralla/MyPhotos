package com.example.myphotos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.myphotos.ui.theme.MyPhotosTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyPhotosTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainScreen(Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    var selectedPicture by remember { mutableStateOf("https://cdn.pixabay.com/photo/2025/07/16/15/01/karlsbad-9718003_960_720.jpg") }

    val images = listOf(
        "https://cdn.pixabay.com/photo/2025/07/16/15/01/karlsbad-9718003_960_720.jpg",
        "https://cdn.pixabay.com/photo/2024/12/17/14/27/swans-9273244_960_720.jpg",
        "https://cdn.pixabay.com/photo/2025/07/31/16/09/animal-9747276_960_720.jpg",
        "https://cdn.pixabay.com/photo/2025/09/12/15/10/small-copper-9830647_960_720.jpg",
        "https://cdn.pixabay.com/photo/2025/09/14/19/45/squirrel-9834881_960_720.jpg"
    )

    Column (
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top
    ) {
        LazyRow(
            modifier = modifier.fillMaxSize(),
        ) {
            items(images.size) { image ->
                AsyncImage(
                    model = ImageRequest.Builder(context)
                        .data(images[image])
                        .build(),
                    contentDescription = "Imagen",
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier.clickable(
                        onClick = {
                            selectedPicture = images[image]
                        }
                    )
                )
            }
        }

        AsyncImage(
            model = ImageRequest.Builder(context)
                .data(selectedPicture)
                .build(),
            contentDescription = "Imagen",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyPhotosTheme {
        Greeting("Android")
    }
}