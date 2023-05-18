package com.example.tamataa;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CardsGame extends AppCompatActivity {

    //gameInfo
    int gameId;
    int phraseCategoryId;

    //player Info
    String playerName;
    int score;

    TextView marketingPhrase, playerNameTV, scoreTV, timerTV;
    private CountDownTimer countDownTimer;
    private long timeLeftInMillis = 60000; // 1 minute in milliseconds

    Button nextBtn;
    List<Button> cards;
    List<PhraseModel> phrases;
    List<CategoryModel> brands;

    CardsDBController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cards_game);


        controller = new CardsDBController(this);

        setGameId();

        marketingPhrase = findViewById(R.id.marketingPhraseTV);

        cards = new ArrayList<>();
        initiateCards();
        setTimer();

        startGame();

        nextBtn = findViewById(R.id.nextBtn);
        nextBtn.setOnClickListener(v -> nextPhrase());
    }

    private void setGameId() {
        Intent intent = getIntent();

        // Retrieve the extra value using the key
        gameId = intent.getIntExtra("gameId", -1);
        if (gameId == -1) finish();

        Toast.makeText(this, "gameId = " + gameId, Toast.LENGTH_SHORT).show();
    }

    private void setTimer() {
        timerTV = findViewById(R.id.timerTV);

        countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateTimer();
            }

            @Override
            public void onFinish() {
                // Timer finished, handle it here
                timerTV.setText("0:00");
            }
        };

        // Start the timer
        countDownTimer.start();
    }
    private void updateTimer() {
        int minutes = (int) (timeLeftInMillis / 1000) / 60;
        int seconds = (int) (timeLeftInMillis / 1000) % 60;

        String timeLeftFormatted = String.format("%d:%02d", minutes, seconds);
        timerTV.setText(timeLeftFormatted);
    }

    private void initiateCards() {
        brands = controller.getAllCategories(gameId);
        cards.add(findViewById(R.id.brand11));
        cards.add(findViewById(R.id.brand12));
        cards.add(findViewById(R.id.brand13));
        cards.add(findViewById(R.id.brand21));
        cards.add(findViewById(R.id.brand22));
        cards.add(findViewById(R.id.brand23));
        cards.add(findViewById(R.id.brand31));
        cards.add(findViewById(R.id.brand32));
        cards.add(findViewById(R.id.brand33));

        if (cards.size() <= brands.size()) {
            for (int i = 0; i < cards.size(); i++) {
                CategoryModel brand = brands.get(i);
                Button cardButton = cards.get(i);
                cardButton.setText(brand.getName());
                cardButton.setOnClickListener(v -> setTheListener(brand));
            }
        }

    }

    private void setTheListener(CategoryModel brand) {
        if(brand.getBrandId() == phraseCategoryId){
            score++;
            marketingPhrase.setText("Correct");
            scoreTV.setText("PlayerScore = " + score);
        }else{
            marketingPhrase.setText("Not Correct");
            scoreTV.setText("PlayerScore = " + score);
        }
        // Assuming you have defined and initialized the "cards" list containing the buttons

        for (Button cardButton : cards) {
            cardButton.setEnabled(false);
        }


    }

    void setPhrase(){
        int phraseCount = phrases.size();

        if (phraseCount <= 0){
            endGame();
        }else {

            Random random = new Random();
            int randomIndex = random.nextInt(phraseCount);

            String randomPhrase = phrases.get(randomIndex).getPhrase();
            phraseCategoryId = phrases.get(randomIndex).getBrandId();
            phrases.remove(randomIndex);

            marketingPhrase.setText(randomPhrase);
        }
    }

    private void startGame() {
        scoreTV = findViewById(R.id.scoreTV);
        playerNameTV = findViewById(R.id.playerNameTV);

        playerName = "Player name";
        score = 0;

        scoreTV.setText("PlayerScore = " + score);
        playerNameTV.setText(playerName);

        phrases = controller.getAllPhrases(gameId);
        setPhrase();
    }
    private void nextPhrase() {
        setPhrase();

        for (Button cardButton : cards) {
            cardButton.setEnabled(true);
        }

    }
    private void endGame() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Score");
        builder.setMessage("Your score is: " + score);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

// Create and show the AlertDialog
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Stop the timer when the activity or fragment is destroyed
        countDownTimer.cancel();
    }
}
