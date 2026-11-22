import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

@Composable
fun window() {
    val helloWorld = remember { mutableStateOf("Hello, World!") }
    Button( onClick = { helloWorld.value = "Pressed"}){
        Text(helloWorld.value)
    }
}