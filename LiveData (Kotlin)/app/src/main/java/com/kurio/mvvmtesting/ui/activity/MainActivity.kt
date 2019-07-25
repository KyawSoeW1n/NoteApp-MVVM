package com.kurio.mvvmtesting.ui.activity

import android.app.Activity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.kurio.mvvmtesting.Constants
import com.kurio.mvvmtesting.R
import com.kurio.mvvmtesting.adapter.NoteAdapter
import com.kurio.mvvmtesting.model.Note
import com.kurio.mvvmtesting.ui.viewmodel.NoteViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    var noteViewModel: NoteViewModel? = null
    internal lateinit var noteAdapter: NoteAdapter
    private lateinit var layoutManager: RecyclerView.LayoutManager
    internal var tempNote: Note? = null
    internal var notesList: List<Note>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initUIComponents()
        setSupportActionBar(toolbar)
        noteViewModel!!.allNote.observe(this, Observer { notes ->
            notesList = notes
            noteAdapter.setNoteList(notes!!)
        })

    }

    private fun initUIComponents() {
        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel::class.java)
        noteAdapter = NoteAdapter()
        layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rc.adapter = noteAdapter
        rc.layoutManager = layoutManager
        fab.setOnClickListener(this)
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, viewHolder1: RecyclerView.ViewHolder): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, i: Int) {
                val note = noteAdapter.allList[viewHolder.adapterPosition]
                tempNote = note
                noteViewModel!!.deleteNote(note)
                val snackbar = Snackbar
                        .make(coordinatorLayout, "Deleted", Snackbar.LENGTH_LONG)
                        .setAction("Undo") {
                            noteViewModel!!.insertNote(tempNote!!)
                            tempNote = null
                        }
                snackbar.show()
            }
        }).attachToRecyclerView(rc)
        noteAdapter.setOnItemClickListener(object : NoteAdapter.onRecyclerViewItemClickListener {
            override fun onItemClickListener(view: View, position: Int) {
                val i = Intent(this@MainActivity, AddEditNoteActivity::class.java)
                i.putExtra(Constants.EXTRA_DESCRIPTION, notesList!![position].description)
                i.putExtra(Constants.EXTRA_TITLE, notesList!![position].title)
                i.putExtra(Constants.EXTRA_PRIORITY, notesList!![position].priority)
                i.putExtra(Constants.EXTRA_ID, notesList!![position].id)
                startActivityForResult(i, 2)
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId


        return if (id == R.id.action_settings) {
            true
        } else super.onOptionsItemSelected(item)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == Activity.RESULT_OK && data != null) {
            val note = Note(data.getStringExtra(Constants.EXTRA_TITLE),
                    data.getStringExtra(Constants.EXTRA_DESCRIPTION),
                    data.getIntExtra(Constants.EXTRA_PRIORITY, 0))
            noteViewModel!!.insertNote(note)
        }

        if (requestCode == 2 && resultCode == Activity.RESULT_OK && data != null) {
            val note = Note(data.getStringExtra(Constants.EXTRA_TITLE),
                    data.getStringExtra(Constants.EXTRA_DESCRIPTION),
                    data.getIntExtra(Constants.EXTRA_PRIORITY, 0))
            note.id = data.getIntExtra(Constants.EXTRA_ID, -1)
            noteViewModel!!.updateNote(note)
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.fab -> {
                val intent = Intent(this@MainActivity, AddEditNoteActivity::class.java)

                startActivityForResult(intent, 1)
            }
        }
    }


}
