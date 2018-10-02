import com.google.gson.Gson
import java.io.File
import java.io.InputStream

class Main {
    companion object {

        private const val pathReader = "PATH_READER"
        private const val pathWriting = "PATH_WRITING"

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
            for (states in states!!.states) {
                for (city in states.cities) {
                    file.appendText("\n"+city + "/" + states.initial+",")
                }
            }

            println("===================== FINISH =====================")
            println("File path: $pathWriting")
        }
    }
}