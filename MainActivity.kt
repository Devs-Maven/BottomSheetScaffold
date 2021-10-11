package com.example.myapplication

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.MyApplicationTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface(color = MaterialTheme.colors.background) {
                    MyApp()
                }
            }
        }
    }
}


@ExperimentalMaterialApi
@Composable
fun MyApp(){

    val sheetState = rememberBottomSheetScaffoldState()
    val scope = rememberCoroutineScope()

    BottomSheetScaffold(
        scaffoldState = sheetState,
        sheetContent = {
            Box(modifier = Modifier.fillMaxHeight(0.4f)){
                Text(
                    text = "This is an Example of Bottom Sheet",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.W600,
                    modifier = Modifier.padding(35.dp)
                )
            }
        },
        sheetPeekHeight = 0.dp,
        sheetBackgroundColor = Color.LightGray,
        sheetShape = RoundedCornerShape(20.dp),
        sheetGesturesEnabled = true,
        content = {
            Column(
                Modifier.fillMaxSize(),
                Arrangement.Center,
                Alignment.CenterHorizontally
            ) {
                Button(
                    onClick = {
                       scope.launch {
                           if(sheetState.bottomSheetState.isCollapsed){
                               sheetState.bottomSheetState.expand()
                               Log.e("Pos", "Expand")
                           }
                           else{
                               sheetState.bottomSheetState.collapse()
                               Log.e("Pos", "Collapse")
                           }
                       }
                    },
                    content = {
                        Text("Show/Hide", fontWeight = FontWeight.W600, fontSize = 25.sp)
                    }
                )
            }
        }
    )

}

