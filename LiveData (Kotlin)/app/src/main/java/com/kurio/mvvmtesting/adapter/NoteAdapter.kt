package com.kurio.mvvmtesting.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.kurio.mvvmtesting.R
import com.kurio.mvvmtesting.model.Note

import java.util.ArrayList

class NoteAdapter : RecyclerView.Adapter<NoteAdapter.MyHolder>() {
    private lateinit var v: View
    var allList: List<Note> = ArrayList()
        internal set
    private var mItemClickListener: onRecyclerViewItemClickListener? = null
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): MyHolder {
        v = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.note_row, viewGroup, false)
        return MyHolder(v)
    }

    override fun onBindViewHolder(myHolder: MyHolder, i: Int) {
        val note = allList[i]
        myHolder.tvDescription.text = note.description
        myHolder.tvTitle.text = note.title
        myHolder.tvPriority.text = "" + note.priority
    }

    override fun getItemCount(): Int {
        return allList.size
    }

    inner class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        var tvTitle: TextView
        var tvDescription: TextView
        var tvPriority: TextView

        init {
            tvTitle = itemView.findViewById(R.id.tvTitle)
            tvDescription = itemView.findViewById(R.id.tvDescription)
            tvPriority = itemView.findViewById(R.id.tvPriority)
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View) {
            if (mItemClickListener != null) {
                mItemClickListener!!.onItemClickListener(v, adapterPosition)
            }
        }
    }

    fun setNoteList(noteList: List<Note>) {
        this.allList = noteList
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(mItemClickListener: onRecyclerViewItemClickListener) {
        this.mItemClickListener = mItemClickListener
    }

    interface onRecyclerViewItemClickListener {
        fun onItemClickListener(view: View, position: Int)
    }
}
