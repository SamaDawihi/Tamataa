package com.example.tamataa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Map;

public class Major extends AppCompatActivity {

    Map<String , Button> gameBtn;

    int majorId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_major);

        setMajorId();

        setGames();

    }

    private void setMajorId() {

        Intent intent = getIntent();

        // Retrieve the extra value using the key
        majorId = intent.getIntExtra("majorId", -1);

        Toast.makeText(this, "majorId = " + majorId, Toast.LENGTH_SHORT).show();

        TextView majorName = findViewById(R.id.majorNameTV);

        switch (majorId){
            case 1:
                majorName.setText("حاسب");
                break;
            case 2:
                majorName.setText("تسويق");
                break;
            default:
                majorName.setText("اختيار خاطئ");

        }
    }


    private void setGames() {
        if (majorId == 2){
            gameBtn.put("cardGame", findViewById(R.id.game1));

        }

    }

}