package com.example.tamataa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.HashMap;
import java.util.Map;

public class Majors extends AppCompatActivity {

    Map<String,Button> majorBtn;
    Button marketingBtn;
    Button computerBtn;

    Intent goToMajor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_majors);



        majorBtn = new HashMap<>();
        majorBtn.put("marketingBtn", findViewById(R.id.marketingBtn));
        majorBtn.put("computerBtn", findViewById(R.id.computerBtn));

        goToMajor = new Intent(this, Major.class);

        majorBtn.get("computerBtn").setOnClickListener(v -> goToMajor(1));
        majorBtn.get("marketingBtn").setOnClickListener(v -> goToMajor(2));


    }

    void goToMajor(int majorId) {
        goToMajor.putExtra("majorId", majorId);
        startActivity(goToMajor);
    }
}