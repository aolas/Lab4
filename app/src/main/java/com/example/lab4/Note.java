package com.example.lab4;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Note {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private String noteText;

    public Note(String title, String noteText) {
        this.title = title;
        this.noteText = noteText;
    }

    @Ignore
    public Note(int id, String title, String noteText) {
        this.id = id;
        this.title = title;
        this.noteText = noteText;
    }
    @Ignore
    public Note(int id) {
        this.id = id;
    }




    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getNoteText() {
        return noteText;
    }
}
