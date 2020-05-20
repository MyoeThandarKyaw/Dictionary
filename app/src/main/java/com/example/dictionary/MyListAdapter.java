package com.example.dictionary;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class MyListAdapter extends ArrayAdapter<Word> {

        //the list values in the List of type hero
        List<Word> wordList;

        //activity context
        Context context;

        //the layout resource file for the list items
        int resource;
        private DBManager dbManager;


        //constructor initializing the values
    public MyListAdapter(Context context, int resource, List<Word> wordList) {
            super(context, resource, wordList);
            this.context = context;
            this.resource = resource;
            this.wordList = wordList;
        }

        //this will return the ListView Item as a View
        @NonNull
        @Override
        public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            dbManager = new DBManager(context);
            dbManager.open();
            //we need to get the view of the xml for our list item
            //And for this we need a layoutinflater
            LayoutInflater layoutInflater = LayoutInflater.from(context);

            //getting the view
            View view = layoutInflater.inflate(resource, null, false);

            //getting the view elements of the list from the view
            TextView txt_no=view.findViewById(R.id.txt_no);
            TextView txt_eng = view.findViewById(R.id.txt_english_word);
            TextView txt_myanmar = view.findViewById(R.id.txt_myanmar_word);
            ImageButton btn_update = view.findViewById(R.id.btn_update);
            ImageButton btn_delete = view.findViewById(R.id.btn_delete);

            //getting the hero of the specified position
            Word word = wordList.get(position);

            //adding values to the list item
            txt_no.setText((position+1)+"");
            txt_eng.setText(word.getEng_word());
            txt_myanmar.setText(word.getMyanmar_word());

            btn_update.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    Toast toast=Toast.makeText(getContext(),"Click Update",Toast.LENGTH_SHORT);
                    toast.show();
                }
            });

            //adding a click listener to the button to remove item from the list
            btn_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //we will call this method to remove the selected value from the list
                    //we are passing the position which is to be removed in the method
                    removeWord(position);
                }
            });

            //finally returning the view
            return view;
        }

        //this method will remove the item from the list
        private void removeWord(final int position) {
            //Creating an alert dialog to confirm the deletion
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Are you sure you want to delete this?");

            //if the response is positive in the alert
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                    //removing the item
                    Word word = wordList.get(position);
                    wordList.remove(position);
                    dbManager.delete(word.getEng_word());
                    //reloading the list
                    notifyDataSetChanged();
                    //Word word = wordList.get(position);
                    Log.e("Position",position+"");


                }
            });

            //if response is negative nothing is being done
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });

            //creating and displaying the alert dialog
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }

}