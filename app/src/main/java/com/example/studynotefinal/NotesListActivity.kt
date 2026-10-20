package com.example.studynotefinal

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import com.google.firebase.firestore.FirebaseFirestore

class NotesListActivity : BaseActivity() {
    private val ids = ArrayList<String>()     // Firestore document IDs
    private val titles = ArrayList<String>()  // text shown in the list
    private lateinit var adapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes_list)

        val listView = findViewById<ListView>(R.id.listNotes)
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, titles)
        listView.adapter = adapter

        // Explicit Intent + pass the Note ID
        listView.setOnItemClickListener { _, _, position, _ ->
            val intent = Intent(this, NoteDetailsActivity::class.java)
            intent.putExtra("NOTE_ID", ids[position])
            startActivity(intent)
        }

        findViewById<Button>(R.id.btnAdd).setOnClickListener {
            startActivity(Intent(this, AddNoteActivity::class.java))
        }
    }

    // Runs every time the screen shows, so the list refreshes after saving
    override fun onResume() {
        super.onResume()
        FirebaseFirestore.getInstance().collection("notes").get()
            .addOnSuccessListener { result ->
                ids.clear()
                titles.clear()
                for (doc in result) {
                    ids.add(doc.id)
                    titles.add("${doc.getString("subject")} - ${doc.getString("title")}")
                }
                adapter.notifyDataSetChanged()
            }
    }
}