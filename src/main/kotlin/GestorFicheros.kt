import java.io.File

class GestorFicheros: IFiles {

    override fun retornarListaAlum(): List<String> {
        val listaAl = "listaalumnos.txt"
        val archivo = File(listaAl)
        val listaAlumnos: MutableList<String> = mutableListOf()

        if (archivo.exists()) {
            val contenido = archivo.readText()
            val listaAlumnosLocal = contenido.split(", ")
            for (alumno in listaAlumnosLocal) {
                listaAlumnos.add(alumno)
            }
        } else {
            println("No se ha podido acceder al fichero ($archivo).")
        }
        return listaAlumnos
    }


    override fun guardarListaAlum(alumnos: List<String>){
        val listaAl = "listaalumnos.txt"
        val archivo = File(listaAl)

        if (archivo.exists()){
            archivo.writeText(alumnos.joinToString(", "))
        }
        else{ println("No se ha podido acceder al fichero ($archivo).") }
    }
}