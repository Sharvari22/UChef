package com.example.uchef;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

public class HomeFragment extends Fragment {

    Button addDishBtn, foodGallery, scheduleBtn;
    ImageView chefImg;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home2, container, false);
        chefImg = view.findViewById(R.id.chefImg);
        addDishBtn = view.findViewById(R.id.addDishBtn);
        foodGallery = view.findViewById(R.id.foodGallery);
        scheduleBtn = view.findViewById(R.id.schedule);

        chefImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), MyAccountFragment.class));
            }
        });
        addDishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), AddDishes.class));
            }
        });

        foodGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), FoodGallery.class));
            }
        });

        scheduleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), ScheduleFragment.class));
            }
        });

        return view;
    }
}