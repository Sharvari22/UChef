package com.example.uchef;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class DishDetailActivity extends AppCompatActivity {

    ImageView dishImage;
    TextView dishDescription, dishCategory, dishAllergy;
    String key="";
    String imageUrl="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dish_detail);

        dishImage = findViewById(R.id.ivImage);
        dishDescription = findViewById(R.id.txtDescription);
        dishCategory = findViewById(R.id.tvCategory);
        dishAllergy = findViewById(R.id.tvAllergy);


        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {

            dishDescription.setText(bundle.getString("Description"));
            key = bundle.getString("keyValue");
            imageUrl = bundle.getString("Image");
            //dishImage.setImageResource(bundle.getInt("Image"));

            Glide.with(this).load(bundle.getString("Image")).into(dishImage);

            dishAllergy.setText(bundle.getString("Allergy"));
        }
    }

    public void btnDeleteDish(View view) {

        final DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Dish");
        FirebaseStorage storage = FirebaseStorage.getInstance();

        StorageReference storageReference = storage.getReferenceFromUrl(imageUrl);

        storageReference.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {

                reference.child(key).removeValue();
                Toast.makeText(DishDetailActivity.this, "Dish Deleted", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), Dishes.class));
                finish();
            }
        });
    }
}