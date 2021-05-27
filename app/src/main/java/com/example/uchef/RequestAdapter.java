package com.example.uchef;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RequestAdapter extends RecyclerView.Adapter<RequestViewHolder>{


    private Context context;
    private List<UserData> userDataList;


    public RequestAdapter(Context context, List<UserData> userDataList) {
        this.context = context;
        this.userDataList = userDataList;
    }

    @Override
    public RequestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_requests, parent, false);

        return new RequestViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RequestViewHolder holder, int position) {

        holder.userImg.setImageResource(userDataList.get(position).getUserImage());
        holder.user_name.setText(userDataList.get(position).getUserName());
        holder.order.setText(userDataList.get(position).getOrder());
        holder.datetime.setText(userDataList.get(position).getDatetime());
        

    }

    @Override
    public int getItemCount() {
        return userDataList.size();
    }
}

class RequestViewHolder extends RecyclerView.ViewHolder {

    ImageView userImg;
    TextView user_name, order, datetime;
    Button accept, decline;
    CardView cardView;

    public RequestViewHolder(View itemView) {
        super(itemView);

        userImg = itemView.findViewById(R.id.userImage);
        user_name = itemView.findViewById(R.id.userName);
        order = itemView.findViewById(R.id.userOrder);
        datetime = itemView.findViewById(R.id.dtOrder);
        accept = itemView.findViewById(R.id.acceptBtn);
        decline = itemView.findViewById(R.id.declineBtn);

        cardView = itemView.findViewById(R.id.requestCardView);
    }
}
