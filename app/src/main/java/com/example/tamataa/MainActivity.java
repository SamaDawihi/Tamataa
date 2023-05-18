package com.example.tamataa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button majorsBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        majorsBtn = findViewById(R.id.majorsBtn);
        Intent goToMajors = new Intent(this, Majors.class);
        majorsBtn.setOnClickListener(v -> startActivity(goToMajors));
    }

}