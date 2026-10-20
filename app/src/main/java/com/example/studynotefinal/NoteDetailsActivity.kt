package com.example.studynotefinal

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.firebase.firestore.FirebaseFirestore

class NoteDetailsActivity : BaseActivity() {
    private var shareText = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_details)

        val tvSubject = findViewById<TextView>(R.id.tvSubject)
        val tvTitle = findViewById<TextView>(R.id.tvTitle)
        val tvContent = findViewById<TextView>(R.id.tvContent)

        // Receive the Note ID, then load that note from Firestore
        val noteId = intent.getStringExtra("NOTE_ID") ?: return

        FirebaseFirestore.getInstance().collection("notes").document(noteId).get()
            .addOnSuccessListener { doc ->
                val subject = doc.getString("subject")
                val title = doc.getString("title")
                val content = doc.getString("content")

                tvSubject.text = subject
                tvTitle.text = title
                tvContent.text = content
                shareText = "$subject\n$title\n\n$content"
            }

        // Implicit Intent: share the note through another app
        findViewById<Button>(R.id.btnShare).setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_TEXT, shareText)
            startActivity(Intent.createChooser(intent, "Share note via"))
        }
    }
}