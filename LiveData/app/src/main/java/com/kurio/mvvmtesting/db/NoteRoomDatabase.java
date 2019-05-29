package com.kurio.mvvmtesting.db;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.kurio.mvvmtesting.model.Note;

@Database(entities = {Note.class}, version = 1, exportSchema = false)
public abstract class NoteRoomDatabase extends RoomDatabase {
    public abstract NoteDao noteDao();

    private static NoteRoomDatabase instance;

    public static NoteRoomDatabase getInstance(Context mCtx) {

        if (instance == null) {
            instance = Room.databaseBuilder(mCtx, NoteRoomDatabase.class, "note_database")
                    .addCallback(roomDb)
                    .build();
        }
        return instance;
    }


    static RoomDatabase.Callback roomDb = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
//            new PopulateData(instance).execute();
        }
    };

    private static class PopulateData extends AsyncTask<Void, Void, Void> {
        NoteRoomDatabase noteRoomDatabase;

        PopulateData(NoteRoomDatabase noteRoomDatabase) {
            this.noteRoomDatabase = noteRoomDatabase;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            for (int i = 0; i < 5; i++) {
                Note note = new Note("Title", "Description", 1);
                noteRoomDatabase.noteDao().insert(note);
            }

            return null;
        }
    }
}
