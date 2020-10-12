package com.example.lab4;

public class Note {
    private int id;
    private String noteText;

    public Note(int id, String noteText) {
        this.id = id;
        this.noteText = noteText;
    }

    public Note() {
    }

    public int getId() {
        return id;
    }

    public String getNoteText() {
        return noteText;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNoteText(String noteText) {
        this.noteText = noteText;
    }
}
