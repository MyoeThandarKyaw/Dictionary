package com.example.dictionary;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class DisplayDefinitation extends AppCompatActivity {
private TextView txt_english, txt_myanmar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_definitation);
        txt_english=(TextView)findViewById(R.id.txt_english_word);
        txt_myanmar=(TextView)findViewById(R.id.txt_myanmar_word);
        String eng_word = getIntent().getExtras().getString("english_word");
        txt_english.setText(eng_word);
        String myanmar_word = getIntent().getExtras().getString("myanmar_word");
        txt_myanmar.setText(myanmar_word);




    }
}
