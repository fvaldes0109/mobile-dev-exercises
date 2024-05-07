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

class ExcersiseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column {
                Box(modifier = Modifier.height(300.dp)) {
                    Image(
                        painter = painterResource(id = R.drawable.bg_image),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )

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
                            text = "Barcelona, Spain",
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                        )
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
                                text = "Biel Morro",
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

                    ComposeGridLayout()

                    Divider(color = Color.Gray, thickness = 1.dp)

                    StatsLinearLayout()

                    Divider(color = Color.Gray, thickness = 1.dp)

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RoundOutlineTextView("barcelona")
                        Spacer(modifier = Modifier.width(10.dp))
                        RoundOutlineTextView("spain")
                    }
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
fun ComposeGridLayout() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        GridLayoutItem("Camera", "NIKON D3200")
        GridLayoutItem("Aperture", "f/5.0")
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        GridLayoutItem("Focal Length", "18.0mm")
        GridLayoutItem("Shutter Speed", "1/125s")
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        GridLayoutItem("ISO", "100")
        GridLayoutItem("Dimensions", "3906 x 4882")
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
fun StatsLinearLayout() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .padding(horizontal = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        LinearLayoutItem("Views", "8.8M")
        LinearLayoutItem("Downloads", "99.1K")
        LinearLayoutItem("Likes", "1.8K")
    }
}

@Composable
fun LinearLayoutItem(label: String, value: String) {
    Column {
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
                .width(60.dp)
                .height(20.dp),
            color = Color.White,
            fontSize = 11.sp,
            style = androidx.compose.ui.text.TextStyle(fontWeight = FontWeight.Bold),
            textAlign = TextAlign.Center
        )
    }
}