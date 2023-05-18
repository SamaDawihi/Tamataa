package com.example.tamataa;

import android.content.Context;

import java.util.List;

public class MajorsDBController {

    private MajorsDB dbHelper;

    public MajorsDBController(Context c) {
        dbHelper = new MajorsDB(c);
    }

    public List<MajorModel> getAllMajors() {
        return dbHelper.getAllMajors();
    }
    public List<GameIdModel> getAllGameIdsByMajor(String majorNameEng){
        return dbHelper.getAllGameIdsByMajor(majorNameEng);
    }
    public GameTypeModel getGameTypeByTypeId(int typeId){
        return dbHelper.getGameTypeByTypeId(typeId);
    }
}
