package com.example.tamataa;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class MajorsDB extends SQLiteOpenHelper {
    private static String DBNAME = "Majors";
    private static int DBVersion = 1;

    private static String MAJOR = "Major";

    private static String GameType = "GameType";

    private static String GAMEID = "gameId";


    public MajorsDB(@Nullable Context context) {

        super(context, DBNAME, null, DBVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query =
                "CREATE TABLE " + MAJOR + " (" +
                        "nameEng TEXT PRIMARY KEY NOT NULL" +
                        ", name TEXT NOT NULL" +
                        ", description TEXT" +
                        ")";
        db.execSQL(query);
        query =
                "CREATE TABLE " + GameType + " (" +
                        "gameTypeId INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL" +
                        ", name TEXT NOT NULL" +
                        ")";
        db.execSQL(query);
        query = "CREATE TABLE " + GAMEID + " (" +
                "gameId INTEGER NOT NULL" +
                ", gameTypeId INTEGER NOT NULL" +
                ", majorNameEng TEXT NOT NULL" +
                ", name TEXT NOT NULL" +
                ", PRIMARY KEY (gameId, gameTypeId)" +
                ", FOREIGN KEY (majorNameEng) REFERENCES " + MAJOR + "(nameEng) ON DELETE CASCADE" +
                ", FOREIGN KEY (gameTypeId) REFERENCES " + GameType + "(gameTypeId) ON DELETE CASCADE" +
                ")";
        db.execSQL(query);

        insertMajors(db);
        insertGameTypes(db);
        insertGameId(db);

    }

    private void insertMajors(SQLiteDatabase db) {
        String query = "INSERT INTO " + MAJOR + " (nameEng, name) VALUES ('Computer Science', 'علوم الحاسب')";
        db.execSQL(query);

        query = "INSERT INTO " + MAJOR + " (nameEng, name) VALUES ('Marketing', 'التسويق')";
        db.execSQL(query);

        query = "INSERT INTO " + MAJOR + " (nameEng, name) VALUES ('Business Administration', 'إدارة الأعمال')";
        db.execSQL(query);

        query = "INSERT INTO " + MAJOR + " (nameEng, name) VALUES ('Mechanical Engineering', 'هندسة الميكانيكية')";
        db.execSQL(query);

        query = "INSERT INTO " + MAJOR + " (nameEng, name) VALUES ('Civil Engineering', 'هندسة مدنية')";
        db.execSQL(query);

        query = "INSERT INTO " + MAJOR + " (nameEng, name) VALUES ('Electrical Engineering', 'هندسة كهربائية')";
        db.execSQL(query);

        query = "INSERT INTO " + MAJOR + " (nameEng, name) VALUES ('Psychology', 'علم النفس')";
        db.execSQL(query);

        query = "INSERT INTO " + MAJOR + " (nameEng, name) VALUES ('Nursing', 'التمريض')";
        db.execSQL(query);

        query = "INSERT INTO " + MAJOR + " (nameEng, name) VALUES ('Architecture', 'الهندسة المعمارية')";
        db.execSQL(query);

        query = "INSERT INTO " + MAJOR + " (nameEng, name) VALUES ('English Literature', 'الأدب الإنجليزي')";
        db.execSQL(query);

        query = "INSERT INTO " + MAJOR + " (nameEng, name) VALUES ('History', 'التاريخ')";
        db.execSQL(query);

        query = "INSERT INTO " + MAJOR + " (nameEng, name) VALUES ('Chemistry', 'الكيمياء')";
        db.execSQL(query);
    }
    private void insertGameTypes(SQLiteDatabase db) {
        String query = "INSERT INTO " + GameType + " (name) VALUES ('Cards Game')";//typeId = 1
        db.execSQL(query);
    }
    private void insertGameId(SQLiteDatabase db) {
        String query = "INSERT INTO " + GAMEID + " (gameId, gameTypeId, majorNameEng, name) VALUES (1, 1, 'Marketing', 'العبارات التسويقية')";
        db.execSQL(query);
        query = "INSERT INTO " + GAMEID + " (gameId, gameTypeId, majorNameEng, name) VALUES (2, 1, 'Computer Science','Catch Exceptions')";
        db.execSQL(query);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + GAMEID);
        db.execSQL("DROP TABLE IF EXISTS " + GameType);
        db.execSQL("DROP TABLE IF EXISTS " + MAJOR);
        onCreate(db);
    }

    public List<MajorModel> getAllMajors() {
        List<MajorModel> majors = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + MAJOR, null);

        if (cursor.moveToFirst()) {
            do {
                String nameEng = cursor.getString(Math.max(cursor.getColumnIndex("nameEng"), 0));
                String name = cursor.getString(Math.max(cursor.getColumnIndex("name"), 0));
                String description = cursor.getString(Math.max(cursor.getColumnIndex("description"), 0));

                MajorModel major = new MajorModel(nameEng, name, description);
                majors.add(major);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return majors;
    }

    public List<GameIdModel> getAllGameIdsByMajor(String majorNameEng) {
        List<GameIdModel> gameIds = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();
        String[] columns = {"gameId", "gameTypeId", "majorNameEng", "name"};
        String selection = "majorNameEng=?";
        String[] selectionArgs = {majorNameEng};

        Cursor cursor = db.query(GAMEID, columns, selection, selectionArgs, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                int gameIdIndex = cursor.getColumnIndex("gameId");
                int gameTypeIdIndex = cursor.getColumnIndex("gameTypeId");
                int majorNameIndex = cursor.getColumnIndex("majorNameEng");
                int nameIndex = cursor.getColumnIndex("name");

                int gameId = cursor.getInt(Math.max(gameIdIndex, 0));
                int gameTypeId = cursor.getInt(Math.max(gameTypeIdIndex, 0));
                String majorName = cursor.getString(Math.max(majorNameIndex, 0));
                String name = cursor.getString(Math.max(nameIndex, 0));

                GameIdModel gameIdModel = new GameIdModel(gameId, gameTypeId, majorName, name);
                gameIds.add(gameIdModel);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return gameIds;
    }


    public GameTypeModel getGameTypeByTypeId(int typeId) {
        GameTypeModel gameType = null;

        SQLiteDatabase db = getReadableDatabase();

        String query = "SELECT * FROM " + GameType + " WHERE gameTypeId = " + typeId;

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            int typeIdIndex = cursor.getColumnIndex("gameTypeId");
            int nameIndex = cursor.getColumnIndex("name");

            int gameTypeId = cursor.getInt(typeIdIndex);
            String name = cursor.getString(nameIndex);

            gameType = new GameTypeModel(gameTypeId, name);
        }

        cursor.close();
        db.close();

        return gameType;
    }
}