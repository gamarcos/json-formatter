import com.google.gson.Gson
import java.io.File
import java.io.InputStream

class Main {
    companion object {

        private const val pathReader = "C:\\Users\\gabri\\Documents\\Projetos\\export-cities\\src\\main\\resources\\cities.json"
        private const val pathWriting = "C:\\Users\\gabri\\Documents\\Projetos\\export-cities\\src\\main\\resources\\citiesExport.txt"

        private var states: StatesModel? = null

        @JvmStatic
        fun main(args: Array<String>) {
            parseJSONData()
        }

        private fun parseJSONData() {
            val inputStream: InputStream = File(pathReader).inputStream()
            val strings = inputStream.bufferedReader().use { it.readText() }
            states = Gson().fromJson(strings, StatesModel::class.java)
            writingFile()
        }

        private fun writingFile() {
            val file = File(pathWriting)
            for (state in states!!.states) {
                for (city in state.cities) {
                    file.appendText("\n"+city + "/" + state.uf + ",")
                }
            }

            println("===================== FINISH =====================")
            println("File path: $pathWriting")
        }
    }
}