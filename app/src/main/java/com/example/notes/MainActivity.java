package com.example.notes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ListNotesAdapter.OnNoteListener, DialogDeleteNote.DialogDeleteNoteListener {

    private ArrayList<Note> mNotes = new ArrayList<>();
    public static final String NOTE_ID = "com.example.notes";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getAllNotes();
        initListNotes();
    }

    public void createNewNote(View view) {
        Intent intent = new Intent(this, EditNoteActivity.class);
        startActivity(intent);
    }

    private void getAllNotes() {
        NotesDatabaseHelper db = new NotesDatabaseHelper(this);

        mNotes = db.getAllNotes();
    }

    private void initListNotes() {
        RecyclerView listNotes = findViewById(R.id.listNotes);
        ListNotesAdapter adapter = new ListNotesAdapter(this, mNotes, this);
        listNotes.setAdapter(adapter);
        listNotes.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onNoteClick(int position) {
        Intent intent = new Intent(this, EditNoteActivity.class);
        intent.putExtra(NOTE_ID, mNotes.get(position).getId());
        startActivity(intent);
    }

    @Override
    public void onNoteLongClick(int position) {
        dialogDeleteNote(position);
    }

    public void dialogDeleteNote(int position) {
        DialogDeleteNote dialogDeleteNote = new DialogDeleteNote();

        Bundle bundle = new Bundle();
        bundle.putInt("position", position);
        dialogDeleteNote.setArguments(bundle);

        dialogDeleteNote.show(getSupportFragmentManager(), "delete note dialog");
    }

    @Override
    public void deleteNote(int position) {
        NotesDatabaseHelper db = new NotesDatabaseHelper(this);
        Note note = mNotes.get(position);
        db.deleteNote(note);

        finish();
        overridePendingTransition(0, 0);
        startActivity(getIntent());
        overridePendingTransition(0, 0);
    }
}
