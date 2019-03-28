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

    public ListNotesAdapter(Context mContext, ArrayList<Note> mNotes) {
        this.mNotes = mNotes;
        this.mContext = mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_note, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
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

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView text;
        RelativeLayout noteLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.noteTitleView);
            text = itemView.findViewById(R.id.noteTextView);
            noteLayout = itemView.findViewById(R.id.noteLayout);
        }
    }

    private void viewGoneIfStringEmpty(String string, View view) {
        if(string.isEmpty()) {
            view.setVisibility(View.GONE);
        }
    }
}
