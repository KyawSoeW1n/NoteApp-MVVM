package com.kurio.mvvmtesting.ui.viewmodel;


import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.kurio.mvvmtesting.data.model.Note;
import com.kurio.mvvmtesting.db.NoteRepo;
import com.kurio.mvvmtesting.di.AppModule;
import com.kurio.mvvmtesting.di.DaggerAppComponent;
import com.kurio.mvvmtesting.di.RoomModule;

import java.util.List;

import javax.inject.Inject;

public class NoteViewModel extends AndroidViewModel {

    @Inject
    NoteRepo noteRepo;
    public LiveData<List<Note>> allNote;

    @Inject
    public NoteViewModel(@NonNull Application application) {
        super(application);
        DaggerAppComponent.builder()
                .appModule(new AppModule(getApplication()))
                .roomModule(new RoomModule(getApplication()))
                .build()
                .inject(this);
    }

    public LiveData<List<Note>> getAllNotes() {
        return noteRepo.getAll();
    }
    public void saveNote(Note note) {
        noteRepo.saveNote(note);
    }

    public void deleteNote(Note note) {
        noteRepo.deleteNote(note);
    }

    public void updateNote(Note note) {
        noteRepo.updateNote(note);
    }
}
