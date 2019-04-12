package com.kurio.mvvmtesting.di;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.kurio.mvvmtesting.db.NoteDao;
import com.kurio.mvvmtesting.db.NoteDateSource;
import com.kurio.mvvmtesting.db.NoteRepo;
import com.kurio.mvvmtesting.db.NoteRoomDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RoomModule {
    private NoteRoomDatabase noteRoomDatabase;

    public RoomModule(Context context) {
        noteRoomDatabase = Room.databaseBuilder(context, NoteRoomDatabase.class, "note_database").build();
    }

    @Singleton
    @Provides
    NoteRoomDatabase providesRoomDatabase() {
        return noteRoomDatabase;
    }


    @Singleton
    @Provides
    NoteDao providesNoteDao(NoteRoomDatabase noteRoomDatabase) {
        return noteRoomDatabase.getNoteDao();
    }

    @Singleton
    @Provides
    NoteRepo productRepository(NoteDao noteDao) {
        return new NoteDateSource(noteDao);
    }


}
