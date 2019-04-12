package com.kurio.mvvmtesting.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.kurio.mvvmtesting.data.model.Note;

@Database(entities = {Note.class}, version = NoteRoomDatabase.VERSION,exportSchema = false)
public abstract class NoteRoomDatabase extends RoomDatabase {
    static final int VERSION = 1;

    public abstract NoteDao getNoteDao();
}