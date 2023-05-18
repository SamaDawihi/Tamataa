package com.example.tamataa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Majors extends AppCompatActivity {

    List<Button> majorBtn;

    MajorsDBController controller;

    List<MajorModel> majors;
    Intent goToMajor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_majors);

        controller = new MajorsDBController(this);
        setMajors();


    }

    private void setMajors() {
        majors = controller.getAllMajors();
        majorBtn = new ArrayList<>();

        majorBtn.add(findViewById(R.id.major1));
        majorBtn.add(findViewById(R.id.major2));
        majorBtn.add(findViewById(R.id.major3));
        majorBtn.add(findViewById(R.id.major4));
        majorBtn.add(findViewById(R.id.major5));
        majorBtn.add(findViewById(R.id.major6));
        majorBtn.add(findViewById(R.id.major7));
        majorBtn.add(findViewById(R.id.major8));
        majorBtn.add(findViewById(R.id.major9));

        goToMajor = new Intent(this, Major.class);

        if (majorBtn.size() <= majors.size()) {
            for (int i = 0; i < majorBtn.size(); i++) {
                MajorModel major = majors.get(i);
                Button cardButton = majorBtn.get(i);
                cardButton.setText(major.getName());
                cardButton.setOnClickListener(v -> goToMajor(major));
            }
        }
    }


    void goToMajor(MajorModel major) {
        goToMajor.putExtra("majorEngName", major.getNameEng());
        goToMajor.putExtra("majorName", major.getName());
        goToMajor.putExtra("majorDescription", major.getDescription());


        startActivity(goToMajor);
    }
}