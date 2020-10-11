package com.example.lab4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MyActivity";
    ListView listView;
    String[] listItem;
    EditText noteEditBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.v(TAG, "onCreate=");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        noteEditBox = findViewById(R.id.editTextNote);
        listView=(ListView)findViewById(R.id.listView);
        listItem = getResources().getStringArray(R.array.array_technology);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, listItem);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // TODO Auto-generated method stub
                String value=adapter.getItem(position);
                Log.v(TAG, "onItemClick=" + value);

            }
        });
    }
    public void onAddNewNote(View view) {
        Log.v(TAG, "onAddNewNote=");
        noteEditBox.setVisibility(View.VISIBLE);

    }

}