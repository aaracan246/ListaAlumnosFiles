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
        val result = studentRepository.getAllStudents()

        // when
    }

    override fun introducirAlumn() {
        val newAlumno = nuevoNombreUsuario.value
        if (newAlumno.isNotBlank()){
            alumnosPriv.add(newAlumno)
            nuevoNombreUsuarioPriv.value = ""
        }
    }

    override fun cambiarNomAlumn(alumno: String) {
        nuevoNombreUsuarioPriv.value = alumno
    }

    override fun borrarAlum(alumno: String) {
        alumnosPriv.remove(alumno)
    }

    override fun limpiarLista() {
        alumnosPriv.clear()
    }

    override fun guardarLista() {
        TODO("Not yet implemented")
    }

}