package com.example.tamataa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MajorActivity extends AppCompatActivity {

    MajorModel major;
    String majorAraName, majorEngName, majorDescription;

    MajorsDBController controller;
    List<GameIdModel> availableGames;

    Intent goToCardsGame;
    List<Button> gameBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_major);

        setMajor();

        controller = new MajorsDBController(this);

        gameBtn = new ArrayList<>();

        setGames();

    }

    private void setMajor() {

        Intent intent = getIntent();

        // Retrieve the extra value using the key
        majorAraName = intent.getStringExtra("majorName");
        majorEngName = intent.getStringExtra("majorEngName");
        majorDescription = intent.getStringExtra("majorDescription");

        major = new MajorModel(majorEngName, majorAraName, majorDescription);

        Toast.makeText(this, "majorName = " + majorAraName, Toast.LENGTH_SHORT).show();

        TextView majorNameTV = findViewById(R.id.majorNameTV);

        majorNameTV.setText(majorAraName);
    }


    private void setGames() {
        availableGames = controller.getAllGameIdsByMajor(majorEngName);

        gameBtn.add(findViewById(R.id.game1));
        gameBtn.add(findViewById(R.id.game2));
        gameBtn.add(findViewById(R.id.game3));
        gameBtn.get(0).setText("SIZE IS " + availableGames.size());

        goToCardsGame = new Intent(this, CardsGameActivity.class);

        //if (gameBtn.size() <= availableGames.size()) {
        //must be based on real btns
            for (int i = 0; i < availableGames.size(); i++) {
                GameIdModel gameId = availableGames.get(i);
                Button cardButton = gameBtn.get(i);
                cardButton.setText(gameId.getName());
                cardButton.setOnClickListener(v -> goToGame(gameId));
            }
        //}

    }

    private void goToGame(GameIdModel gameId) {
        if(gameId.getGameTypeId() == 1) {//cardsGame
            goToCardsGame = new Intent(this, CardsGameActivity.class);
            goToCardsGame.putExtra("gameId", gameId.getGameId());//marketing phrase game
            startActivity(goToCardsGame);
        }


    }

}