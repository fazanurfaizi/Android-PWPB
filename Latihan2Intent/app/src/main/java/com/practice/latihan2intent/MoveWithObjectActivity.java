package com.practice.latihan2intent;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MoveWithObjectActivity extends AppCompatActivity {

    public static String EXTRA_PERSON = " extra_person";
    public TextView tvobject;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_move_with_object);
        this.tvobject = (TextView) findViewById(R.id.tv_object_received);
        Person person = getIntent().getParcelableExtra(EXTRA_PERSON);
        String text = "Name : " + person.getName() + ", Email : " +  person.getEmail() + ", Age : "
                + person.getAge() + ", Location : " + person.getCity();
        this.tvobject.setText(text);
    }
}
