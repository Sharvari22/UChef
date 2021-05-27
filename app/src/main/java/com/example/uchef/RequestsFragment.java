package com.example.uchef;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class RequestsFragment extends Fragment {

    RecyclerView recyclerView;
    List<UserData> userDataList;
    UserData userData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_requests, container, false);

        /*recyclerView = view.findViewById(R.id.requestRecyclerView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 1);
        recyclerView.setLayoutManager(gridLayoutManager);

        userDataList = new ArrayList<>();

        userData = new UserData("Tia Bhuva", "Hunger Meal", "03-05-2021 Monday", R.drawable.user1);
        userDataList.add(userData);

        userData = new UserData("Karthik Verma", "Pepper Chicken Pizza", "21-05-2021 Monday", R.drawable.user2);
        userDataList.add(userData);

        userData = new UserData("Tia Bhuva", "Hunger Meal", "18-05-2021 Monday", R.drawable.user3);
        userDataList.add(userData);

        RequestAdapter requestAdapter = new RequestAdapter(getActivity(), userDataList);
        recyclerView.setAdapter(requestAdapter);*/
        return view;
    }
}