package com.example.lab4;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MyActivity";
    ListView listView;
    EditText textNote;
    ArrayAdapter<String> adapter;
    RelativeLayout noteEditLayout, mainNoteLayout;
    ListAdapter allNotes;

    //LIST OF ARRAY STRINGS WHICH WILL SERVE AS LIST ITEMS
    ArrayList<String> listItems=new ArrayList<String>();
    //DEFINING A STRING ADAPTER WHICH WILL HANDLE THE DATA OF THE LISTVIEW

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.v(TAG, "onCreate=");
        super.onCreate(savedInstanceState);

        //myDataBaseHelper.updateNoteDBStructure();

        setContentView(R.layout.activity_main);
        noteEditLayout = findViewById(R.id.noteEditLayout);
        mainNoteLayout = findViewById(R.id.mainNoteLayout);
        listView = findViewById(R.id.noteListView);
        textNote = findViewById(R.id.editTextNote);

        NoteDatabase db = NoteDatabase.getInstance(this);
        allNotes = (ListAdapter) db.noteDao().getAllNotes();


        //adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listItems);
        listView.setAdapter(allNotes);



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // TODO Auto-generated method stub
                String value = allNotes.getItem(position).toString();
                Log.v(TAG, "Value= " + value + " position: " + String.valueOf(position));

            }
        });


    }
    public void onAddNewNote(View view) {
        Log.v(TAG, "onAddNewNote=");

        noteEditLayout.setVisibility(View.VISIBLE);
        mainNoteLayout.setVisibility(View.INVISIBLE);
    }
    public void onCloseNote(View view) {
        String note = textNote.getText().toString();
        //adapter.add(note);
        //adapter.notifyDataSetChanged();
        Log.v(TAG, String.valueOf(adapter.getCount()));
        Log.v(TAG, note);
        textNote.setText("");
        noteEditLayout.setVisibility(View.INVISIBLE);
        mainNoteLayout.setVisibility(View.VISIBLE);
    }
}