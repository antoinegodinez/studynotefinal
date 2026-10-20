package com.example.studynotefinal

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore

class AddNoteActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)

        val etSubject = findViewById<EditText>(R.id.etSubject)
        val etTitle = findViewById<EditText>(R.id.etTitle)
        val etContent = findViewById<EditText>(R.id.etContent)

        findViewById<Button>(R.id.btnSave).setOnClickListener {
            val note = hashMapOf(
                "subject" to etSubject.text.toString().trim(),
                "title" to etTitle.text.toString().trim(),
                "content" to etContent.text.toString().trim()
            )

            if (note.values.any { it.isEmpty() }) {
                Toast.makeText(this, "Fill in all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            FirebaseFirestore.getInstance().collection("notes").add(note)
                .addOnSuccessListener {
                    Toast.makeText(this, "Note saved", Toast.LENGTH_SHORT).show()
                    finish() // Back-stack: close this screen; the existing list is shown, no duplicate
                }
                .addOnFailureListener {
                    Toast.makeText(this, "Save failed", Toast.LENGTH_SHORT).show()
                }
        }
    }
}