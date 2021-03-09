package com.example.ulanganpwpbsqlite.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.ulanganpwpbsqlite.R;
import com.example.ulanganpwpbsqlite.database.DatabaseHelper;
import com.example.ulanganpwpbsqlite.models.Note;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class AddNoteActivity extends AppCompatActivity {

    public static final String EXTRA_ID = "extra_id";
    public static final String EXTRA_TITLE = "extra_title";
    public static final String EXTRA_BODY = "extra_body";

    public boolean edit = false;

    private List<Note> noteList;

    private EditText editTextTitle;
    private EditText editTextBody;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        this.setTitle("Tambah Note");
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Button saveBtn = (Button) findViewById(R.id.btnSubmit);
        this.editTextTitle = (EditText) findViewById(R.id.titleTxt);
        this.editTextBody = (EditText) findViewById(R.id.bodyTxt);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveNote();
            }
        });

        if(getIntent().getStringExtra(EXTRA_TITLE) == null) {
            this.edit = false;
        } else {
            this.edit = true;
            this.editTextTitle.setText(getIntent().getStringExtra(EXTRA_TITLE));
            this.editTextBody.setText(getIntent().getStringExtra(EXTRA_BODY));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if(edit) {
            getMenuInflater().inflate(R.menu.delete_menu, menu);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                finish();
                return true;
            case R.id.deleteBtn:
                this.deleteNote();
        }

        return super.onOptionsItemSelected(item);
    }

    private void saveNote() {
        String title = this.editTextTitle.getText().toString();
        String body = this.editTextBody.getText().toString();

        if(title.equals("") || body.equals("")) {
            Toast.makeText(this, "Isi semua field", Toast.LENGTH_SHORT);
        } else {
            DatabaseHelper db = new DatabaseHelper(this);
            Note note = new Note();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
            Date date = new Date();
            String formatCreatedAt = simpleDateFormat.format(date);
            if(this.edit) {
                note.setId(getIntent().getIntExtra(EXTRA_ID, 0));
                note.setTitle(title);
                note.setBody(body);
                note.setCreatedAt(formatCreatedAt);
                db.updateNote(note);
            } else {
                note.setTitle(title);
                note.setBody(body);
                note.setCreatedAt(formatCreatedAt);
                db.insertNote(note);
            }

            db.close();
        }
    }

    private void deleteNote() {
        DatabaseHelper db = new DatabaseHelper(AddNoteActivity.this);
        db.deleteNote(getIntent().getIntExtra(EXTRA_ID, 0));
        this.finish();
    }
}