package com.kurio.mvvmtesting.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kurio.mvvmtesting.R;
import com.kurio.mvvmtesting.data.model.Note;

import java.util.ArrayList;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.MyHolder> {
    View v;
    List<Note> allList=new ArrayList<>();
    private onRecyclerViewItemClickListener mItemClickListener;
    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.note_row, viewGroup, false);
        return new MyHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, int i) {
        Note note = allList.get(i);
        myHolder.tvDescription.setText(note.getDescription());
        myHolder.tvTitle.setText(note.getTitle());
        myHolder.tvPriority.setText("" + note.getPriority());
    }

    @Override
    public int getItemCount() {
        return allList.size();
    }

     class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvTitle, tvDescription, tvPriority;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle=itemView.findViewById(R.id.tvTitle);
            tvDescription=itemView.findViewById(R.id.tvDescription);
            tvPriority=itemView.findViewById(R.id.tvPriority);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mItemClickListener != null) {
                mItemClickListener.onItemClickListener(v, getAdapterPosition());
            }
        }
    }

    public void setNoteList(List<Note> noteList){
        this.allList=noteList;
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
