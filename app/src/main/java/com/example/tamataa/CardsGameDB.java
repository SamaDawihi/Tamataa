package com.example.tamataa;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class CardsGameDB extends SQLiteOpenHelper {
    private static String DBNAME = "CardsGame";
    private static int DBVersion = 1;

    private static String GAME = "Game";

    private static String CATEGORY = "Category";

    private static String PHRASE = "Phrase";

    public CardsGameDB(@Nullable Context context) {

        super(context, DBNAME, null, DBVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query =
                "CREATE TABLE " + GAME + " (" +
                        "gameId INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL" +
                        ", name TEXT NOT NULL" +
                        ", description TEXT" +
                        ")";
        db.execSQL(query);
        query =
                "CREATE TABLE " + CATEGORY + " (" +
                        "categoryId INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL" +
                        ", gameId INTEGER NOT NULL" +
                        ", name TEXT NOT NULL" +
                        ", FOREIGN KEY (gameId) REFERENCES " + GAME + "(gameId) ON DELETE CASCADE" +
                        ")";
        db.execSQL(query);
        query =
                "CREATE TABLE " + PHRASE + " (" +
                        "phraseId INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL" +
                        ", categoryId INTEGER NOT NULL" +
                        ", phrase TEXT NOT NULL" +
                        ", FOREIGN KEY (categoryId) REFERENCES " + CATEGORY + "(categoryId) ON DELETE CASCADE" +
                        ")";
        db.execSQL(query);
        insertGames(db);
        insertMarketingPhrasesCategories(db, 1);//gameId of Marketing phrase game is 1.
        insertExceptionsCategories(db, 2);//gameId of Exceptions game is 2.
    }

    private void insertGames(SQLiteDatabase db) {
        String query = "INSERT INTO " + GAME + " (name, description) VALUES ('العبارات التسويقية','إختر المنتج الملائم للعبارة التسويقية')";
        db.execSQL(query);

        query = "INSERT INTO " + GAME + " (name, description) VALUES ('Catch exceptions', 'find the exeption that will be caused by the provided codes')";
        db.execSQL(query);


    }
    private void insertMarketingPhrasesCategories(SQLiteDatabase db, int gameId) {
        String query = "INSERT INTO " + CATEGORY + " (gameId, name) VALUES ( " + gameId + ", 'أبل')";//1
        db.execSQL(query);

        query = "INSERT INTO " + CATEGORY + " (gameId, name) VALUES ( " + gameId + ", 'أرامكو')";//2
        db.execSQL(query);

        query = "INSERT INTO " + CATEGORY + " (gameId, name) VALUES ( " + gameId + ", 'مرسيدس')";//3
        db.execSQL(query);

        query = "INSERT INTO " + CATEGORY + " (gameId, name) VALUES ( " + gameId + ", 'أمازون')";//4
        db.execSQL(query);

        query = "INSERT INTO " + CATEGORY + " (gameId, name) VALUES ( " + gameId + ", 'ألفابت')";//5
        db.execSQL(query);

        query = "INSERT INTO " + CATEGORY + " (gameId, name) VALUES ( " + gameId + ", 'سامسونق')";//6
        db.execSQL(query);

        query = "INSERT INTO " + CATEGORY + " (gameId, name) VALUES ( " + gameId + ", 'بيبسي')";//7
        db.execSQL(query);

        query = "INSERT INTO " + CATEGORY + " (gameId, name) VALUES ( " + gameId + ", 'سابك')";//8
        db.execSQL(query);

        query = "INSERT INTO " + CATEGORY + " (gameId, name) VALUES ( " + gameId + ", 'تويتر')";//9
        db.execSQL(query);
        insertMarketingPhrases(db);
    }

    private void insertMarketingPhrases(SQLiteDatabase db) {
        String query = "INSERT INTO " + PHRASE + " (categoryId, phrase) VALUES (1, 'عبارة ابل 1')";
        db.execSQL(query);

        query = "INSERT INTO " + PHRASE + " (categoryId, phrase) VALUES (1, 'عبارة ابل 2')";
        db.execSQL(query);

        query = "INSERT INTO " + PHRASE + " (categoryId, phrase) VALUES (1, 'عبارة ابل 3')";
        db.execSQL(query);


        query = "INSERT INTO " + PHRASE + " (categoryId, phrase) VALUES (2, 'عبارة ارامكو 1')";
        db.execSQL(query);

        query = "INSERT INTO " + PHRASE + " (categoryId, phrase) VALUES (2, 'عبارة ارامكو 2')";
        db.execSQL(query);

        query = "INSERT INTO " + PHRASE + " (categoryId, phrase) VALUES (2, 'عبارة ارامكو 3')";
        db.execSQL(query);


        query = "INSERT INTO " + PHRASE + " (categoryId, phrase) VALUES (3, 'عبارة مرسيدس 1')";
        db.execSQL(query);

        query = "INSERT INTO " + PHRASE + " (categoryId, phrase) VALUES (3, 'عبارة مرسيدس 2')";
        db.execSQL(query);

        query = "INSERT INTO " + PHRASE + " (categoryId, phrase) VALUES (3, 'عبارة مرسيدس 3')";
        db.execSQL(query);
    }

    private void insertExceptionsCategories(SQLiteDatabase db, int gameId) {//ArrayIndexOutOfBoundsException

        String query = "INSERT INTO " + CATEGORY + " (gameId, name) VALUES ( " + gameId + ", 'NullPointerException')";//10
        db.execSQL(query);

        query = "INSERT INTO " + CATEGORY + " (gameId, name) VALUES ( " + gameId + ", 'ArrayIndexOutOfBoundsException')";//11
        db.execSQL(query);

        query = "INSERT INTO " + CATEGORY + " (gameId, name) VALUES ( " + gameId + ", 'ArithmeticException')";//12
        db.execSQL(query);

        query = "INSERT INTO " + CATEGORY + " (gameId, name) VALUES ( " + gameId + ", 'ClassCastException')";//13
        db.execSQL(query);

        query = "INSERT INTO " + CATEGORY + " (gameId, name) VALUES ( " + gameId + ", 'FileNotFoundException')";//14
        db.execSQL(query);

        query = "INSERT INTO " + CATEGORY + " (gameId, name) VALUES ( " + gameId + ", 'IOException')";//15
        db.execSQL(query);

        query = "INSERT INTO " + CATEGORY + " (gameId, name) VALUES ( " + gameId + ", 'ConcurrentModificationException')";//16
        db.execSQL(query);

        query = "INSERT INTO " + CATEGORY + " (gameId, name) VALUES ( " + gameId + ", 'IllegalArgumentException')";//17
        db.execSQL(query);

        query = "INSERT INTO " + CATEGORY + " (gameId, name) VALUES ( " + gameId + ", 'UnsupportedOperationException')";//18
        db.execSQL(query);

        insertExceptionsPhrases(db);
    }

    private void insertExceptionsPhrases(SQLiteDatabase db) {

        // Throws NullPointerException
        String query = "INSERT INTO " + PHRASE + " (categoryId, phrase) " +
                "VALUES (10, 'String str = null;\n" + "int length = str.length(); ')";
        db.execSQL(query);
        query = "INSERT INTO " + PHRASE + " (categoryId, phrase) " +
                "VALUES (10, 'String[] array = new String[5];\n" + "String element = array[0].toUpperCase();')";
        db.execSQL(query);
        query = "INSERT INTO " + PHRASE + " (categoryId, phrase) " +
                "VALUES (10, 'String str = null;\n" + "boolean isEmpty = str.isEmpty();')";
        db.execSQL(query);


        // Throws ArrayIndexOutOfBoundsException
        query = "INSERT INTO " + PHRASE + " (categoryId, phrase) " +
                "VALUES (11, 'int[] numbers = {1, 2, 3};\n" + "int value = numbers[5];')";
        db.execSQL(query);
        query = "INSERT INTO " + PHRASE + " (categoryId, phrase) " +
                "VALUES (11, 'int[] numbers = {1, 2, 3};\n" + "int value = numbers[-1];')";
        db.execSQL(query);
        query = "INSERT INTO " + PHRASE + " (categoryId, phrase) " +
                "VALUES (11, 'int[] numbers = {1, 2, 3};\n" + "int value = numbers[3];')";
        db.execSQL(query);


        // Throws ArithmeticException
        query = "INSERT INTO " + PHRASE + " (categoryId, phrase) " +
                "VALUES (12, 'int result = 10 / 0;')";
        db.execSQL(query);
        query = "INSERT INTO " + PHRASE + " (categoryId, phrase) " +
                "VALUES (12, 'int result = 10 % 0;')";
        db.execSQL(query);
        query = "INSERT INTO " + PHRASE + " (categoryId, phrase) " +
                "VALUES (12, 'int result = 10 >> 32;')";
        db.execSQL(query);


        // Throws ClassCastException
        query = "INSERT INTO " + PHRASE + " (categoryId, phrase) " +
                "VALUES (13, 'Object obj = new Integer(10);\n" + "String str = (String) obj;')";
        db.execSQL(query);query = "INSERT INTO " + PHRASE + " (categoryId, phrase) " +
                "VALUES (13, 'Object obj = new Integer(10);\n" + "Comparable<Integer> comparable = (Comparable<Integer>) obj;')";
        db.execSQL(query);query = "INSERT INTO " + PHRASE + " (categoryId, phrase) " +
                "VALUES (13, 'Object obj = new Integer(10);\n" + "ArrayList<Integer> list = (ArrayList<Integer>) obj;')";
        db.execSQL(query);

        // Throws FileNotFoundException
        query = "INSERT INTO " + PHRASE + " (categoryId, phrase) " +
                "VALUES (14, 'File file = new File(\"nonexistent.txt\");\n" + "FileInputStream fis = new FileInputStream(file);')";
        db.execSQL(query);
        query = "INSERT INTO " + PHRASE + " (categoryId, phrase) " +
                "VALUES (14, 'File file = new File(\"C:/myfolder/file.txt\");\n" + "FileInputStream fis = new FileInputStream(file);')";
        db.execSQL(query);
        query = "INSERT INTO " + PHRASE + " (categoryId, phrase) " +
                "VALUES (14, 'File file = new File(\"/root/important.txt\");\n" + "FileInputStream fis = new FileInputStream(file);')";
        db.execSQL(query);

        // Throws IOException
        query = "INSERT INTO " + PHRASE + " (categoryId, phrase) " +
                "VALUES (15, 'FileReader reader = new FileReader(\"myfile.txt\");\n" + "char[] buffer = new char[1024];\n" + "reader.read(buffer);')";
        db.execSQL(query);
        query = "INSERT INTO " + PHRASE + " (categoryId, phrase) " +
                "VALUES (15, 'FileInputStream fis = new FileInputStream(\"myfile.txt\");\n" + "fis.close();\n" + "fis.close();')";
        db.execSQL(query);
    }




    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + GAME);
        db.execSQL("DROP TABLE IF EXISTS " + CATEGORY);
        db.execSQL("DROP TABLE IF EXISTS " + PHRASE);
        onCreate(db);
    }



    public List<CategoryModel> getAllCategories() {
        List<CategoryModel> categories = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM " + CATEGORY;
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                int categoryId = cursor.getInt(Math.max(cursor.getColumnIndex("categoryId"), 0));
                String name = cursor.getString(Math.max(cursor.getColumnIndex("name"), 0));

                CategoryModel category = new CategoryModel(categoryId, name);
                categories.add(category);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return categories;
    }
    public List<PhraseModel> getAllPhrases() {
        List<PhraseModel> phrases = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM " + PHRASE;
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                int phraseId = cursor.getInt(Math.max(cursor.getColumnIndex("phraseId"),0));
                int categoryId = cursor.getInt(Math.max(cursor.getColumnIndex("categoryId"),0));
                String phraseText = cursor.getString(Math.max(cursor.getColumnIndex("phrase"),0));

                PhraseModel phrase = new PhraseModel(phraseId, categoryId, phraseText);
                phrases.add(phrase);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return phrases;
    }
    public List<CategoryModel> getAllCategories(int gameId) {
        List<CategoryModel> categories = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM " + CATEGORY + " WHERE gameId = " + gameId;
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                int categoryId = cursor.getInt(Math.max(cursor.getColumnIndex("categoryId"), 0));
                String name = cursor.getString(Math.max(cursor.getColumnIndex("name"), 0));

                CategoryModel category = new CategoryModel(categoryId, name);
                categories.add(category);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return categories;
    }

    public List<PhraseModel> getAllPhrases(int gameId) {
        List<PhraseModel> phrases = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT p.* FROM " + PHRASE + " p INNER JOIN " + CATEGORY + " c ON p.categoryId = c.categoryId WHERE c.gameId = " + gameId;
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                int phraseId = cursor.getInt(Math.max(cursor.getColumnIndex("phraseId"), 0));
                int categoryId = cursor.getInt(Math.max(cursor.getColumnIndex("categoryId"), 0));
                String phraseText = cursor.getString(Math.max(cursor.getColumnIndex("phrase"), 0));

                PhraseModel phrase = new PhraseModel(phraseId, categoryId, phraseText);
                phrases.add(phrase);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return phrases;
    }


}
