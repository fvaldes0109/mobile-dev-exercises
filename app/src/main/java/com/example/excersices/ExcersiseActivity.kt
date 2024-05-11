package com.example.excersices

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.excersices.ui.theme.ExcersicesTheme
import kotlinx.coroutines.Dispatchers
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.request.ImageRequest
import kotlinx.coroutines.withContext

class ExcersiseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AppContent()
        }
    }
}

@Composable
fun AppContent() {

    var photo by remember { mutableStateOf<Unsplash?>(null) }
    LaunchedEffect(key1 = Unit) {
        val photoFromApi = withContext(Dispatchers.IO) {
            getRandomPhoto() // Assuming this function performs the API call
        }
        photo = photoFromApi
    }

    Column {
        Box(modifier = Modifier.height(300.dp)) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(photo?.url)
                    .addHeader("Authorization", "Client-ID 2k7FU31RiDWg5u6mJ2SRgg-ljyb6Y_5RiOPmICzaisg")
                    .build(),
                contentDescription = "Photo",
                modifier = Modifier
                    .fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            if (photo?.location != "null") {
                Row(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(20.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.location),
                        contentDescription = null,
                        modifier = Modifier
                            .size(30.dp)
                            .padding(end = 8.dp)
                    )

                    Text(
                        text = photo?.location ?: "",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                    )
                }
            }
        }
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        ){
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    ProfilePicture(
                        painter = painterResource(id = R.drawable.pfp),
                        modifier = Modifier.size(40.dp),
                        contentDescription = "Profile Picture"
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = photo?.user ?: "",
                        color = Color.White,
//                            style = MaterialTheme.typography.body1,
                        fontWeight = FontWeight.Bold
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        painter = painterResource(id = R.drawable.download),
                    )
                    IconButton(
                        painter = painterResource(id = R.drawable.favorite),
                    )
                    IconButton(
                        painter = painterResource(id = R.drawable.bookmark),
                    )
                }
            }
            Divider(color = Color.Gray, thickness = 1.dp)

            ComposeGridLayout(photo)

            Divider(color = Color.Gray, thickness = 1.dp)

            StatsLinearLayout(photo)

            Divider(color = Color.Gray, thickness = 1.dp)

            if (photo?.city != "null") {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RoundOutlineTextView(photo?.city ?: "")
                    Spacer(modifier = Modifier.width(10.dp))
                    RoundOutlineTextView(photo?.country ?: "")
                }
            }
        }
    }
}

@Composable
fun ProfilePicture(
    painter: Painter,
    modifier: Modifier = Modifier,
    contentDescription: String?
) {
    Image(
        painter = painter,
        contentDescription = contentDescription,
        modifier = modifier.clip(CircleShape),
        contentScale = ContentScale.Crop,
    )
}

@Composable
fun IconButton(
    painter: Painter,
) {
    Image(
        painter = painter,
        contentDescription = null,
        modifier = Modifier
            .size(45.dp)
            .padding(10.dp)
    )
}

@Composable
fun ComposeGridLayout(photo: Unsplash?) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        GridLayoutItem("Camera", photo?.camera ?: "")
        GridLayoutItem("Aperture", photo?.aperture ?: "")
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        GridLayoutItem("Focal Length", photo?.focalLength ?: "")
        GridLayoutItem("Shutter Speed", "1/125s")
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        GridLayoutItem("ISO", photo?.iso.toString())
        GridLayoutItem("Dimensions", "${photo?.width} x ${photo?.height}")
    }
}

@Composable
fun GridLayoutItem(label: String, value: String) {
    Column(
        modifier = Modifier.width(200.dp)){
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = label,
            color = Color.White,
            fontSize = 17.sp,
        )
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = value,
            color = Color.Gray,
            fontSize = 17.sp,
        )
    }
}

@Composable
fun StatsLinearLayout(photo: Unsplash?) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .padding(horizontal = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        LinearLayoutItem("Views", "1.2M")
        LinearLayoutItem("Downloads", photo?.downloads.toString())
        LinearLayoutItem("Likes", photo?.likes.toString())
    }
}

@Composable
fun LinearLayoutItem(label: String, value: String) {
    Column (
        modifier = Modifier.wrapContentWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = label,
            color = Color.White,
            textAlign = TextAlign.Center,
            fontSize = 15.sp,
        )
        Text(
            text = value,
            color = Color.Gray,
            textAlign = TextAlign.Center,
            fontSize = 15.sp,
        )
    }
}

@Composable
fun RoundOutlineTextView(text: String) {
    Box(
        modifier = Modifier
            .wrapContentSize()
            .background(
                color = Color.Gray,
                shape = RoundedCornerShape(20.dp),
            )
            .padding(horizontal = 10.dp)
    ) {
        Text(
            text = text,
            modifier = Modifier
                .padding(3.dp),
            color = Color.White,
            fontSize = 11.sp,
            style = androidx.compose.ui.text.TextStyle(fontWeight = FontWeight.Bold),
            textAlign = TextAlign.Center
        )
    }
}