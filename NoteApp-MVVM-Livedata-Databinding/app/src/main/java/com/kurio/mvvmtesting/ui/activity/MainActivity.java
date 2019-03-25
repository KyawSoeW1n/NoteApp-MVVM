package com.kurio.mvvmtesting.ui.activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.kurio.mvvmtesting.R;
import com.kurio.mvvmtesting.adapter.NoteAdapter;
import com.kurio.mvvmtesting.databinding.ActivityMainBinding;
import com.kurio.mvvmtesting.databinding.ContentMainBinding;
import com.kurio.mvvmtesting.model.Note;
import com.kurio.mvvmtesting.viewmodel.NoteViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    NoteViewModel noteViewModel;
    NoteAdapter noteAdapter;
    RecyclerView.LayoutManager layoutManager;
    Note tempNote;
    List<Note> notesList;
    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        initUIComponents();
        setSupportActionBar(binding.toolbar);
        noteViewModel.allNote.observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(@Nullable List<Note> notes) {
                notesList = notes;
                noteAdapter.setNoteList(notes);
            }
        });
    }

    private void initUIComponents() {
        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);
        noteAdapter = new NoteAdapter();
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        binding.contentMain.rc.setLayoutManager(layoutManager);
        binding.contentMain.rc.setAdapter(noteAdapter);
        binding.fab.setOnClickListener(this);
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                Note note = noteAdapter.getAllList().get(viewHolder.getAdapterPosition());
                tempNote = note;
                noteViewModel.deleteNote(note);
                Snackbar snackbar = Snackbar
                        .make(binding.coordinatorLayout, "Deleted", Snackbar.LENGTH_LONG)
                        .setAction("Undo", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                noteViewModel.insertNote(tempNote);
                                tempNote = null;
                            }
                        });
                snackbar.show();
            }
        }).attachToRecyclerView(binding.contentMain.rc);
        noteAdapter.setOnItemClickListener(new NoteAdapter.onRecyclerViewItemClickListener() {
            @Override
            public void onItemClickListener(View view, int position) {
                Intent i = new Intent(MainActivity.this, AddEditNoteActivity.class);
                i.putExtra("description", notesList.get(position).getDescription());
                i.putExtra("title", notesList.get(position).getTitle());
                i.putExtra("priority", notesList.get(position).getPriority());
                i.putExtra("id", notesList.get(position).getId());
                startActivityForResult(i, 2);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i("REQUEST CODE",""+requestCode);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            Note note = new Note(data.getStringExtra("title"),
                    data.getStringExtra("description"),
                    data.getIntExtra("priority", 0));
            noteViewModel.insertNote(note);
        }

        if (requestCode == 2 && resultCode == RESULT_OK && data != null) {
            Note note = new Note(data.getStringExtra("title"),
                    data.getStringExtra("description"),
                    data.getIntExtra("priority", 0));
            note.setId(data.getIntExtra("id", -1));
            noteViewModel.updateNote(note);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab:
                startActivityForResult(new Intent(MainActivity.this, AddEditNoteActivity.class),
                        1);
                break;
        }
    }
}
