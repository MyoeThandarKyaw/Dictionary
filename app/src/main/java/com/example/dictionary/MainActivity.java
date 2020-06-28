package com.example.dictionary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
private Button btn_search;
private Button btn_new_def;
private Button btn_view_all_def;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_search=(Button) findViewById(R.id.btn_english);
        btn_new_def=(Button) findViewById(R.id.btn_new_vocab);
        btn_view_all_def=(Button)findViewById(R.id.btn_view_all_vocab);

        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast=Toast.makeText(getApplicationContext(),"Hello English to Myanmar",Toast.LENGTH_SHORT);
                toast.show();
                Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
                startActivity(intent);
            }
        });
        btn_new_def.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), NewDefinitionActivity.class);
                startActivity(intent);
            }
        });
        btn_view_all_def.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DispalyActvity.class);
                startActivity(intent);
            }
        });
    }
}
