package com.kurio.mvvmtesting.db

import android.app.Application
import android.arch.lifecycle.LiveData
import android.os.AsyncTask

import com.kurio.mvvmtesting.model.Note

class NoteRepo(application: Application) {
    private val noteDao: NoteDao = NoteRoomDatabase.getInstance(application).noteDao()
    val allNotes: LiveData<List<Note>>

    init {
        allNotes = noteDao.allNote
    }

    fun insertNote(note: Note) {
        InsertAsyncTask(noteDao).execute(note)
    }

    fun updateNote(note: Note) {
        UpdateAsyncTask(noteDao).execute(note)
    }

    fun deleteNote(note: Note) {
        DeleteAsyncTask(noteDao).execute(note)
    }

    private class InsertAsyncTask internal constructor(private val noteDao: NoteDao) : AsyncTask<Note, Void, Void>() {

        override fun onPreExecute() {
            super.onPreExecute()
        }

        override fun doInBackground(vararg notes: Note): Void? {
            noteDao.insert(notes[0])
            return null
        }

        override fun onPostExecute(aVoid: Void?) {
            super.onPostExecute(aVoid)
        }
    }

    private class UpdateAsyncTask internal constructor(private val noteDao: NoteDao) : AsyncTask<Note, Void, Void>() {

        override fun onPreExecute() {
            super.onPreExecute()
        }

        override fun doInBackground(vararg notes: Note): Void? {
            noteDao.update(notes[0])
            return null
        }

        override fun onPostExecute(aVoid: Void?) {
            super.onPostExecute(aVoid)
        }
    }

    private class DeleteAsyncTask internal constructor(private val noteDao: NoteDao) : AsyncTask<Note, Void, Void>() {

        override fun onPreExecute() {
            super.onPreExecute()
        }

        override fun doInBackground(vararg notes: Note): Void? {
            noteDao.delete(notes[0])
            return null
        }

        override fun onPostExecute(aVoid: Void?) {
            super.onPostExecute(aVoid)
        }
    }

}
