package com.example.notepad

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notepad.databinding.ActivityMainBinding



class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var  db : NoteDatabseHelper
    private lateinit var notesAdapter: NotesAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = NoteDatabseHelper(this)
        notesAdapter = NotesAdapter(db.getAllNotes(),this)


        binding.notesRecyclerView.layoutManager = LinearLayoutManager(this)

        binding.notesRecyclerView.adapter = notesAdapter

        binding.addButton.setOnClickListener {
            Intent(this, AddNoteActivity :: class.java).also{
                startActivity(it)
            }
        }

    }

    override fun onResume() {
        super.onResume()
        notesAdapter.refreshData(db.getAllNotes())
    }

}