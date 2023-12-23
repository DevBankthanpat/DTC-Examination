package com.bankthanapat.dtcexaminationjava;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    private SQLiteDatabase sqLiteDatabase;

    public DBHelper(Context context) {
        super(context, "DTC_Animal.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_ANIMAL_TABLE = String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT)",
                Animal.TABLE,
                Animal.Column.ID,
                Animal.Column.NAME);
        db.execSQL(CREATE_ANIMAL_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String DROP_FRIEND_TABLE = "DROP TABLE IF EXISTS " + Animal.TABLE;
        db.execSQL(DROP_FRIEND_TABLE);
        onCreate(db);
    }

    public boolean isDataExists() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + Animal.TABLE, null);
        boolean isDataExists = cursor.getCount() > 0;
        cursor.close();
        db.close();
        return isDataExists;
    }

    public void addAnimal(String name) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Animal.Column.NAME, name);

        db.insert(Animal.TABLE, null, values);
        db.close();
    }

    public List<String> getAnimalList() {
        List<String> animals = new ArrayList<String>();

        sqLiteDatabase = this.getWritableDatabase();

        Cursor cursor = sqLiteDatabase.query(Animal.TABLE, null, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        while(!cursor.isAfterLast()) {
            animals.add(cursor.getString(1));
            cursor.moveToNext();
        }
        sqLiteDatabase.close();
        return animals;
    }
}
