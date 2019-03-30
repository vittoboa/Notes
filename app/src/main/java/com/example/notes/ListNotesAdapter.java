package com.example.notes;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class ListNotesAdapter extends RecyclerView.Adapter<ListNotesAdapter.ViewHolder> {
    private ArrayList<Note> mNotes = new ArrayList<>();
    private Context mContext;
    private OnNoteListener mOnNoteListener;

    public ListNotesAdapter(Context mContext, ArrayList<Note> mNotes, OnNoteListener onNoteListener) {
        this.mNotes = mNotes;
        this.mContext = mContext;
        this.mOnNoteListener = onNoteListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_note, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view, mOnNoteListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        String noteTitle = mNotes.get(i).getTitle();
        String noteText = mNotes.get(i).getText();

        viewHolder.title.setText(noteTitle);
        viewHolder.text.setText(noteText);

        viewGoneIfStringEmpty(noteTitle, viewHolder.title);
        viewGoneIfStringEmpty(noteText, viewHolder.text);
    }

    @Override
    public int getItemCount() {
        return mNotes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        TextView title;
        TextView text;
        RelativeLayout noteLayout;
        OnNoteListener onNoteListener;

        public ViewHolder(@NonNull View itemView, OnNoteListener onNoteListener) {
            super(itemView);
            title = itemView.findViewById(R.id.noteTitleView);
            text = itemView.findViewById(R.id.noteTextView);
            noteLayout = itemView.findViewById(R.id.noteLayout);
            this.onNoteListener = onNoteListener;

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onNoteListener.onNoteClick(getAdapterPosition());
        }

        @Override
        public boolean onLongClick(View v) {
            onNoteListener.onNoteLongClick(getAdapterPosition());
            return true;
        }
    }

    public interface OnNoteListener {
        void onNoteClick(int position);
        void onNoteLongClick(int position);
    }

    private void viewGoneIfStringEmpty(String string, View view) {
        if(string.isEmpty()) {
            view.setVisibility(View.GONE);
        }
    }
}
