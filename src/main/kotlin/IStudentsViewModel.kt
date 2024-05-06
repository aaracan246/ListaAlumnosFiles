import androidx.compose.runtime.State

interface IStudentsViewModel {

    /* Variables y funciones para hacer override luego */

    val nuevoNombreUsuario: State<String>
    val alumnos: List<String>
    val infoMessage: State<String>
    val showInfoMessage: State<Boolean>

    fun cargarAlumnos()
    fun introducirAlumn()
    fun cambiarNomAlumn(alumno: String)
    fun borrarAlum(alumno: String)
    fun limpiarLista()
    fun guardarLista()

}