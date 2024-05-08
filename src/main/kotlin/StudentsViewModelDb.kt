import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf

class StudentsViewModelDb(private val studentRepository: StudentRepository): IStudentsViewModel {

    private val nuevoNombreUsuarioPriv = mutableStateOf("")
    override var nuevoNombreUsuario: State<String> = nuevoNombreUsuarioPriv

    private val alumnosPriv = mutableStateListOf<String>()
    override val alumnos: List<String> = alumnosPriv

    private val infoMessagePriv = mutableStateOf("")
    override var infoMessage: State<String> = infoMessagePriv

    private val showInfoMessagePriv = mutableStateOf(false)
    override var showInfoMessage: State<Boolean> = showInfoMessagePriv

    override fun cargarAlumnos() {
        TODO("Not yet implemented")
    }

    override fun introducirAlumn() {
        TODO("Not yet implemented")
    }

    override fun cambiarNomAlumn(alumno: String) {
        TODO("Not yet implemented")
    }

    override fun borrarAlum(alumno: String) {
        TODO("Not yet implemented")
    }

    override fun limpiarLista() {
        TODO("Not yet implemented")
    }

    override fun guardarLista() {
        TODO("Not yet implemented")
    }

}