package com.example.notes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Note> mNotes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getAllNotes();
        initListNotes();
    }

    public void createNewNote(View view) {
        Intent intent = new Intent(this, CreateNoteActivity.class);
        startActivity(intent);
    }

    private void getAllNotes() {
        NotesDatabaseHelper db = new NotesDatabaseHelper(this);

        mNotes = db.getAllNotes();
    }

    private void initListNotes() {
        RecyclerView listNotes = findViewById(R.id.listNotes);
        ListNotesAdapter adapter = new ListNotesAdapter(this, mNotes);
        listNotes.setAdapter(adapter);
        listNotes.setLayoutManager(new LinearLayoutManager(this));
    }
}
