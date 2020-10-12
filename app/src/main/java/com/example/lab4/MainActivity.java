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
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MyActivity";
    ListView listView;
    EditText textNote;
    ArrayAdapter<String> adapter;
    RelativeLayout noteEditLayout, mainNoteLayout;
    private DatabaseHelper myDataBaseHelper;
    ArrayList<Note> allNotes = new ArrayList<>();

    //LIST OF ARRAY STRINGS WHICH WILL SERVE AS LIST ITEMS
    ArrayList<String> listItems=new ArrayList<String>();
    //DEFINING A STRING ADAPTER WHICH WILL HANDLE THE DATA OF THE LISTVIEW

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.v(TAG, "onCreate=");
        super.onCreate(savedInstanceState);
        myDataBaseHelper = new DatabaseHelper(this);
        try{
            SQLiteDatabase database = myDataBaseHelper.getWritableDatabase();
            Cursor cursor = database.query("notes", null, null , null,null,null,null );
            if (cursor != null){
                if(cursor.moveToFirst()){
                    int cursorCount = cursor.getCount();
                    for (int i = 0; i<cursor.getCount(); i++){
                        Note noteData = new Note();
                        int id = cursor.getInt(cursor.getColumnIndex("id"));
                        String dataText = cursor.getString(cursor.getColumnIndex("note_text"));
                        noteData.setId(id);
                        noteData.setNoteText(dataText);
                        Log.v(TAG,"got data: " + dataText);
                        allNotes.add(noteData);
                        listItems.add(dataText);
                        cursor.moveToNext();
                    }

                }else{
                    cursor.close();
                    database.close();
                }
            }
            else{
                database.close();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }


        setContentView(R.layout.activity_main);
        noteEditLayout = findViewById(R.id.noteEditLayout);
        mainNoteLayout = findViewById(R.id.mainNoteLayout);
        listView = findViewById(R.id.noteListView);
        textNote = findViewById(R.id.editTextNote);
        adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listItems);
        listView.setAdapter(adapter);



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // TODO Auto-generated method stub
                String value=adapter.getItem(position);
                Log.v(TAG, "onItemClick=" + value + " position: " + String.valueOf(position));

            }
        });


    }
    public void onAddNewNote(View view) {
        Log.v(TAG, "onAddNewNote=");
        noteEditLayout.setVisibility(View.VISIBLE);
        mainNoteLayout.setVisibility(view.INVISIBLE);
    }
    public void onCloseNote(View view) {
        String note = textNote.getText().toString();
        adapter.add(note);
        adapter.notifyDataSetChanged();
        Log.v(TAG, String.valueOf(adapter.getCount()));
        Log.v(TAG, note);
        textNote.setText("");
        noteEditLayout.setVisibility(View.INVISIBLE);
        mainNoteLayout.setVisibility(view.VISIBLE);
    }
}