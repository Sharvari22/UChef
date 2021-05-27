package com.example.uchef;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.transition.AutoTransition;
import androidx.transition.TransitionManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

public class MyAccountFragment extends Fragment {

    View view;
    LinearLayout hidden_layout1, hidden_layout2;
    CardView cardView1, cardView2, cardView3, cardView4, cardView5;
    ImageView arrow_more1, arrow_more2, arrow_more3, arrow_more4, arrow_more5, back;
    EditText chef_fullname, chef_email, chef_phone, chef_password, chef_address, chef_zip, chef_state, chef_city;
    Button save_btn1, save_btn2;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_myaccount, container, false);

        hidden_layout1 = view.findViewById(R.id.hidden_layout1);
        hidden_layout2 = view.findViewById(R.id.hidden_layout2);

        cardView1 = view.findViewById(R.id.base_cardview1);
        cardView2 = view.findViewById(R.id.base_cardview2);
        cardView3 = view.findViewById(R.id.base_cardview3);
        cardView4 = view.findViewById(R.id.base_cardview4);
        cardView5 = view.findViewById(R.id.base_cardview5);

        arrow_more1 = view.findViewById(R.id.arrow_more1);
        arrow_more2 = view.findViewById(R.id.arrow_more2);
        arrow_more3 = view.findViewById(R.id.arrow_more3);
        arrow_more4 = view.findViewById(R.id.arrow_more4);
        arrow_more5 = view.findViewById(R.id.arrow_more5);

        back = view.findViewById(R.id.back_btn);

        chef_fullname = view.findViewById(R.id.chef_fullname);
        chef_phone = view.findViewById(R.id.chef_phone);
        chef_email = view.findViewById(R.id.chef_email);
        chef_password = view.findViewById(R.id.chef_password);
        chef_address = view.findViewById(R.id.chef_address);
        chef_state = view.findViewById(R.id.chef_state);
        chef_zip = view.findViewById(R.id.chef_zip);
        chef_city = view.findViewById(R.id.chef_city);

        save_btn1 = view.findViewById(R.id.save_btn1);
        save_btn2 = view.findViewById(R.id.save_btn2);




        arrow_more1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hidden_layout1.getVisibility() == View.VISIBLE) {
                    TransitionManager.beginDelayedTransition(cardView1,
                            new AutoTransition());
                    hidden_layout1.setVisibility(View.GONE);
                    arrow_more1.setImageResource(R.drawable.next_arrow);
                }
                else {
                    TransitionManager.beginDelayedTransition(cardView1,
                            new AutoTransition());
                    hidden_layout1.setVisibility(View.VISIBLE);
                    arrow_more1.setImageResource(R.drawable.arrow_close);
                }
            }
        });

        arrow_more2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hidden_layout2.getVisibility() == View.VISIBLE) {
                    TransitionManager.beginDelayedTransition(cardView2,
                            new AutoTransition());
                    hidden_layout2.setVisibility(View.GONE);
                    arrow_more2.setImageResource(R.drawable.next_arrow);
                }
                else {
                    TransitionManager.beginDelayedTransition(cardView2,
                            new AutoTransition());
                    hidden_layout2.setVisibility(View.VISIBLE);
                    arrow_more2.setImageResource(R.drawable.arrow_close);
                }

            }
        });

        arrow_more3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getActivity(), Verification.class));

            }
        });
        arrow_more4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getActivity(), MoreAboutChef.class));

            }
        });

        arrow_more5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return view;
    }
}