package com.example.lemonadeapp

import android.annotation.SuppressLint
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonadeapp.ui.theme.LemonadeAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    lemonadeApp()
                }
            }
        }
    }
}

@Preview
@Composable
fun lemonadeApp(){

    lemonadeWithImage(modifier = Modifier.wrapContentSize(Alignment.Center))
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun lemonadeWithImage(modifier: Modifier = Modifier){

    var result by remember { mutableStateOf(1) }
    var squeezeCount by remember { mutableStateOf(0) }

    val imageId = when(result){
        1 -> R.drawable.lemon_tree
        2 -> R.drawable.lemon_squeeze
        3 -> R.drawable.lemon_drink
        else -> R.drawable.lemon_restart
    }

    val stringId = when(result){
        1 -> R.string.lemon_tree
        2 -> R.string.tap_lemon
        3 -> R.string.drink
        else -> R.string.start_again
    }

    Scaffold (
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                         text = "Lemonade",
                         fontWeight = FontWeight.Bold
                    )
                },

                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = Color.Yellow,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    actionIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )

            )
        }
    ) {

        Box(modifier = Modifier) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {

                Button(
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Green),
                    onClick = {

                        if (result == 1) {
                            squeezeCount = (2..4).random()
                            result++
                        } else if (result == 2) {
                            squeezeCount--;
                            if (squeezeCount == 0)
                                result++;
                        } else if (result == 4)
                            result = 1;
                        else
                            result++

                    },
                ) {
                    Image(
                        painter = painterResource(id = imageId),
                        contentDescription = result.toString()
                    )
                }


                Text(
                    text = stringResource(id = stringId),
                    modifier.padding(16.dp),
                    fontSize = 18.sp
                )

            }

        }
    }
}

