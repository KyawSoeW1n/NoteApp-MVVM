package com.kurio.mvvmtesting.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;

import com.kurio.mvvmtesting.R;

public class AddEditNoteActivity extends AppCompatActivity {
    EditText etTitle, etDescription;
    NumberPicker priorityPicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_note);
        etDescription = findViewById(R.id.etDescription);
        etTitle = findViewById(R.id.etTitle);
        priorityPicker = findViewById(R.id.numberPicker);
        priorityPicker.setMinValue(0);
        priorityPicker.setMaxValue(10);
        if (getIntent().hasExtra("title")) {
            initData();
        }
    }


    private void initData() {
        etTitle.setText(getIntent().getStringExtra("title"));
        etDescription.setText(getIntent().getStringExtra("description"));
        priorityPicker.setValue(getIntent().getIntExtra("priority", 0));
    }

    private void saveNote() {
        Intent i = new Intent();
        i.putExtra("description", etDescription.getText().toString().trim());
        i.putExtra("title", etTitle.getText().toString().trim());
        i.putExtra("priority", priorityPicker.getValue());
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
