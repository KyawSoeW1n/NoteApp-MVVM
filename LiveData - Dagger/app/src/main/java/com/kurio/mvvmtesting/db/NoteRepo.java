package com.kurio.mvvmtesting.db;

import android.arch.lifecycle.LiveData;

import com.kurio.mvvmtesting.data.model.Note;

import java.util.List;

public interface NoteRepo {
    LiveData<List<Note>> getAll();

    void updateNote(Note note);

    void saveNote(Note note);

    void deleteNote(Note note);
}
