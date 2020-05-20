package com.example.dictionary;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.util.ArrayList;

public class DispalyActvity extends AppCompatActivity {
    private DBManager dbManager;

    private ListView listView;

    private SimpleCursorAdapter adapter;

    final String[] from = new String[] { DatabaseHelper._ID,
            DatabaseHelper.english_word, DatabaseHelper.myanmar_word };

    final int[] to = new int[] { R.id.txt_no, R.id.txt_english_word, R.id.txt_myanmar_word };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dispaly_actvity);
        dbManager = new DBManager(this);
        dbManager.open();

        ArrayList<Word> wordArrayList = dbManager.getAllWords();
        //Cursor cursor=dbManager.fetch();

        listView = (ListView) findViewById(R.id.list_view);

//        for (int i=0;i<wordArrayList.size();i++){
//            Log.e("Word Data",wordArrayList.get(i).getEng_word());
//            Log.e("Word Data",wordArrayList.get(i).getMyanmar_word());
//
//        }
       // adapter = new SimpleCursorAdapter(this, R.layout.activity_view_record, cursor, from, to, 0);
        MyListAdapter adapter = new MyListAdapter(this, R.layout.activity_view_record, wordArrayList);
        adapter.notifyDataSetChanged();

        listView.setAdapter(adapter);
    }
}
