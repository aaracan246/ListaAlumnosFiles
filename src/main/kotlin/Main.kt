import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.input.key.*
import androidx.compose.ui.res.loadImageBitmap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogWindow
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import kotlinx.coroutines.delay
import java.io.File
import java.io.InputStream


/* Elevar y hacer funciones */

@Composable
fun AppListaAlumn(
    alumno: String,
    alumnos: List<String>,
    onAlumnoChange: (String) -> Unit,
    onAddAlumno: (String) -> Unit,
    onClearAll: () -> Unit,
    onSaveChanges: () -> Unit
) {
//    var nombreUsuario by remember { mutableStateOf("") }
//    val nameStudentfocusRequester = remember { FocusRequester() }
//
//    var showInfoMessage by remember { mutableStateOf(false)}
//    var infoMessage by remember { mutableStateOf("") }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {

            OutlinedTextField(
                value = alumno,
                onValueChange = onAlumnoChange,
                singleLine = true,
                label = { Text(text = "Student's name: ") },
                modifier = Modifier
                    .padding(16.dp)
                    .focusRequester(nameStudentfocusRequester)
                    .onKeyEvent { event ->

                        if (event.key == Key.Enter && event.type == KeyEventType.KeyUp && nombreUsuario.isNotBlank()){
                            onAddAlumno(nombreUsuario)
                            nombreUsuario = ""
                            nameStudentfocusRequester.requestFocus()
                            true

                        }
                        else{ false }
                    }
            )

            Button(
                onClick = { onAddAlumno(nombreUsuario) },
                modifier = Modifier
                    .padding(bottom = 16.dp)
            ) {
                Text("Add new student")
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Box(
                    modifier = Modifier
                        .background(color = Color.Cyan)
                        .border(width = 1.dp, color = Color.Black)
                        .weight(1f)
                ){
                    LazyColumn(
                        //modifier = Modifier.verticalScroll(enabled = true, state = ScrollState(0))
                    ) {
                        items(alumnos) { alumno ->
                            Text(alumno, modifier = Modifier
                                .padding(8.dp))
                        }
                    }
                }

                Button(
                    onClick = onClearAll,
                    modifier = Modifier.padding(top = 16.dp, bottom = 32.dp)
                ) {
                    Text("Clear all")
                }

                Button(
                    onClick =  {
                        onSaveChanges()
                        infoMessage = "¡¡Fichero guardado con éxito!!"
                        showInfoMessage = true
                    },
                    modifier = Modifier.padding(bottom = 16.dp)
                ) {
                    Text("Save changes")
                }
            }
        }
    }
    if (showInfoMessage) {
        InfoMessage(
            message = infoMessage,
            onCloseInfoMessage = {
                showInfoMessage = false
                infoMessage = ""
                nameStudentfocusRequester.requestFocus()
            }
        )
    }
    LaunchedEffect(showInfoMessage) {
        if (showInfoMessage) {
            delay(2000)
            showInfoMessage = false
            infoMessage = ""
            nameStudentfocusRequester.requestFocus()
        }
    }
}

@Composable
fun InfoMessage(message: String, onCloseInfoMessage: () -> Unit){
    DialogWindow(
        icon = painterResource("Sample1.png"),
        title = "Atención!!",
        resizable = false,
        onCloseRequest = onCloseInfoMessage
    ){
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ){
            Text(message)
        }
    }
}

fun main() = application {
    val gestorFicheros = GestorFicheros()
    val archivo = "listaalumnos.txt"
    val file = File(archivo)
    val viewModel = StudentsViewModel(gestorFicheros, file)
    val alumno by viewModel.nuevoNombreUsuario
    var listaAlumnos = viewModel.alumnos

    LaunchedEffect(key1 = true) {
        viewModel.cargarAlumnos()
    }

    val imageFile = File("src/main/resources/Sample1.png")
    val inputStream: InputStream = imageFile.inputStream()     // <-- Buscar cómo saltar este paso / no me ha dejado cargar la imagen del tirón

    Window(
        onCloseRequest = ::exitApplication,
        title = "AppListaAlumnos",
        icon = BitmapPainter(image = loadImageBitmap(inputStream))
    ) {


       // var savedAlumnos by remember { mutableStateOf(alumnos) }

        AppListaAlumn(
            alumno = alumno,

            alumnos = listaAlumnos,

            onAddAlumno = {
                viewModel.introducirAlumn()
            },
            onAlumnoChange = {
                viewModel.cambiarNomAlumn(it)
            },
            onClearAll = {
                viewModel.limpiarLista()
            },
            onSaveChanges = {
                viewModel.guardarLista()
            }
        )
    }
}




/*@Composable
	fun Toast(message: String, onDismiss: () -> Unit) {
		Dialog(
			icon = painterResource("info_icon.png"),
			title = "Atención",
			resizable = false,
			onCloseRequest = onDismiss
		) {
			Box(
				contentAlignment = Alignment.Center,
				modifier = Modifier.fillMaxSize().padding(16.dp)
			) {
				Text(message)
			}
		}
		// Cierra el Toast después de 2 segundos
		LaunchedEffect(Unit) {
			delay(2000)
			onDismiss()
		}
	}
* TooltipArea:

Se utiliza para proporcionar información contextual en forma de una pequeña ventana emergente (tooltip) cuando el usuario interactúa con el componente de la interfaz, por ejemplo, al pasar el cursor sobre un elemento en una aplicación de escritorio.

    TooltipArea(
        tooltip = {
            // Composable con la info que se quiera mostrar...
        }
    ) {
        // Composable sobre el que se mostrará la info contextual
    }
* onKeyEvent:

Es un manejador de eventos que te permite reaccionar a eventos de teclado que ocurren en un composable específico. Es especialmente útil en desarrollos de escritorio o en aplicaciones web que necesitan interactividad a través del teclado.

En resumen, para manejar los eventos de pulsaciones de teclas en un composable... por ejemplo en la LazyColumn dónde se mostrarán los estudiantes:

	modifier = Modifier
                .onKeyEvent { event ->
                    if (event.type == KeyEventType.KeyUp) {
                        when (event.key) {
                            Key.DirectionUp -> {
                                if (selectedIndex > 0) {
                                    onStudentSelected(selectedIndex - 1)
                                    true
                                } else false
                            }
                            Key.DirectionDown -> {
                                if (selectedIndex < studentsState.size - 1) {
                                    onStudentSelected(selectedIndex + 1)
                                    true
                                } else false
                            }
                            else -> false
                        }
                    } else {
                        false//Solo manejar cuando la tecla se haya levantado de la presión
                    }
                }*/