package com.kurio.mvvmtesting.db;

import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.kurio.mvvmtesting.data.model.Note;

import java.util.List;

import javax.inject.Inject;


public class NoteDateSource implements NoteRepo {
    private NoteDao noteDao;

    @Inject
    public NoteDateSource(NoteDao noteDao) {
        this.noteDao = noteDao;
    }

    @Override
    public LiveData<List<Note>> getAll() {
        return noteDao.getAllNote();
    }

    @Override
    public void updateNote(Note note) {
        new UpdateAsyncTask(noteDao).execute(note);
//        noteDao.update(note);
    }

    @Override
    public void saveNote(Note note) {
        new InsertAsyncTask(noteDao).execute(note);
    }

    private static class InsertAsyncTask extends AsyncTask<Note, Void, Void> {
        private NoteDao noteDao;

        InsertAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.save(notes[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }

    private static class UpdateAsyncTask extends AsyncTask<Note, Void, Void> {
        private NoteDao noteDao;

        UpdateAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.update(notes[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }

    private static class DeleteAsyncTask extends AsyncTask<Note, Void, Void> {
        private NoteDao noteDao;

        DeleteAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.delete(notes[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }

    @Override
    public void deleteNote(Note note) {
        new DeleteAsyncTask(noteDao).execute(note);
    }


}
