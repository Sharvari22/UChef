package com.example.uchef;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

public class MoreAboutChef extends AppCompatActivity {

    TextView tvCuisines, tvLanguage;
    boolean[] selectedCuisines, selectedLanguages;
    ArrayList<Integer> cuisineList = new ArrayList<>();
    ArrayList<Integer> langList = new ArrayList<>();
    String[] cuisineArray = {"French", "Italian", "Chinese", "Mexican", "Thai", "Korean", "Lebanese", "Indian", "Vietnamese", "Greek"};
    String[] langArray = {"English", "Hindi", "Marathi", "Spanish", "French", "Punjabi", "Telugu", "Tamil", "Konkani", "Malayalam"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moreabout_chef);

        tvCuisines = findViewById(R.id.tvCuisines);
        tvLanguage = findViewById(R.id.tvLanguage);

        //initialize selectedCuisines array & selectedLanguages array
        selectedCuisines = new boolean[cuisineArray.length];
        selectedLanguages = new boolean[langArray.length];

        tvCuisines.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //initialize alert dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(MoreAboutChef.this);
                //set title
                builder.setTitle("Select Cuisines");
                //set dialog non cancelable
                builder.setCancelable(false);

                builder.setMultiChoiceItems(cuisineArray, selectedCuisines, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        //check condition
                        if (isChecked) {
                            //when checkbox selected
                            //add position in cuisine list
                            cuisineList.add(which);
                            //sort cuisine list
                            Collections.sort(cuisineList);
                        } else {
                            //when checkbox unselected
                            //remove position from cuisine list
                            cuisineList.remove(which);
                        }
                    }
                });

                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //initialize string builder
                        StringBuilder stringBuilder = new StringBuilder();
                        //use for loop
                        for (int j = 0; j < cuisineList.size(); j++) {
                            //concat array value
                            stringBuilder.append(cuisineArray[cuisineList.get(j)]);
                            //check condition
                            if (j != cuisineList.size()-1) {
                                //when j value not equals to cuisine list size-1
                                //add comma
                                stringBuilder.append(", ");
                            }
                        }
                        //set text on text view
                        tvCuisines.setText(stringBuilder.toString());
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //dismiss dialog
                        dialog.dismiss();
                    }
                });

                builder.setNeutralButton("Clear All", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //use for loop
                        for (int j = 0; j < selectedCuisines.length; j++) {
                            //remove all selection
                            selectedCuisines[j] = false;
                            //clear cuisine list
                            cuisineList.clear();
                            //clear text view
                            tvCuisines.setText("");
                        }
                    }
                });
                //show dialog
                builder.show();
            }

        });


        tvLanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //initialize alert dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(MoreAboutChef.this);
                //set title
                builder.setTitle("Languages Spoken");
                //set dialog non cancelable
                builder.setCancelable(false);

                builder.setMultiChoiceItems(langArray, selectedLanguages, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        //check condition
                        if (isChecked) {
                            //when checkbox selected
                            //add position in cuisine list
                            langList.add(which);
                            //sort cuisine list
                            Collections.sort(langList);
                        } else {
                            //when checkbox unselected
                            //remove position from cuisine list
                            langList.remove(which);
                        }
                    }
                });

                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //initialize string builder
                        StringBuilder stringBuilder = new StringBuilder();
                        //use for loop
                        for (int j = 0; j < langList.size(); j++) {
                            //concat array value
                            stringBuilder.append(langArray[langList.get(j)]);
                            //check condition
                            if (j != langList.size()-1) {
                                //when j value not equals to cuisine list size-1
                                //add comma
                                stringBuilder.append(", ");
                            }
                        }
                        //set text on text view
                        tvLanguage.setText(stringBuilder.toString());
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //dismiss dialog
                        dialog.dismiss();
                    }
                });

                builder.setNeutralButton("Clear All", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //use for loop
                        for (int j = 0; j < selectedLanguages.length; j++) {
                            //remove all selection
                            selectedLanguages[j] = false;
                            //clear cuisine list
                            langList.clear();
                            //clear text view
                            tvLanguage.setText("");
                        }
                    }
                });
                //show dialog
                builder.show();
            }

        });

    }
}