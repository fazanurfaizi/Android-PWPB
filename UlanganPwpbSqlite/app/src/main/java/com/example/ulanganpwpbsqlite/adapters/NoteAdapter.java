package com.example.ulanganpwpbsqlite.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ulanganpwpbsqlite.R;
import com.example.ulanganpwpbsqlite.activities.AddNoteActivity;
import com.example.ulanganpwpbsqlite.models.Note;

import java.util.ArrayList;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {

    private Context context;
    private onUserClickListener listener;
    private List<Note> noteList = new ArrayList<>();

    public NoteAdapter(Context context, List<Note> noteList, onUserClickListener listener) {
        this.context = context;
        this.listener = listener;
        this.noteList = noteList;
    }

    public NoteAdapter(Context context, List<Note> noteList) {
        this.context = context;
        this.noteList = noteList;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.note_item, parent, false);
        NoteViewHolder noteViewHolder = new NoteViewHolder(view);
        return noteViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        holder.title.setText(noteList.get(position).getTitle());
        holder.body.setText(noteList.get(position).getBody());
        holder.createdAt.setText(noteList.get(position).getCreatedAt());

        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AddNoteActivity.class);
                intent.putExtra(AddNoteActivity.EXTRA_ID, noteList.get(position).getId());
                intent.putExtra(AddNoteActivity.EXTRA_TITLE, noteList.get(position).getTitle());
                intent.putExtra(AddNoteActivity.EXTRA_BODY, noteList.get(position).getBody());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.noteList.size();
    }

    public interface onUserClickListener {
        void onUserClick(Note note, String action);
    }

    public class NoteViewHolder extends RecyclerView.ViewHolder {

        TextView title, body, createdAt;
        ConstraintLayout constraintLayout;

        public NoteViewHolder(@NonNull View view) {
            super(view);

            title = view.findViewById(R.id.noteTitle);
            body = view.findViewById(R.id.noteBody);
            createdAt = view.findViewById(R.id.noteDate);
            constraintLayout = view.findViewById(R.id.noteItem);
        }
    }

}
