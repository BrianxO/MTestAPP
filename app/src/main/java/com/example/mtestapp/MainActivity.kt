package com.example.mtestapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.compose.material3.Button





class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MTestApp()
        }
    }
}

@Composable
fun MTestApp() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "current_conditions") {
        composable("current_conditions") {
            MTestAppScreen(navController = navController)
        }
        composable("forecast_screen") {
            ForecastScreen()
        }
    }
}


@Composable
fun MTestAppScreen(navController: NavController) {
    Column(Modifier.fillMaxSize()) {
        MTestAppTitle()
        Column(Modifier.weight(1f)) {
            MTestAppBox()
            MTestAppStats(navController)
            Spacer(modifier = Modifier.height(16.dp))
            MTestAppContext()
        }
        Button(
            onClick = { navController.navigate("forecast_screen") },
            modifier = Modifier.fillMaxWidth().padding(7.dp)
        ) {
            Text(text = "Forecast")
        }
    }
}



@Composable
fun MTestAppTitle() {
    val titleColor = Color.Red

    Surface(modifier = Modifier.fillMaxWidth(), color = titleColor) {
        Text(
            text = stringResource(R.string.app_name),
            modifier = Modifier.padding(16.dp),
            color = Color.White
        )
    }
}

@Composable
fun MTestAppStats(navController: NavController) {
    val temperature = stringResource(R.string.temp)
    val feelsLike = stringResource(R.string.fltemp)
    val degreeSymbol = "\u00B0"

    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Column(Modifier.weight(1f)) {
            Text(
                text = buildAnnotatedString {
                    append(temperature)
                    withStyle(style = SpanStyle(fontSize = 75.sp)) {
                        append(" $degreeSymbol")
                    }
                },
                modifier = Modifier.padding(start = 16.dp),
                fontSize = 75.sp
            )
            Text(
                text = feelsLike,
                modifier = Modifier
                    .size(200.dp, 60.dp)
                    .padding(16.dp),
                fontSize = 14.sp
            )
        }
        Image(
            painter = painterResource(id = R.drawable.sun),
            contentDescription = null,
            modifier = Modifier
                .size(100.dp)
                .padding(end = 13.dp)
                .align(Alignment.CenterVertically)
        )
        }
    }

@Composable
fun MTestAppContext() {
    Column(Modifier.padding(start = 6.dp)) {
        val low = stringResource(R.string.lowtmp)
        val high = stringResource(R.string.hightmp)
        val humidity = stringResource(R.string.humidity)
        val pressure = stringResource(R.string.pressure)

        Text(text = low)
        Text(text = high)
        Text(text = humidity)
        Text(text = pressure)
    }
}

@Composable
fun MTestAppBox() {
    val location = stringResource(R.string.secondary_title)

    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.TopCenter
    ) {
        Text(
            text = location,
            color = MaterialTheme.colorScheme.onBackground,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(13.dp)
        )
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    val navController = rememberNavController()
    MTestAppScreen(navController = navController)
}
