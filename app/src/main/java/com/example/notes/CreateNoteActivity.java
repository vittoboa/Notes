package com.example.notes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class CreateNoteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note);
    }

    private boolean isNoteEmpty(Note note) {
        return note.getTitle().isEmpty() && note.getText().isEmpty();
    }

    private void storeInDB(Note note) {
        NotesDatabaseHelper db = new NotesDatabaseHelper(this);

        if(!isNoteEmpty(note)) {
            db.addNote(note);
        }

    }

    private void goToMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void saveNote(View view) {
        String noteTitle = ((EditText)findViewById(R.id.noteTitleWrite)).getText().toString();
        String noteText = ((EditText)findViewById(R.id.noteTextWrite)).getText().toString();
        Note note = new Note(noteTitle, noteText);

        storeInDB(note);

        goToMainActivity();
    }
}
