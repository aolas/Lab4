package com.example.lab4;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface NoteDao {
    @Insert
    void insertSingleNote(Note note);

    @Delete
    void deleteSingleNote(Note note);

    @Update
    void updateSingleNote(Note note);

    @Query("SELECT * FROM note")
    List<Note> getAllNotes();
}
