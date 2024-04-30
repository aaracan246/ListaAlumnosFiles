import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
fun AppListaAlumn(){

    var nombreUsuario by remember { mutableStateOf("") }
    val alumnos = remember { mutableStateListOf<String>() }

    for (alumno in retornarListaAlum()){
        alumnos.add(alumno)
    }

    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(5.dp, alignment = Alignment.CenterVertically),
        modifier = Modifier.fillMaxSize()
    ){
        Row (
            modifier = Modifier.fillMaxSize()
        ){
            OutlinedTextField(
                value = nombreUsuario,
                onValueChange = { nombreUsuario = it },
                label = { Text(text = "Usuario") }
            )
            LazyColumn (
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(5.dp, alignment = Alignment.CenterVertically),
                userScrollEnabled = true,
                modifier = Modifier.fillMaxSize()
            ){
                items(alumnos){estudiante ->
                    Text(estudiante)
                }
            }
        }
    }
}

fun retornarListaAlum(): List<String>{
    val listaAl = "listaalumnos.txt"
    val archivo = File(listaAl)
    val listaAlumnos: MutableList<String> = mutableListOf()

    if (archivo.exists()){
        val contenido = archivo.readText()
        val listaAlumnosLocal = contenido.split(", ")
        println("Lista de alumnos:")
        for (alumno in listaAlumnosLocal) { listaAlumnos.add(alumno)}
    }
    else{
        println("No se ha podido acceder al fichero ($archivo).")
    }
    return listaAlumnos
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        AppListaAlumn()
    }
}



