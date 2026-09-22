package ci.nsu.moble

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.material3.TextField
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.material3.TextField
import android.content.Intent
import kotlin.jvm.java
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import ci.nsu.moble.ShoppingViewModel
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.layout.Row

import androidx.compose.foundation.layout.fillMaxWidth

import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Checkbox
import androidx.compose.ui.unit.sp


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                MyScreen(
                    modifier = Modifier.padding(innerPadding)
                )
            }
        }
    }

    @Composable
    fun MyAppTheme(content: @Composable () -> Unit) {
        MaterialTheme(content = content)
    }

    @Composable
    fun MyScreen (modifier: Modifier = Modifier) {
        var newItemText by remember { mutableStateOf("") }
        val viewModel: ShoppingViewModel = viewModel()
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
        Column(
            modifier = modifier.fillMaxSize().padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                value = newItemText,
                onValueChange = { text ->
                    newItemText = text
                    // Синхронизируем с ViewModel (чтобы addItem видел актуальный текст)
                    viewModel.onNewItemTextChanged(text)
                },
                label = { Text("Продукт") }
            )
            Button(
                onClick = {viewModel.addItem()},
            ) {
                Text("Добавить")
            }
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {

                items (uiState.items, key = { it.id }) { item ->
                    Row( horizontalArrangement = Arrangement.SpaceBetween,){
                    Text(text = item.name)

                    Checkbox(
                        checked = item.isBought,
                        onCheckedChange = { viewModel.toggleItemBought(item.id) }
                    )
                    Button(
                        onClick = { viewModel.deleteItem(item.id) }
                    ) {
                        Text("Удалить")
                    }
                }
                }
            }
        }
    }
}
