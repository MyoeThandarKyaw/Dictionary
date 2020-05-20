package com.example.dictionary;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

public class DBManager {

    private DatabaseHelper dbHelper;

    private Context context;

    private SQLiteDatabase database;

    public DBManager(Context c) {
        context = c;
    }

    public DBManager open() throws SQLException {
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public void insert(String english_word, String myanmar_word) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.english_word, english_word);
        contentValue.put(DatabaseHelper.myanmar_word, myanmar_word);
        database.insert(DatabaseHelper.TABLE_NAME, null, contentValue);
    }

    public Cursor fetch() {
        String[] columns = new String[] { DatabaseHelper._ID, DatabaseHelper.english_word, DatabaseHelper.myanmar_word };
        Cursor cursor = database.query(DatabaseHelper.TABLE_NAME, columns, null, null, null, null, null);
        final int idIndex = cursor.getColumnIndex(DatabaseHelper._ID);
        final int engIndex = cursor.getColumnIndex(DatabaseHelper.english_word);
        final int myanmarIndex = cursor.getColumnIndex(DatabaseHelper.myanmar_word );
        if (cursor != null) {
            cursor.moveToFirst();
        }
        ArrayList<Word> wordArrayList = new ArrayList<>();
        while(cursor.isAfterLast() == false) {
            Word word = new Word();
            word.setEng_word(cursor.getString(engIndex));
            word.setMyanmar_word(cursor.getString(myanmarIndex));


            wordArrayList.add(word);
        }
        return cursor;
    }

    public int update(long _id, String english_word, String myanmar_word) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.english_word, english_word);
        contentValues.put(DatabaseHelper.myanmar_word, myanmar_word);
        int i = database.update(DatabaseHelper.TABLE_NAME, contentValues, DatabaseHelper._ID + " = " + _id, null);
        return i;
    }
    public ArrayList<Word> getAllWords() {
        //SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Word> listItems = new ArrayList<Word>();

        Cursor cursor = database.rawQuery("SELECT * from " + DatabaseHelper.TABLE_NAME,
                new String[] {});
        final int engIndex = cursor.getColumnIndex(DatabaseHelper.english_word);
        final int myanmarIndex = cursor.getColumnIndex(DatabaseHelper.myanmar_word );

        if (cursor.moveToFirst()) {
            do {
                Word word = new Word();

                word.setEng_word(cursor.getString(engIndex));

                word.setMyanmar_word(cursor.getString(myanmarIndex));

                listItems.add(word);
            } while (cursor.moveToNext());
        }

        cursor.close();

        return listItems;
    }

    public void delete(String word) {
        //Log.e("Position","enter delte"+word);
        //database.delete(DatabaseHelper.TABLE_NAME, DatabaseHelper._ID + "=" + _id, null);
        database.execSQL("DELETE FROM " +DatabaseHelper.TABLE_NAME+ "  WHERE EnglishWord ='"+word+"'");
    }
//public int delete(String ID)
//{
//    String where="ID=?";
//    int numberOFEntriesDeleted= database.delete(DatabaseHelper.TABLE_NAME, where, new String[]{ID}) ;
//    //toast("Number fo Entry Deleted Successfully : "+numberOFEntriesDeleted);
//    return numberOFEntriesDeleted;
//}
}
