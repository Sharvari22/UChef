package com.example.uchef;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AddDishes extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Spinner selectionSpinner;

    Uri uri;
    String imageUrl;
    ImageView back;
    ImageButton uploadDishImg;
    EditText dish_title, dish_detail, dish_price;
    CheckBox checkbox1, checkbox2, checkbox3, checkbox4, checkbox5, checkbox6;
    Button addDishBtn, addedDish;

    AlertDialog b;
    AlertDialog.Builder dialogBuilder;

    FirebaseDatabase database;
    DatabaseReference reference;
    DishData dishData;

    int i = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_dishes);

        reference = database.getInstance().getReference().child("Dish");

        back = findViewById(R.id.back_btn);

        uploadDishImg = findViewById(R.id.upload_dish);
        dish_title = findViewById(R.id.dish_title);
        dish_detail = findViewById(R.id.dish_detail);
        dish_price = findViewById(R.id.dish_price);
        checkbox1 = findViewById(R.id.checkbox1);
        checkbox2 = findViewById(R.id.checkbox2);
        checkbox3 = findViewById(R.id.checkbox3);
        checkbox4 = findViewById(R.id.checkbox4);
        checkbox5 = findViewById(R.id.checkbox5);
        checkbox6 = findViewById(R.id.checkbox6);
        addDishBtn = findViewById(R.id.addDish_btn);
        addedDish = findViewById(R.id.addedDish);



        initSelectionSpinner();

        uploadDishImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPicker = new Intent(Intent.ACTION_PICK);
                photoPicker.setType("image/*");
                startActivityForResult(photoPicker, 1);
            }
        });

        addDishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                uploadDishImg();
            }
        });

        /*reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    i = (int) snapshot.getChildrenCount();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            uri = data.getData();
            uploadDishImg.setImageURI(uri);
        } else {
            Toast.makeText(this, "Please select an image!", Toast.LENGTH_SHORT).show();
        }
    }

    public void uploadDishImg() {
        StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("DishImage").child(uri.getLastPathSegment());

        dialogBuilder = new AlertDialog.Builder(AddDishes.this);
        LayoutInflater inflater = (LayoutInflater) getSystemService( Context.LAYOUT_INFLATER_SERVICE );
        View dialogView = inflater.inflate(R.layout.progress_dialog_layout, null);
        dialogBuilder.setView(dialogView);
        dialogBuilder.setCancelable(false);
        b = dialogBuilder.create();


        storageReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                while (!uriTask.isComplete());
                Uri urlImage = uriTask.getResult();
                imageUrl = urlImage.toString();
                uploadDishDetails();

                b.dismiss();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                b.dismiss();
            }
        });
    }

    public void uploadDishDetails() {

        String c1 = "Gluten";
        String c2 = "Soy";
        String c3 = "Peanuts";
        String c4 = "Milk";
        String c5 = "Shellfish";
        String c6 = "Tree Nuts";

        dishData = new DishData(dish_title.getText().toString(), dish_detail.getText().toString(), dish_price.getText().toString(),
                imageUrl);

        if (checkbox1.isChecked()) {
            dishData.setCheck1(c1);
            //reference.child(String.valueOf(i+1)).setValue(dishData);
        }else {

        }
        if (checkbox2.isChecked()) {
            dishData.setCheck2(c2);
            //reference.child(String.valueOf(i+1)).setValue(dishData);
        }else {

        }
        if (checkbox3.isChecked()) {
            dishData.setCheck3(c3);
            //reference.child(String.valueOf(i+1)).setValue(dishData);
        }else {

        }
        if (checkbox4.isChecked()) {
            dishData.setCheck4(c4);
            //reference.child(String.valueOf(i+1)).setValue(dishData);
        }else {

        }
        if (checkbox5.isChecked()) {
            dishData.setCheck5(c5);
            //reference.child(String.valueOf(i+1)).setValue(dishData);
        }else {

        }
        if (checkbox6.isChecked()) {
            dishData.setCheck6(c6);
            //reference.child(String.valueOf(i+1)).setValue(dishData);
        }else {

        }

        dishData.setSpinner(selectionSpinner.getSelectedItem().toString());
        //reference.child(String.valueOf(i+1)).setValue(dishData);


        String myCurrentDateTime = DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
        FirebaseDatabase.getInstance().getReference("Dish").child(myCurrentDateTime).setValue(dishData).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if (task.isSuccessful()) {
                    Toast.makeText(AddDishes.this, "Dish Added Successfully!", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(AddDishes.this, "Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void initSelectionSpinner() {
        selectionSpinner = findViewById(R.id.category_spinner);

        //set spinner activity as the item selected listener
        selectionSpinner.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add(0, "Select category of dish:");
        categories.add("Appetizer");
        categories.add("Main Course");
        categories.add("Desert");
        categories.add("Beverages");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,categories );
        // Drop down layout style
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        selectionSpinner.setAdapter(dataAdapter);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this, selectionSpinner.getSelectedItem() + " selected", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void btn_viewDishes(View view) {

        startActivity(new Intent(this, Dishes.class));
    }
}