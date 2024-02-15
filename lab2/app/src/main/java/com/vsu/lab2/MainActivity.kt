package com.vsu.lab2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.io.BufferedReader
import java.io.InputStreamReader

class MainActivity : AppCompatActivity() {

    private val READ_REQUEST_CODE = 1
    private val CREATE_FILE_REQUEST_CODE = 2

    private val namesList = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Button to read names from a file
        findViewById<Button>(R.id.btnReadNames).setOnClickListener {
            readNamesFromFile()
        }

        // Button to sort names
        findViewById<Button>(R.id.btnSortNames).setOnClickListener {
            sortNames()
        }

        // Button to create a new file
        findViewById<Button>(R.id.btnCreateFile).setOnClickListener {
            createNewFile()
        }
    }

    private fun readNamesFromFile() {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
        intent.addCategory(Intent.CATEGORY_OPENABLE)
        intent.type = "text/plain" // Set the MIME type (you can change this as needed)
        startActivityForResult(intent, READ_REQUEST_CODE)
    }

    private fun sortNames() {
        namesList.sort()
        val namesString = namesList.joinToString("\n")
        val textView: TextView = findViewById(R.id.textView)
        textView.text = namesString
    }

    private fun createNewFile() {
        val intent = Intent(Intent.ACTION_CREATE_DOCUMENT)
        intent.addCategory(Intent.CATEGORY_OPENABLE)
        intent.type = "text/plain" // Set the MIME type (you can change this as needed)
        intent.putExtra(Intent.EXTRA_TITLE, "names.txt")
        startActivityForResult(intent, CREATE_FILE_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == READ_REQUEST_CODE && resultCode == RESULT_OK) {
            readFile(data)
        } else if (requestCode == CREATE_FILE_REQUEST_CODE && resultCode == RESULT_OK) {
            writeFile(data)
        }
    }

    private fun writeFile(data: Intent?) {
        val uri = data?.data
        val outputStream = uri?.let { contentResolver.openOutputStream(it) }
        namesList.forEach { name ->
            outputStream?.write(name.toByteArray())
            outputStream?.write("\n".toByteArray())
        }
        outputStream?.close()
    }

    private fun readFile(data: Intent?) {
        val uri = data?.data
        val inputStream = uri?.let { contentResolver.openInputStream(it) }
        val reader = BufferedReader(InputStreamReader(inputStream))
        var line: String?
        namesList.clear()
        while (reader.readLine().also { line = it } != null) {
            namesList.add(line.orEmpty())
        }
        val namesString = namesList.joinToString("\n")
        val textView: TextView = findViewById(R.id.textView)
        textView.text = namesString
        reader.close()
    }
}
