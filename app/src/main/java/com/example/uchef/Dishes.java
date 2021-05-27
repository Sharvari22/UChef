package com.example.uchef;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Dishes extends AppCompatActivity {

    RecyclerView recyclerView;
    List<DishData> myDishList;
    DishData dishData;

    private DatabaseReference databaseReference;
    private ValueEventListener eventListener;

    AlertDialog b;
    AlertDialog.Builder dialogBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dishes);

        recyclerView = findViewById(R.id.dishRecyclerView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(Dishes.this,1);
        recyclerView.setLayoutManager(gridLayoutManager);

        dialogBuilder = new AlertDialog.Builder(Dishes.this);
        LayoutInflater inflater = (LayoutInflater) getSystemService( Context.LAYOUT_INFLATER_SERVICE );
        View dialogView = inflater.inflate(R.layout.progress_dialog_loadingdish, null);
        dialogBuilder.setView(dialogView);
        dialogBuilder.setCancelable(false);
        b = dialogBuilder.create();

        myDishList = new ArrayList<>();


        DishAdapter dishAdapter = new DishAdapter(Dishes.this, myDishList);
        recyclerView.setAdapter(dishAdapter);

        databaseReference = FirebaseDatabase.getInstance().getReference("Dish");

        b.show();
        eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                myDishList.clear();

                for (DataSnapshot dishSnapshot: snapshot.getChildren()) {

                    DishData dishData = dishSnapshot.getValue(DishData.class);
                    dishData.setKey(dishSnapshot.getKey());
                    myDishList.add(dishData);
                }

                dishAdapter.notifyDataSetChanged();
                b.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                b.dismiss();

            }
        });
    }

    public void btn_uploadDish(View view) {

        startActivity(new Intent(this, AddDishes.class));
    }
}