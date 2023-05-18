package com.example.tamataa;

import android.content.Context;

import java.util.List;

public class CardsDBController {

    private CardsGameDB dbHelper;

    public CardsDBController(Context c) {
        dbHelper = new CardsGameDB(c);
    }

    List<PhraseModel> getAllPhrases(){
        return dbHelper.getAllPhrases();
    }

    public List<BrandModel> getAllBrands() {
        return dbHelper.getAllBrands();
    }
}
