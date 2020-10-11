package yichunyen.swapi.demo.data

import java.io.BufferedReader
import java.io.FileInputStream
import java.io.InputStreamReader

private const val ASSET_BASE_PATH = "../app/src/main/assets/"

open class BaseTest {
    fun readJsonFile(jsonFileName: String): String {
        if (jsonFileName.endsWith("json")) {
            throw Exception("Do not put the json file extension")
        }

        val bufferedReader = BufferedReader(
            InputStreamReader(
                FileInputStream("$ASSET_BASE_PATH$jsonFileName.json")
            )
        )
        val stringBuilder = StringBuilder()
        var line = bufferedReader.readLine()
        while (line != null) {
            stringBuilder.append(line)
            line = bufferedReader.readLine()
        }

        return stringBuilder.toString()
    }
}