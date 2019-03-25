package com.kurio.mvvmtesting.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.kurio.mvvmtesting.model.Note;

import java.util.List;

@Dao
interface NoteDao {
    @Insert
    void insert(Note note);

    @Update
    void update(Note note);

    @Delete
    void delete(Note note);

    @Query("select * from note_table")
    LiveData<List<Note>> getAllNote();
}
