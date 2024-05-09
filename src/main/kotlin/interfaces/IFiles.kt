package interfaces

import java.io.File

interface IFiles {
    fun retornarListaAlum(file: File): List<String>
    fun guardarListaAlum(file: File, alumnos: List<String>)
}