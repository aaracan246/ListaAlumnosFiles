import java.sql.Connection
import java.sql.Statement



class StudentRepository {
    fun getAllStudents(): Result<List<String>>{

        var connectionDb: Connection? = null
        var stmt: Statement? = null

        try {
            val students = mutableListOf<String>()
            connectionDb = Database.getConnection()
            stmt = connectionDb.createStatement()
            val query = "SELECT name FROM students"
            val rs = stmt.executeQuery(query)

            while (rs.next()){
                students.add(rs.getString("name"))
            }

            stmt.close()
            return Result.success(students)

        }catch (e: Exception){
            return Result.failure(e)
        }
    }

    fun updateStudents(students: List<String>): Result<Unit> {
        var connectionDb : Connection? = null
        return try {
            connectionDb = Database.getConnection()
            connectionDb.autoCommit = false
            connectionDb.createStatement().use { stmt ->
                stmt.execute("DELETE FROM students")
            }
            connectionDb.prepareStatement("INSERT INTO students (name) VALUES (?)").use { ps ->
                for (student in students) {
                    ps.setString(1, student)
                    ps.executeUpdate()
                }
            }
            connectionDb.commit()
            Result.success(Unit)
        } catch (e: Exception) {
            connectionDb?.rollback()
            Result.failure(e)
        } finally {
            connectionDb?.autoCommit = true
            connectionDb?.close()
        }
    }


}