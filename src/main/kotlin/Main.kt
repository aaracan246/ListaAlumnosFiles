import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.res.loadImageBitmap
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import java.io.File
import java.io.InputStream


/* Elevar y hacer funciones */

fun main() = application {
    val gestorFicheros = GestorFicheros()
    val archivo = "listaalumnos.txt"
    val file = File(archivo)
    val viewModel = StudentsViewModel(gestorFicheros, file)

    viewModel.cargarAlumnos()

    val imageFile = File("src/main/resources/Sample1.png")
    val inputStream: InputStream = imageFile.inputStream()     // <-- Buscar cómo saltar este paso / no me ha dejado cargar la imagen del tirón

    Window(
        onCloseRequest = ::exitApplication,
        title = "AppListaAlumnos",
        icon = BitmapPainter(image = loadImageBitmap(inputStream))
    ) {

        MaterialTheme {
            Surface(
                modifier = Modifier.fillMaxSize()
            ) {
                StudentScreen(viewModel)
            }
        }

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