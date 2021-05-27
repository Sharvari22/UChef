package com.example.uchef;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class DishAdapter extends RecyclerView.Adapter<DishViewHolder> {


    private Context mContext;
    private List<DishData> myDishList;


    public DishAdapter(Context mContext, List<DishData> myDishList) {
        this.mContext = mContext;
        this.myDishList = myDishList;
    }


    @Override
    public DishViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_row_dish, parent, false);

        return new DishViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull DishViewHolder holder, int position) {

        Glide.with(mContext).load(myDishList.get(position).getDishImage()).into(holder.imageView);

        //holder.imageView.setImageResource(myDishList.get(position).getDishImage());
        holder.mTitle.setText(myDishList.get(position).getDishTitle());
        holder.mDescription.setText(myDishList.get(position).getDishDescription());
        holder.mPrice.setText(myDishList.get(position).getDishPrice());

        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext, DishDetailActivity.class);
                intent.putExtra("Image", myDishList.get(holder.getBindingAdapterPosition()).getDishImage());
                intent.putExtra("Description", myDishList.get(holder.getBindingAdapterPosition()).getDishDescription());
                intent.putExtra("Allergy", myDishList.get(holder.getBindingAdapterPosition()).getSpinner());
                intent.putExtra("keyValue", myDishList.get(holder.getBindingAdapterPosition()).getKey());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return myDishList.size();
    }
}

class DishViewHolder extends RecyclerView.ViewHolder {

    ImageView imageView;
    TextView mTitle, mDescription, mPrice;
    CardView mCardView;

    public DishViewHolder(View itemView) {
        super(itemView);

        imageView = itemView.findViewById(R.id.ivImage);
        mTitle = itemView.findViewById(R.id.tvTitle);
        mDescription = itemView.findViewById(R.id.tvDescription);
        mPrice = itemView.findViewById(R.id.tvPrice);
        mCardView = itemView.findViewById(R.id.dishCardView);
    }
}
