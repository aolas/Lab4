package com.example.lab4;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class DeleteNoteActivity extends AppCompatActivity {


    public static final String TAG = "Delete note activity";
    NoteDatabase db;
    Toast message;
    Spinner spnSelect;
    List<AbstractNotes> abNotes;
    ArrayAdapter<AbstractNotes> dataAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.v(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.deletenoteactivity);
        db= NoteDatabase.getInstance(this);
        spnSelect = findViewById(R.id.spnSelect);
        abNotes = db.noteDao().getAllAbstractNotes();
        dataAdapter = new ArrayAdapter<AbstractNotes>(this, android.R.layout.simple_spinner_dropdown_item, abNotes);

        spnSelect.setAdapter(dataAdapter);
        message =Toast.makeText(getApplicationContext(),R.string.empty,Toast.LENGTH_SHORT);
    }

    public void onBtnDeleteClick(View view) {
        if (dataAdapter.getCount() > 0){
            AbstractNotes currentAbstractNote = (AbstractNotes) spnSelect.getSelectedItem();
            Note currentNote = new Note(currentAbstractNote.getId());
            db.noteDao().deleteSingleNote(currentNote);
            dataAdapter.remove(currentAbstractNote);
            message.setText(getString(R.string.Deleted));
            Log.v(TAG, "Note deleted");
        }else{
            message.setText(getString(R.string.empty));
            message.show();
            Log.v(TAG, "Nothing to delete");
        }
    }

    public void onCloseNote(View view) {

        Intent intent = new Intent(DeleteNoteActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
        Log.v(TAG, "closing delete note activity");
    }
}
