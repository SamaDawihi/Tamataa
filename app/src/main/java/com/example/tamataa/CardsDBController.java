package com.example.tamataa;

import android.content.Context;

import java.util.List;

public class CardsDBController {

    private CardsGameDB dbHelper;

    public CardsDBController(Context c) {
        dbHelper = new CardsGameDB(c);
    }

    List<PhraseModel> getAllPhrases(int gameId){
        return dbHelper.getAllPhrases(gameId);
    }

    public List<CategoryModel> getAllCategories(int gameId) {
        return dbHelper.getAllCategories(gameId);
    }
}
