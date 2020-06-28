package com.example.dictionary;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.media.Image;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {
private ImageButton btn_clear;
private EditText edt_search;
    private DBManager dbManager;

    private ListView listView;
    ArrayList<Word> wordArrayList;

    private SimpleCursorAdapter adapter;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        dbManager = new DBManager(this);
        dbManager.open();
        btn_clear= (ImageButton) findViewById(R.id.btn_clear);
        edt_search=(EditText) findViewById(R.id.edt_search);
        listView=(ListView)findViewById(R.id.list_view);

        edt_search.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                if (s.length() > 0)
                {

                    Log.e("characteronTextChanged",s.toString());


                }

            }

            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            public void afterTextChanged(Editable s) {

                if(s.length()>0){
                    wordArrayList= dbManager.fetchAll(s.toString());
                    if(wordArrayList!=null){
                        MyListAdapter adapter = new MyListAdapter(getApplicationContext(), R.layout.display_result, wordArrayList);
                        adapter.notifyDataSetChanged();

                        listView.setAdapter(adapter);

                    }
                }


//            for (int i=0;i<wordArrayList.size();i++){
//                Log.e("ArrayListData",wordArrayList.get(i).getEng_word());
//            }
            }
        });
        btn_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edt_search.setText("");
            }
        });



    }
}
