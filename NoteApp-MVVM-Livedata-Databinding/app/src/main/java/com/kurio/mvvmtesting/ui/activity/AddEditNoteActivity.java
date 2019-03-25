package com.kurio.mvvmtesting.ui.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.kurio.mvvmtesting.R;
import com.kurio.mvvmtesting.databinding.ActivityAddEditNoteBinding;
import com.kurio.mvvmtesting.model.Note;

public class AddEditNoteActivity extends AppCompatActivity {
    ActivityAddEditNoteBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_edit_note);
        if (getIntent().hasExtra("title")) {
            Log.i("Come", "??");
            initData();
        }
        binding.numberPicker.setMinValue(0);
        binding.numberPicker.setMaxValue(10);
    }

    public void initData() {
        Note note = new Note(getIntent().getStringExtra("title"),
                getIntent().getStringExtra("description"),
                getIntent().getIntExtra("priority", 0));
        binding.setAddedit(note);
    }

    private void saveNote() {
        Intent i = new Intent();
        i.putExtra("description", binding.etDescription.getText().toString().trim());
        i.putExtra("title", binding.etTitle.getText().toString().trim());
        i.putExtra("priority", binding.numberPicker.getValue());
        if (getIntent().hasExtra("id"))
            i.putExtra("id", getIntent().getIntExtra("id", -1));
        setResult(RESULT_OK, i);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_note_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.save_note) {
            saveNote();
            return true;
        }
        return super.onOptionsItemSelected(item);

    }
}
