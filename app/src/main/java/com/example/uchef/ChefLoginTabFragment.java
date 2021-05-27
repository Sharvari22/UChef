package com.example.uchef;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import static com.example.uchef.ChefLoginActivity.onResetPasswordFragment;


public class ChefLoginTabFragment extends Fragment {

    private EditText email,pass;
    private TextView forgetpass;
    private Button login;
    float b = 0;
    private FirebaseAuth firebaseAuth;
    private final String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+.[a-z]+";
    ConstraintLayout parentConstraintLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.cheflogin_tab_fragment, container, false);
        email = (EditText)v.findViewById(R.id.email);
        pass = (EditText)v.findViewById(R.id.pass);
        forgetpass = (TextView)v.findViewById(R.id.forget_pass);
        login = (Button)v.findViewById(R.id.loginbutton);
        firebaseAuth = FirebaseAuth.getInstance();
        parentConstraintLayout = getActivity().findViewById(R.id.parentConstraintLayout);

        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkInputs();
            }


            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        pass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkInputs();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkEmailAndPassword();
            }
        });

        forgetpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onResetPasswordFragment = true;
                setFragment(new ResetPasswordFragment());

            }
        });


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

    private void setFragment(ResetPasswordFragment resetPasswordFragment) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(parentConstraintLayout.getId(), resetPasswordFragment);
        fragmentTransaction.commit();
    }

    private void checkEmailAndPassword() {
        if (email.getText().toString().matches(emailPattern)) {
            if (pass.length() >= 6) {

                login.setEnabled(false);
                login.setTextColor(Color.argb(50, 255, 255, 255));

                firebaseAuth.signInWithEmailAndPassword(email.getText().toString(), pass.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    mainIntent();
                                } else {
                                    login.setEnabled(true);
                                    login.setTextColor(Color.rgb(255, 255, 255));
                                    String error = task.getException().getMessage();
                                    Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            } else {
                Toast.makeText(getActivity(), "Incorrect email or password!", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getActivity(), "Incorrect email or password!", Toast.LENGTH_SHORT).show();
        }
    }

    private void mainIntent() {
        if(ChefLoginActivity.disableCloseBtn){
            ChefLoginActivity.disableCloseBtn=false;
        }else {
            startActivity(new Intent(getActivity(),ChefMainPage.class));
        }
        getActivity().finish();
    }

    private void checkInputs() {
        if (!TextUtils.isEmpty(email.getText())) {
            if (!TextUtils.isEmpty(pass.getText())) {
                login.setEnabled(true);
                login.setTextColor(Color.rgb(255, 255, 255));
            } else {
                login.setEnabled(false);
                login.setTextColor(Color.argb(50, 255, 255, 255));
            }
        } else {
            login.setEnabled(false);
            login.setTextColor(Color.argb(50, 255, 255, 255));
        }
    }

}