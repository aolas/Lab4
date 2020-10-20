package com.example.lab4;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class AddNoteActivity extends AppCompatActivity {
    public static final String TAG = "Add note activity";
    private Intent intent;
    private EditText textNote, titleNote;
    NoteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.v(TAG, "onCreate=");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addnoteactivity);
        titleNote = findViewById(R.id.title);
        textNote = findViewById(R.id.editTextNote);
        db = NoteDatabase.getInstance(this);

    }
    public void onSaveandClose(View view){
        Log.v(TAG, "On save and close");
        String title = titleNote.getText().toString();
        String note = textNote.getText().toString();

        Note newNote = new Note(title,note);
        db.noteDao().insertSingleNote(newNote);

        intent = new Intent(AddNoteActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
    public void onCloseNote(View view) {
        Log.v(TAG, "On close click");
        intent = new Intent(AddNoteActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}

