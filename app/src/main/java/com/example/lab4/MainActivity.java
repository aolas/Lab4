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
import android.widget.TextView;


import java.util.List;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MyActivity";
    ListView listView;
    AbstractNotes absNote;
    Note currentNote;
    EditText updateTitle, updateEditTextNote;
    List<Note> allNotes;
    Intent intent;
    RelativeLayout noteUpdaytLayout, listViewLayout;
    NoteDatabase db;

    //LIST OF ARRAY STRINGS WHICH WILL SERVE AS LIST ITEMS


    //DEFINING A STRING ADAPTER WHICH WILL HANDLE THE DATA OF THE LISTVIEW


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
        allNotes = db.noteDao().getAllNotes();
        List<AbstractNotes> abNotes = db.noteDao().getAllAbstractNotes();

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
    public void onAddNewNote(View view) {
        Log.v(TAG, "onAddNewNote=");

        intent = new Intent(MainActivity.this, AddNoteActivity.class);
        startActivity(intent);
        finish();
    }

    public void onUpdateSaveandClose(View view) {
        Note newNote = new Note(currentNote.getId(),updateTitle.getText().toString(),updateEditTextNote.getText().toString() );
        absNote.setTitle(updateTitle.getText().toString());
        db.noteDao().updateSingleNote(newNote);
        noteUpdaytLayout.setVisibility(View.GONE);
        listViewLayout.setVisibility(View.VISIBLE);
    }

    public void onCloseNoteWithoutUpdate(View view) {

        noteUpdaytLayout.setVisibility(View.GONE);
        listViewLayout.setVisibility(View.VISIBLE);
    }
}