import interfaces.IFiles
import java.io.File

class GestorFicheros: IFiles {

    override fun retornarListaAlum(file: File): List<String> {
        val listaAlumnos: MutableList<String> = mutableListOf()

        if (file.exists()) {
            val contenido = file.readText()
            val listaAlumnosLocal = contenido.split(", ")
            for (alumno in listaAlumnosLocal) {
                listaAlumnos.add(alumno)
            }
        } else {
            println("No se ha podido acceder al fichero ($file).")
        }
        return listaAlumnos
    }


    override fun guardarListaAlum(file: File, alumnos: List<String>){

        if (file.exists()){
            file.writeText(alumnos.joinToString(", "))
        }
        else{ println("No se ha podido acceder al fichero ($file).") }
    }
}