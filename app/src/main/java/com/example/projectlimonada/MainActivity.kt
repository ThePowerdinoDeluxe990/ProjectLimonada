package com.example.projectlimonada

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.projectlimonada.ui.theme.ProjectLimonadaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProjectLimonadaTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    BaseApp()
                }
            }
        }
    }
}


@Composable
fun AppCont(){
    var number by remember {
        mutableIntStateOf(1)
    }

    var randomNumber by remember{
        mutableIntStateOf(1)
    }

    val imageResource = when(number){
        1 -> R.drawable.lemon_tree
        2 -> R.drawable.lemon_squeeze
        3 -> R.drawable.lemon_drink
        else -> R.drawable.lemon_restart
    }

    val frase = when(number){
        1 -> R.string.first_word
        2 -> R.string.second_word
        3 -> R.string.third_word
        else -> R.string.last_word
    }

    val descripcion = when(number){
        1 -> R.string.imagedesc
        2 -> R.string.imagedesc1
        3 -> R.string.imagedesc2
        else -> R.string.imagedesc3
    }

    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center){

        Button(
            onClick = {
                if(number == 2){
                    randomNumber = (1..6).random()
                    if(randomNumber== 3){
                        number ++
                    }
                }else if(number == 4){
                    number = 1
                }else{
                    number ++
                }
                      },
            shape = RoundedCornerShape(20.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.tertiaryContainer)) {
            Image( painter = painterResource(imageResource),
                contentDescription = stringResource(id = descripcion)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(stringResource(frase))

    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaseApp(){
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(stringResource(R.string.app_name),
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                ) },
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color(0xFFFDF442))
            )
    }
    ){
        AppCont()
    }
}

@Preview(showBackground = true)
@Composable
fun Previewer(){
    BaseApp()
}