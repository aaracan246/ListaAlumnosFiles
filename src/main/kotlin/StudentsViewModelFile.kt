import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import java.io.File

class StudentsViewModelFile(

    private val manejoArchivos: IFiles,
    private val archivoStudents: File

): IStudentsViewModel {

    private val nuevoNombreUsuarioPriv = mutableStateOf("")
    override var nuevoNombreUsuario: State<String> = nuevoNombreUsuarioPriv

    private val alumnosPriv = mutableStateListOf<String>()
    override val alumnos: List<String> = alumnosPriv

    private val infoMessagePriv = mutableStateOf("")
    override var infoMessage: State<String> = infoMessagePriv

    private val showInfoMessagePriv = mutableStateOf(false)
    override var showInfoMessage: State<Boolean> = showInfoMessagePriv

    override fun cargarAlumnos(){
        alumnosPriv.addAll(manejoArchivos.retornarListaAlum(archivoStudents))
    }

    override fun introducirAlumn(){
        alumnosPriv.add(nuevoNombreUsuario.value)
        nuevoNombreUsuarioPriv.value = ""
    }

    override fun cambiarNomAlumn(alumno: String){
        nuevoNombreUsuarioPriv.value = alumno
    }

    override fun borrarAlum(alumno: String) {
        alumnosPriv.remove(alumno)
    }

    override fun limpiarLista() {
        alumnosPriv.clear()
    }

    override fun guardarLista() {
        manejoArchivos.guardarListaAlum(archivoStudents, alumnosPriv)
    }
}