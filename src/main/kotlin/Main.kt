import androidx.compose.material.Button
import androidx.compose.ui.window.singleWindowApplication
import androidx.compose.material.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

fun main(): Unit = singleWindowApplication {
    val helloWorld = remember { mutableStateOf("Hello, World!") }
    Button( onClick = { helloWorld.value = "Pressed"}){
        Text(helloWorld.value)
    }

}