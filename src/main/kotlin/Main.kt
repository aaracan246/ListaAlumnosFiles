import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
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
fun AppListaAlumn(
    alumnos: List<String>,
    onAddAlumno: (String) -> Unit,
    onClearAll: () -> Unit,
    onSaveChanges: () -> Unit
) {
    var nombreUsuario by remember { mutableStateOf("") }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            OutlinedTextField(
                value = nombreUsuario,
                onValueChange = { nombreUsuario = it },
                label = { Text(text = "Student's name: ") },
                modifier = Modifier.padding(16.dp)
            )

            Button(
                onClick = { onAddAlumno(nombreUsuario) },
                modifier = Modifier.padding(bottom = 16.dp)
            ) {
                Text("Add new student")
            }
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            LazyColumn(
                modifier = Modifier.weight(1f)
            ) {
                items(alumnos) { alumno ->
                    Text(alumno, modifier = Modifier.padding(8.dp))
                }
            }

            Button(
                onClick = onClearAll,
                modifier = Modifier.padding(top = 16.dp, bottom = 32.dp)
            ) {
                Text("Clear all")
            }
            Button(
                onClick = onSaveChanges,
                modifier = Modifier.padding(bottom = 16.dp)
            ) {
                Text("Save changes")
            }
        }

    }
}


fun retornarListaAlum(): List<String> {
    val listaAl = "listaalumnos.txt"
    val archivo = File(listaAl)
    val listaAlumnos: MutableList<String> = mutableListOf()

    if (archivo.exists()) {
        val contenido = archivo.readText()
        val listaAlumnosLocal = contenido.split(", ")
        for (alumno in listaAlumnosLocal) {
            listaAlumnos.add(alumno)
        }
    } else {
        println("No se ha podido acceder al fichero ($archivo).")
    }
    return listaAlumnos
}


fun guardarListaAlum(alumnos: List<String>){
    val listaAl = "listaalumnos.txt"
    val archivo = File(listaAl)

    if (archivo.exists()){
        archivo.writeText(alumnos.joinToString(", "))
    }
    else{ println("No se ha podido acceder al fichero ($archivo).") }
}


fun main() = application {
    val alumnos = mutableListOf<String>()

    for (alumno in retornarListaAlum()) {
        alumnos.add(alumno)
    }

    Window(onCloseRequest = ::exitApplication) {
        var savedAlumnos by remember { mutableStateOf(alumnos) }

        AppListaAlumn(
            alumnos = savedAlumnos,
            onAddAlumno = { alumno ->
                val newAlumnos = savedAlumnos.toMutableList()
                newAlumnos.add(alumno)
                savedAlumnos = newAlumnos
            },
            onClearAll = {
                alumnos.clear()
                savedAlumnos = alumnos.toList().toMutableList()
            },
            onSaveChanges = {
                guardarListaAlum(savedAlumnos)
            }
        )
    }
}