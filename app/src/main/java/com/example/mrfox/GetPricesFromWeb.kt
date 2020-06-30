import java.net.HttpURLConnection
import java.net.URL


fun main(args: Array<String>) {

    fun sendGet() {
        val url = URL("https://www.mrfox.cz/sluzby")

        with(url.openConnection() as HttpURLConnection) {
            requestMethod = "GET"  // optional default is GET

            inputStream.bufferedReader().use {
                val listicek = it.readLines()

                val indexes = arrayListOf<Int>()
                for ((index, value) in listicek.withIndex()) {
                    if ("service-item" in value){
                        indexes.add(index)
                    }
                }

                var loopTru = 0

                var rangeOfservices = arrayListOf<Int>()
                while (loopTru <= indexes.count()) {
                    for (i in indexes[loopTru]..indexes[loopTru+1]) {
                        rangeOfservices.add(i)
                    }

                    loopTru++

                }

            }
        }
    }


    sendGet()

}
