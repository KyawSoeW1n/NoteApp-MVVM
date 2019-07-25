package com.kurio.mvvmtesting.db

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import android.os.AsyncTask
import com.kurio.mvvmtesting.Constants

import com.kurio.mvvmtesting.model.Note

@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class NoteRoomDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao

//    private class PopulateData internal constructor(internal var noteRoomDatabase: NoteRoomDatabase) : AsyncTask<Void, Void, Void>() {
//
//        override fun doInBackground(vararg voids: Void): Void? {
//            for (i in 0..4) {
//                val note = Note("Title", "Description", 1)
//                noteRoomDatabase.noteDao().insert(note)
//            }
//
//            return null
//        }
//    }

    companion object {

        private var instance: NoteRoomDatabase? = null

        fun getInstance(mCtx: Context): NoteRoomDatabase {

            if (instance == null) {
                instance = Room.databaseBuilder<NoteRoomDatabase>(mCtx, NoteRoomDatabase::class.java, Constants.DB_NAME)
                        .addCallback(roomDb)
                        .build()
            }
            return instance as NoteRoomDatabase
        }


        internal var roomDb: RoomDatabase.Callback = object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                //            new PopulateData(instance).execute();
            }
        }
    }
}
