package com.example.ulanganpwpbsqlite.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.ulanganpwpbsqlite.R;
import com.example.ulanganpwpbsqlite.adapters.NoteAdapter;
import com.example.ulanganpwpbsqlite.database.DatabaseHelper;
import com.example.ulanganpwpbsqlite.models.Note;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class HomeActivity extends AppCompatActivity {

//    private RecyclerView recyclerView;
//    private FloatingActionButton fabAddButton;
//    private RecyclerView.Adapter adapter;
//    private List<Note> noteList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Fragment fragment = new HomeFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.framercontainer, fragment);
        fragmentTransaction.commit();

//        this.recyclerView = (RecyclerView) findViewById(R.id.noteListRecyclerView);
//        this.fabAddButton = (FloatingActionButton) findViewById(R.id.fabAddNote);
//
//        this.initView();
//        this.loadNotes();
//
//        this.fabAddButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(HomeActivity.this, AddNoteActivity.class);
//                startActivity(intent);
//            }
//        });
    }

//    private void initView() {
//        this.recyclerView.setLayoutManager(new LinearLayoutManager(this));
//    }
//
//    private void loadNotes() {
//        DatabaseHelper db = new DatabaseHelper(this);
//
//        this.noteList = db.getAllNotes();
//        if(noteList.size() > 0) {
//            this.adapter = new NoteAdapter(this, noteList);
//            this.recyclerView.setAdapter(this.adapter);
//            this.adapter.notifyDataSetChanged();
//        }
//    }
}