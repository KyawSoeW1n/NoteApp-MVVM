package com.kurio.mvvmtesting.ui.viewmodel


import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData

import com.kurio.mvvmtesting.db.NoteRepo
import com.kurio.mvvmtesting.model.Note

class NoteViewModel(application: Application) : AndroidViewModel(application) {
    private val noteRepo: NoteRepo = NoteRepo(application)
    var allNote: LiveData<List<Note>>

    init {
        allNote = noteRepo.allNotes
    }

    fun insertNote(note: Note) {
        noteRepo.insertNote(note)
    }

    fun deleteNote(note: Note) {
        noteRepo.deleteNote(note)
    }

    fun updateNote(note: Note) {
        noteRepo.updateNote(note)
    }
}
