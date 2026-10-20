package com.example.studynotefinal

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity : AppCompatActivity() {
    val TAG = "Lifecycle"
    val name get() = javaClass.simpleName

    override fun onCreate(savedInstanceState: Bundle?) { super.onCreate(savedInstanceState); Log.d(TAG, "$name onCreate") }
    override fun onStart() { super.onStart(); Log.d(TAG, "$name onStart") }
    override fun onResume() { super.onResume(); Log.d(TAG, "$name onResume") }
    override fun onPause() { super.onPause(); Log.d(TAG, "$name onPause") }
    override fun onStop() { super.onStop(); Log.d(TAG, "$name onStop") }
    override fun onDestroy() { super.onDestroy(); Log.d(TAG, "$name onDestroy") }
}