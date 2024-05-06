import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogWindow
import kotlinx.coroutines.delay

@Composable
fun StudentScreen(
    viewModel: IStudentsViewModel
) {
    val nameStudentfocusRequester = remember { FocusRequester() }

    val alumno by viewModel.nuevoNombreUsuario
    val listaAlumnos = viewModel.alumnos
    var infoMessage = viewModel.infoMessage.value
    var showInfoMessage = viewModel.showInfoMessage.value

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
                onValueChange = { viewModel.cambiarNomAlumn(it) },
                singleLine = true,
                label = { Text(text = "Student's name: ") },
                modifier = Modifier
                    .padding(16.dp)
                    .focusRequester(nameStudentfocusRequester)
                    .onKeyEvent { event ->

                        if (event.key == Key.Enter && event.type == KeyEventType.KeyUp && alumno.isNotBlank()){
                            viewModel.introducirAlumn()
                            nameStudentfocusRequester.requestFocus()
                            true

                        }
                        else{ false }
                    }
            )

            Button(
                onClick = { viewModel.introducirAlumn() },
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
                    LazyColumn {
                        items(listaAlumnos) { alumno ->
                            Text(alumno, modifier = Modifier
                                .padding(8.dp))
                        }
                    }
                }

                Button(
                    onClick = { viewModel.limpiarLista() },
                    modifier = Modifier.padding(top = 16.dp, bottom = 32.dp)
                ) {
                    Text("Clear all")
                }

                Button(
                    onClick =  {
                        viewModel.guardarLista()
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