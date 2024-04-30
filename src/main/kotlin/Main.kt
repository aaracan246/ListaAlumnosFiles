import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import java.io.File


@Composable
fun ListaAlumnos(){

    var nombreUsuario by remember { mutableStateOf("") }

    val listaAlumnos = "listaalumnos.txt"
    val ARCHIVO = File(listaAlumnos)

    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(5.dp, alignment = Alignment.CenterVertically),
        modifier = Modifier.fillMaxSize()
    ){
        OutlinedTextField(
            value = nombreUsuario,
            onValueChange = { nombreUsuario = it },
            label = { Text(text = "Usuario") }
        )
    }
    LazyColumn (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(5.dp, alignment = Alignment.CenterVertically),
        modifier = Modifier.fillMaxSize()
    ){

    }
}


fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        ListaAlumnos()
    }
}



