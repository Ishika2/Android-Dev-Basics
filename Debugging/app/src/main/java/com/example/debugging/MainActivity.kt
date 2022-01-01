package com.example.debugging

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        Log.d(TAG,"this is where the app crashed before")
        val helloTextView: TextView = findViewById(R.id.hello_world)
//        Log.d(TAG, "this should be logged if the bug is fixed")
        helloTextView.text = "Hello, debugging"
        logging()
//        setContentView(R.layout.activity_main)
        division()
//        logging()
    }
    private fun division() {
        val num = 60
        var deno = 4
        repeat(5) {
            Log.v(TAG, "${num/deno}")
            deno--
        }
    }
    private fun logging() {
        Log.e(TAG, "ERROR: a serious error like an app crash")
        Log.w(TAG, "WARN: warns about the potential for serious errors")
        Log.i(TAG, "INFO: reporting technical info")
        Log.d(TAG, "DEBUG: Reporting technical info useful for debugging")
        Log.v(TAG, "VERBOSE: more verbose than debug logs!")
    }
}