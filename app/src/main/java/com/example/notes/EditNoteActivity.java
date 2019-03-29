package com.example.notes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class EditNoteActivity extends AppCompatActivity {
    private int noteId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note);

        Intent intent = getIntent();
        noteId = intent.getIntExtra(MainActivity.NOTE_ID, -1);

        if(!isNoteNew(noteId)) {
            getNote(noteId);
        }
    }

    private boolean isNoteNew(int noteId) {
        return noteId == -1;
    }

    private void getNote(int noteId) {
        NotesDatabaseHelper db = new NotesDatabaseHelper(this);
        Note note = db.getNote(noteId);

        EditText noteTitleWrite = findViewById(R.id.noteTitleWrite);
        noteTitleWrite.setText(note.getTitle());
        EditText noteTextWrite = findViewById(R.id.noteTextWrite);
        noteTextWrite.setText(note.getText());
    }

    private void storeInDB(Note note) {
        NotesDatabaseHelper db = new NotesDatabaseHelper(this);

        if(note.isEmpty()) {
            db.deleteNote(note);
        } else if (isNoteNew(noteId)) {
            db.addNote(note);
        } else {
            db.updateNote(note);
        }
    }

    private void goToMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void saveNote(View view) {
        String noteTitle = ((EditText)findViewById(R.id.noteTitleWrite)).getText().toString();
        String noteText = ((EditText)findViewById(R.id.noteTextWrite)).getText().toString();
        Note note = new Note(noteId, noteTitle, noteText);

        storeInDB(note);

        goToMainActivity();
    }
}
