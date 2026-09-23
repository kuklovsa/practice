package ci.nsu.mobile.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ci.nsu.mobile.main.ui.theme.PracticeTheme
import android.R.attr.button
import android.R.attr.text
import android.content.Intent
import androidx.compose.ui.platform.LocalContext
import android.R.attr.label
import android.R.attr.onClick
import androidx.compose.material3.Button
import android.app.Activity

class VvodData : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PracticeTheme {
                ScreenFirst()
            }
        }
    }
}





    @Composable
    fun ScreenFirst() {
        var text1 by remember { mutableStateOf("") }
        var text2 by remember { mutableStateOf("") }
        val context = LocalContext.current
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                value = text1,
                onValueChange = { newValue ->
                    if (newValue.all { it.isDigit() }) {
                        text1 = newValue
                    }
                },
                label = { Text("Стартовый взнос") }
            )
            TextField(
                value = text2,
                onValueChange = { newValue ->
                    if (newValue.all { it.isDigit() }) {
                        text2 = newValue
                    }
                },
                label = { Text("Срок вклада") }
            )
            Button(
                onClick = {
                    if (text1.isNullOrBlank() or text2.isNullOrBlank()) {
                    } else {
                        val intent = Intent(context, VvodData2::class.java).apply {
                            putExtra("StartMoney", text1)
                            putExtra("mounth", text2)
                        }
                    }
                }
            ) {
                Text ("Посчитать")
            }
            Button (
                onClick = {
                    if (context is Activity) {
                        context.finish()
                    }
                }
            ) {
                Text ("Выйти")
            }
        }
    }

