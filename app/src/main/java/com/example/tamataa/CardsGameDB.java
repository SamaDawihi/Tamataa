package com.example.tamataa;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Color;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class CardsGameDB extends SQLiteOpenHelper {
    private static String DBNAME = "CardsGame";
    private static int DBVersion = 1;

    private static String Brand = "BRAND";

    private static String Phrase = "PHRASE";




    public CardsGameDB(@Nullable Context context) {

        super(context, DBNAME, null, DBVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query =
                "CREATE TABLE " + Brand + " (" +
                        "brandId INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL" +
                        ", name TEXT NOT NULL" +
                        ")";
        db.execSQL(query);
        insertBrands(db);
        query =
                "CREATE TABLE " + Phrase + " (" +
                        "phraseId INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL" +
                        ", brandId INTEGER NOT NULL" +
                        ", phrase TEXT NOT NULL" +
                        ", FOREIGN KEY (brandId) REFERENCES " + Brand + "(brandId) ON DELETE CASCADE" +
                        ")";
        db.execSQL(query);
        insertPhrases(db);

    }

    private void insertBrands(SQLiteDatabase db) {
        String query = "INSERT INTO " + Brand + " (name) VALUES ('أبل')";
        db.execSQL(query);

        query = "INSERT INTO " + Brand + " (name) VALUES ('أرامكو')";
        db.execSQL(query);

        query = "INSERT INTO " + Brand + " (name) VALUES ('مرسيدس')";
        db.execSQL(query);

        query = "INSERT INTO " + Brand + " (name) VALUES ('أمازون')";
        db.execSQL(query);

        query = "INSERT INTO " + Brand + " (name) VALUES ('ألفابت')";
        db.execSQL(query);

        query = "INSERT INTO " + Brand + " (name) VALUES ('سامسونق')";
        db.execSQL(query);

        query = "INSERT INTO " + Brand + " (name) VALUES ('بيبسي')";
        db.execSQL(query);

        query = "INSERT INTO " + Brand + " (name) VALUES ('سابك')";
        db.execSQL(query);

        query = "INSERT INTO " + Brand + " (name) VALUES ('تويتر')";
        db.execSQL(query);
    }

    private void insertPhrases(SQLiteDatabase db) {
        String query = "INSERT INTO " + Phrase + " (brandId, phrase) VALUES (1, 'عبارة ابل 1')";
        db.execSQL(query);

        query = "INSERT INTO " + Phrase + " (brandId, phrase) VALUES (1, 'عبارة ابل 2')";
        db.execSQL(query);

        query = "INSERT INTO " + Phrase + " (brandId, phrase) VALUES (1, 'عبارة ابل 3')";
        db.execSQL(query);


        query = "INSERT INTO " + Phrase + " (brandId, phrase) VALUES (2, 'عبارة ارامكو 1')";
        db.execSQL(query);

        query = "INSERT INTO " + Phrase + " (brandId, phrase) VALUES (2, 'عبارة ارامكو 2')";
        db.execSQL(query);

        query = "INSERT INTO " + Phrase + " (brandId, phrase) VALUES (2, 'عبارة ارامكو 3')";
        db.execSQL(query);


        query = "INSERT INTO " + Phrase + " (brandId, phrase) VALUES (3, 'عبارة مرسيدس 1')";
        db.execSQL(query);

        query = "INSERT INTO " + Phrase + " (brandId, phrase) VALUES (3, 'عبارة مرسيدس 2')";
        db.execSQL(query);

        query = "INSERT INTO " + Phrase + " (brandId, phrase) VALUES (3, 'عبارة مرسيدس 3')";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Brand);
        db.execSQL("DROP TABLE IF EXISTS " + Phrase);
        onCreate(db);
    }

    public List<PhraseModel> getAllPhrases() {
        List<PhraseModel> phrases = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM " + Phrase;
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                int phraseId = cursor.getInt(Math.max(cursor.getColumnIndex("phraseId"),0));
                int brandId = cursor.getInt(Math.max(cursor.getColumnIndex("brandId"),0));
                String phraseText = cursor.getString(Math.max(cursor.getColumnIndex("phrase"),0));

                PhraseModel phrase = new PhraseModel(phraseId, brandId, phraseText);
                phrases.add(phrase);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return phrases;
    }

    public List<BrandModel> getAllBrands() {
        List<BrandModel> brands = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM " + Brand;
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                int brandId = cursor.getInt(Math.max(cursor.getColumnIndex("brandId"), 0));
                String name = cursor.getString(Math.max(cursor.getColumnIndex("name"), 0));

                BrandModel brand = new BrandModel(brandId, name);
                brands.add(brand);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return brands;
    }


}
