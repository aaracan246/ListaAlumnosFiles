import androidx.compose.runtime.State

interface IStudentsViewModel {

    /* Variables y funciones para hacer override luego */

    val nuevoNombreUsuario: State<String>
    val alumnos: List<String>

}