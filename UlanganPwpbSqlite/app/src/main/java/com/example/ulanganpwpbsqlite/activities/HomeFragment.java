package com.example.ulanganpwpbsqlite.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.ulanganpwpbsqlite.R;
import com.example.ulanganpwpbsqlite.adapters.NoteAdapter;
import com.example.ulanganpwpbsqlite.database.DatabaseHelper;
import com.example.ulanganpwpbsqlite.models.Note;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment implements
        View.OnClickListener, NoteAdapter.onUserClickListener {

    private RecyclerView recyclerView;
    private EditText editTitle, editBody;
    private FloatingActionButton btnAdd;
    private RecyclerView.LayoutManager layoutManager;
    private Context context;
    private List<Note> noteList;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.context = this.getActivity();
        this.recyclerView = view.findViewById(R.id.noteListRecyclerView);
        this.layoutManager = new LinearLayoutManager(this.context);
        this.recyclerView.setLayoutManager(this.layoutManager);

        this.editTitle = view.findViewById(R.id.titleTxt);
        this.editBody = view.findViewById(R.id.bodyTxt);
        this.btnAdd = view.findViewById(R.id.fabAddNote);

        this.btnAdd.setOnClickListener(this);
        this.setupRecyclerView();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fabAddNote:
                Intent intent = new Intent(getActivity(), AddNoteActivity.class);
                startActivity(intent);
        }
    }

    @Override
    public void onUserClick(Note note, String action) {

    }

    private void setupRecyclerView() {
        DatabaseHelper db = new DatabaseHelper(this.context);
        this.noteList = db.getAllNotes();

        NoteAdapter adapter = new NoteAdapter(this.context, this.noteList, this);
        this.recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}