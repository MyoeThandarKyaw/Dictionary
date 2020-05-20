package com.example.dictionary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NewDefinitionActivity extends AppCompatActivity {
private Button btn_add_new_definition;
    private DBManager dbManager;
    private EditText edt_english_word;
    private EditText edt_myanmar_word;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_definition);
        btn_add_new_definition=(Button)findViewById(R.id.btn_add);
        edt_english_word=(EditText)findViewById(R.id.edt_english_word);
        edt_myanmar_word=(EditText)findViewById(R.id.edt_myanmar_word);
        dbManager = new DBManager(this);
        dbManager.open();

        btn_add_new_definition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String english_word = edt_english_word.getText().toString();
                final String myanmar_word = edt_myanmar_word.getText().toString();
                if(english_word.length()==0||myanmar_word.length()==0){
                    Toast toast=Toast.makeText(getApplicationContext(),"Please fill English Word & Myanmar Word",Toast.LENGTH_SHORT);
                    toast.show();
                }else{
                    dbManager.insert(english_word, myanmar_word);
                    Toast toast=Toast.makeText(getApplicationContext(),"Successfully Insert!",Toast.LENGTH_SHORT);
                    toast.show();
                    finish();
                    Intent intent = new Intent(getApplicationContext(), DispalyActvity.class);
                    startActivity(intent);
                }


            }
        });

    }
}
