package com.example.lab4;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddNoteActivity extends AppCompatActivity {
    public static final String TAG = "Add note activity";
    private Intent intent;
    private EditText textNote, titleNote;
    NoteDatabase db;
    Toast message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.v(TAG, "onCreate=");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addnoteactivity);
        titleNote = findViewById(R.id.title);
        textNote = findViewById(R.id.editTextNote);
        db = NoteDatabase.getInstance(this);
        message =Toast.makeText(getApplicationContext(),R.string.empty,Toast.LENGTH_SHORT);
    }
    public void onSaveandClose(View view){
        Log.v(TAG, "On save and close");
        String title = titleNote.getText().toString();
        String note = textNote.getText().toString();
        if (!title.matches("") && !note.matches("")){
            Note newNote = new Note(title,note);
            db.noteDao().insertSingleNote(newNote);
            intent = new Intent(AddNoteActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }else{
            message.setText(getString(R.string.emptyText));
            message.show();
            Log.v(TAG, "Can't be Empty text");
        }



    }
    public void onCloseNote(View view) {
        Log.v(TAG, "On close click");
        intent = new Intent(AddNoteActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}

