package com.example.lab4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;


import java.util.List;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MyActivity";
    ListView listView;
    AbstractNotes absNote;
    Note currentNote;
    EditText updateTitle, updateEditTextNote;
    Toast message;
    RelativeLayout noteUpdaytLayout, listViewLayout;
    NoteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.v(TAG, "onCreate=");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        noteUpdaytLayout = findViewById(R.id.noteUpdaytLayout);
        listViewLayout = findViewById(R.id.listViewLayout);
        listView = findViewById(R.id.noteListView);
        updateTitle = findViewById(R.id.updateTitle);
        updateEditTextNote = findViewById(R.id.updateEditTextNote);

        db= NoteDatabase.getInstance(this);
        List<AbstractNotes> abNotes = db.noteDao().getAllAbstractNotes();
        message =Toast.makeText(getApplicationContext(),R.string.empty,Toast.LENGTH_SHORT);
        ArrayAdapter<AbstractNotes> dataAdapter = new ArrayAdapter<AbstractNotes>(this, android.R.layout.simple_list_item_1, abNotes);
        listView.setAdapter(dataAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                absNote = (AbstractNotes) adapterView.getItemAtPosition(position);
                Log.v(TAG, "Value= " + absNote.getTitle() + " position: " + String.valueOf(position));
                currentNote = db.noteDao().getNote(absNote.getId());
                if (currentNote == null){
                    Log.v(TAG,"query empty");
                } else
                {
                    updateTitle.setText(currentNote.getTitle());
                    updateEditTextNote.setText(currentNote.getNoteText());
                }
                listViewLayout.setVisibility(View.GONE);
                noteUpdaytLayout.setVisibility(View.VISIBLE);

            }
        });
    }
    public void onDeleteNoteActivity(View view){
        Log.v(TAG, "on Delete Note");
        Intent intent = new Intent(MainActivity.this, DeleteNoteActivity.class);
        startActivity(intent);
        finish();
    }
    public void onAddNewNote(View view) {
        Log.v(TAG, "on Add New Note");

        Intent intent = new Intent(MainActivity.this, AddNoteActivity.class);
        startActivity(intent);
        finish();
    }

    public void onUpdateSaveandClose(View view) {
        String title, note;
        title = updateTitle.getText().toString();
        note = updateEditTextNote.getText().toString();
        if (!title.matches("") && !note.matches("")){
            Note newNote = new Note(currentNote.getId(),title, note);
            absNote.setTitle(updateTitle.getText().toString());
            db.noteDao().updateSingleNote(newNote);
            noteUpdaytLayout.setVisibility(View.GONE);
            listViewLayout.setVisibility(View.VISIBLE);
        }else{
            message.setText(getString(R.string.emptyText));
            message.show();
            Log.v(TAG, "Can't be Empty text");
        }


    }

    public void onCloseNoteWithoutUpdate(View view) {
        Log.v(TAG, "On note close without update");
        noteUpdaytLayout.setVisibility(View.GONE);
        listViewLayout.setVisibility(View.VISIBLE);
    }
}