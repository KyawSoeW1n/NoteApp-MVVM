package com.kurio.mvvmtesting.viewmodel;


import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.kurio.mvvmtesting.db.NoteRepo;
import com.kurio.mvvmtesting.model.Note;

import java.util.List;

public class NoteViewModel extends AndroidViewModel {
    NoteRepo noteRepo;
   public LiveData<List<Note>> allNote;

    public NoteViewModel(@NonNull Application application) {
        super(application);
        this.noteRepo = new NoteRepo(application);
        allNote = noteRepo.getAllNotes();
    }

    public void insertNote(Note note) {
        noteRepo.insertNote(note);
    }

    public void deleteNote(Note note) {
        noteRepo.deleteNote(note);
    }

    public void updateNote(Note note) {
        noteRepo.updateNote(note);
    }
}
