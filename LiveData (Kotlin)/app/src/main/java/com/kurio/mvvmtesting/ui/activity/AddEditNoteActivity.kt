package com.kurio.mvvmtesting.ui.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.kurio.mvvmtesting.Constants
import com.kurio.mvvmtesting.R
import kotlinx.android.synthetic.main.activity_add_edit_note.*

class AddEditNoteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_edit_note)
        numberPicker.minValue = 0
        numberPicker.maxValue = 10
        if (intent.hasExtra(Constants.EXTRA_TITLE)) {
            initData()
        }
    }


    private fun initData() {
        etTitle.setText(intent.getStringExtra(Constants.EXTRA_TITLE))
        etDescription.setText(intent.getStringExtra(Constants.EXTRA_DESCRIPTION))
        numberPicker.value = intent.getIntExtra(Constants.EXTRA_PRIORITY, 0)
    }

    private fun saveNote() {
        val i = Intent()
        i.putExtra(Constants.EXTRA_DESCRIPTION, etDescription.text.toString())
        i.putExtra(Constants.EXTRA_TITLE, etTitle.text.toString())
        i.putExtra(Constants.EXTRA_PRIORITY, numberPicker.value)
        if (intent.hasExtra(Constants.EXTRA_ID))
            i.putExtra(Constants.EXTRA_ID, intent.getIntExtra(Constants.EXTRA_ID, -1))
        setResult(Activity.RESULT_OK, i)
        finish()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.add_note_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.save_note) {
            saveNote()
            return true
        }
        return super.onOptionsItemSelected(item)

    }
}
