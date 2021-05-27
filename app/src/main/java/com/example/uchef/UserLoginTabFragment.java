package com.example.uchef;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class UserLoginTabFragment extends Fragment {

    private EditText email,pass;
    private TextView forgetpass;
    private Button login;
    float b = 0;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int id = view.getId();
            switch (id) {
                case R.id.loginbutton:
                    signInWithEmailAndPassword();
                    break;
            }
        }
    };

    private void signInWithEmailAndPassword() {
        try{
            if(!email.getText().toString().isEmpty() && !pass.getText().toString().isEmpty()){
                mFirebaseAuth.signInWithEmailAndPassword(email.getText().toString(),
                        pass.getText().toString())
                        .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                String message;
                                if (task.isSuccessful()) {
                                    Toast.makeText(getContext(),"You have been logged in successfully",Toast.LENGTH_SHORT).show();
                                } else {
                                    message = "fail signInWithEmailAndPassword";
                                }
                                //mPasswordTextView.setText(message);
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });
            } else {
                Toast.makeText(getContext(),"Please fill in the fields first!",Toast.LENGTH_SHORT).show();
            }
        }catch(Exception e){
            Toast.makeText(getContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.userlogin_tab_fragment, container, false);

        email = (EditText)v.findViewById(R.id.email);
        pass = (EditText)v.findViewById(R.id.pass);
        forgetpass = (TextView)v.findViewById(R.id.forget_pass);
        login = (Button)v.findViewById(R.id.loginbutton);
        mFirebaseAuth = FirebaseAuth.getInstance();
        login.setOnClickListener(mOnClickListener);

        email.setTranslationX(800);
        pass.setTranslationX(800);
        forgetpass.setTranslationX(800);
        login.setTranslationX(800);

        email.setAlpha(b);
        pass.setAlpha(b);
        forgetpass.setAlpha(b);
        login.setAlpha(b);

        email.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        pass.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        forgetpass.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        login.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(700).start();


        return v;
    }
}