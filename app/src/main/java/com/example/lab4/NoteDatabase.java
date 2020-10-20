package com.example.lab4;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Note.class}, version = 1)
public abstract class NoteDatabase extends RoomDatabase {
    public abstract NoteDao noteDao();

    private static NoteDatabase instance;

    //Synchronize for singleton pattern
    public static synchronized NoteDatabase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context,NoteDatabase.class, "note_db")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .addCallback(initialCallback)
            .build()       ;
        }
        return instance;
    }
    private static RoomDatabase.Callback initialCallback= new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateInitialData(instance).execute();
        }
    };
    private static class PopulateInitialData extends AsyncTask<Void, Void, Void> {
        private NoteDao noteDao;

        public PopulateInitialData(NoteDatabase db) {
            this.noteDao = db.noteDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.insertSingleNote(new Note("Melisa note","contact phone +37061899456"));
            noteDao.insertSingleNote(new Note("Brad note","We can make a deal"));
            noteDao.insertSingleNote(new Note("Tom note","We have his email: tom@gmail.com"));
            return null;
        }
    }
}
