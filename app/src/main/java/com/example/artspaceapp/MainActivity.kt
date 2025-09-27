package com.example.artspaceapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspaceapp.ui.theme.ArtSpaceAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArtSpaceAppTheme {

            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ArtSpaceApp() {
    var id=1
    ArtSpaceAppTheme {
        DisplayArt()
    }
}

@Composable
fun DisplayArt(button1text:String="PREVIOUS",button2text:String="NEXT",button3text:String="RANDOM",button4Text:String="POEZIE",modifier:Modifier=Modifier.fillMaxSize().wrapContentSize(Alignment.Center)) {
    var id by remember{mutableStateOf(1)}
    val imageId=when(id){
       1->R.drawable.image1
       2->R.drawable.image2
        3->R.drawable.image3
        else ->R.drawable.image4
    }
    val titleId=when(id){
        1->R.string.name1
        2->R.string.name2
        3->R.string.name3
        else->R.string.name4
    }
    val citeId=when(id){
        1->R.string.citat1
        2->R.string.citat2
        3->R.string.citat3
        else->R.string.citat4
    }
    var showPoetry:Boolean by remember{ mutableStateOf(false) }

    Box(modifier=modifier) {
        Image(
            painter=painterResource(R.drawable.starsky),
            contentDescription="space sky",
            modifier=Modifier.fillMaxSize(),
            contentScale= ContentScale.Crop
        )
        Column(modifier = modifier) {
            if(!showPoetry){
            Image(
                painter = painterResource(imageId),
                contentDescription = null,
                modifier = Modifier.fillMaxWidth().align(Alignment.CenterHorizontally).width(200.dp)
                    .height(200.dp)
            )

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            Text(
                text = stringResource(id = titleId),
                fontSize = 30.sp,
                modifier = Modifier.align(Alignment.CenterHorizontally), color = Color.White
            )
            Spacer(modifier = Modifier.height(100.dp))
            Text(
                text = "Citat:\t" + stringResource(id = citeId),
                fontSize = 20.sp,
                modifier = Modifier.align(Alignment.CenterHorizontally).padding(20.dp),
                color = Color.White
            )
            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    onClick = {
                        id = id - 1
                        if (id == 0) id = 4
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
                ) {
                    Text(
                        text = button1text,
                        color = Color.Black,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Button(
                    onClick = {
                        if (id == 4) id = 1
                        else id++
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Green)
                ) {
                    Text(
                        text = button2text,
                        color = Color.Black,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Button(
                    onClick = {
                        id = (1..4).random()
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Yellow)
                ) {
                    Text(
                        text = button3text,
                        color = Color.Black,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            Spacer(modifier=Modifier.height(20.dp))
                Button(onClick={
                    showPoetry=!showPoetry
                },
                    colors=ButtonDefaults.buttonColors(containerColor = Color.Blue),
                    modifier=Modifier.align(Alignment.CenterHorizontally)){
                    Text(
                        text=button4Text,
                        color=Color.White
                    )
                }
            }


            val poetryId=when(id){
                1->R.string.poetry1
                2->R.string.poetry2
                3->R.string.poetry3
                else -> R.string.poetry4
            }
            val poetry=stringResource(poetryId)
            if(showPoetry) {
                Text(
                    text = poetry,
                    modifier = Modifier.padding(20.dp).align(Alignment.CenterHorizontally),
                    color = Color.White,
                    fontSize=20.sp
                )
                Button(onClick={
                    showPoetry=!showPoetry
                },
                    colors=ButtonDefaults.buttonColors(containerColor = Color.Blue),
                    modifier=Modifier.align(Alignment.CenterHorizontally)){
                    Text(
                        text="BACK",
                        color=Color.White
                    )
                }
            }






        }
    }
}