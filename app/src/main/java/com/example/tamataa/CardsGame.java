package com.example.tamataa;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class CardsGame extends AppCompatActivity {

    String playerName;

    int phraseBrandId;
    int score;

    TextView marketingPhrase, playerNameTV, scoreTV;

    Button nextBtn;
    List<Button> cards;

    List<PhraseModel> phrases;
    List<BrandModel> brands;

    CardsDBController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cards_game);


        controller = new CardsDBController(this);

        marketingPhrase = findViewById(R.id.marketingPhraseTV);



        cards = new ArrayList<>();
        initiateCards();

        startGame();

        nextBtn = findViewById(R.id.nextBtn);
        nextBtn.setOnClickListener(v -> nextPhrase());
    }




    private void initiateCards() {
        brands = controller.getAllBrands();
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
                BrandModel brand = brands.get(i);
                Button cardButton = cards.get(i);
                cardButton.setText(brand.getName());
                cardButton.setOnClickListener(v -> setTheListener(brand));
            }
        }

    }

    private void setTheListener(BrandModel brand) {
        if(brand.getBrandId() == phraseBrandId){
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
            phraseBrandId = phrases.get(randomIndex).getBrandId();
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

        phrases = controller.getAllPhrases();
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
}