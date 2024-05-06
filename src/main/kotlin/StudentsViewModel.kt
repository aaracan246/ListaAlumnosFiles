import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import java.io.File

class StudentsViewModel(

    val manejoArchivos: IFiles,
    val archivoStudents: File

): IStudentsViewModel {

    private val nuevoNombreUsuarioPriv = mutableStateOf("")
    override val nuevoNombreUsuario: State<String> = nuevoNombreUsuarioPriv

    private val alumnosPriv = mutableStateListOf<String>()
    override val alumnos: List<String> = alumnosPriv

    private val infoMessagePriv = mutableStateOf("")
    override val infoMessage: State<String> = infoMessagePriv

    private val showInfoMessagePriv = mutableStateOf(false)
    override val showInfoMessage: State<Boolean> = showInfoMessagePriv



}