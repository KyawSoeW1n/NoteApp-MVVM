package com.kurio.mvvmtesting.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kurio.mvvmtesting.BR;
import com.kurio.mvvmtesting.CustomClickListener;
import com.kurio.mvvmtesting.R;
import com.kurio.mvvmtesting.databinding.NoteRowBinding;
import com.kurio.mvvmtesting.model.Note;

import java.util.ArrayList;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.MyHolder> {
    List<Note> allList = new ArrayList<>();
    private onRecyclerViewItemClickListener mItemClickListener;

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        NoteRowBinding noteRowBinding = DataBindingUtil.inflate(
                LayoutInflater.from(viewGroup.getContext()),
                R.layout.note_row, viewGroup, false);
        return new MyHolder(noteRowBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, int i) {
        Note note = allList.get(i);
        myHolder.noteRowBinding.setNotemodel(note);
        myHolder.bind(note);
//        myHolder.noteRowBinding.setItemClickListener(this);
    }

    @Override
    public int getItemCount() {
        return allList.size();
    }

    class MyHolder extends RecyclerView.ViewHolder implements CustomClickListener {
        //        TextView tvTitle, tvDescription, tvPriority;
        NoteRowBinding noteRowBinding;

        public MyHolder(@NonNull NoteRowBinding binding) {
            super(binding.getRoot());
            this.noteRowBinding = binding;
            noteRowBinding.setItemClickListener(this);
        }

        public void bind(Object obj) {
            noteRowBinding.setVariable(BR.notemodel, obj);
            noteRowBinding.executePendingBindings();
        }
//
//        @Override
//        public void onClick(View v) {
//
//        }

        @Override
        public void cardClicked(Note note) {
            if (mItemClickListener != null) {
                mItemClickListener.onItemClickListener(noteRowBinding.getRoot(), getAdapterPosition());
            }
        }
    }

    public void setNoteList(List<Note> noteList) {
        this.allList = noteList;
        notifyDataSetChanged();
    }

    public List<Note> getAllList() {
        return allList;
    }

    public void setOnItemClickListener(onRecyclerViewItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    public interface onRecyclerViewItemClickListener {
        void onItemClickListener(View view, int position);
    }
}
