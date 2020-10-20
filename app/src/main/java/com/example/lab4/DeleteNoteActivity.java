package com.example.lab4;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class DeleteNoteActivity extends AppCompatActivity {

    public static final String TAG = "Delete note activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.v(TAG, "onCreate");
        super.onCreate(savedInstanceState);

        //myDataBaseHelper.updateNoteDBStructure();


        //adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listItems);
        //listView.setAdapter(allNotes);


    }
}
