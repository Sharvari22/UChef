package com.example.uchef;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class UserSignupTabFragment extends Fragment {

    private EditText name, email, phone, pass, confirmpass;
    private Button signup;
    View usersignuptabfragment;
    float b = 0;
    private final String TAG = "AuthenticationFragment";
    private FirebaseAuth mFirebaseAuth;

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int id = view.getId();
            switch (id) {
                case R.id.signup:
                    createUserWithEmailAndPassword();
                    break;
            }
        }
    };

    private void createUserWithEmailAndPassword() {
//        String email = "oemilk@naver.com"; // email address format
//        String password = "123456"; // at least 6 characters
        try{
            if(!email.getText().toString().isEmpty() && !pass.getText().toString().isEmpty()){
                mFirebaseAuth.createUserWithEmailAndPassword(email.getText().toString(), pass.getText().toString())
                        .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                String message;
                                if (task.isSuccessful()) {
                                    Toast.makeText(getContext(),"Your account has been created successfully!",Toast.LENGTH_SHORT).show();
                                } else {
                                    message = "fail createUserWithEmailAndPassword";
                                }
                                //Toast.makeText(getContext(),"Please fill in the fields first!",Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });
            }else {
                Toast.makeText(getContext(),"Please fill in the fields first!",Toast.LENGTH_SHORT).show();
            }
        }catch(Exception e){
            Toast.makeText(getContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }

    }

    public UserSignupTabFragment() {
        // Required empty public constructor
    }

    public static UserSignupTabFragment newInstance() {
        UserSignupTabFragment fragment = new UserSignupTabFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.usersignup_tab_fragment, container, false);

        mFirebaseAuth = FirebaseAuth.getInstance();

        name = (EditText) v.findViewById(R.id.name);
        email = (EditText) v.findViewById(R.id.email);
        pass = (EditText) v.findViewById(R.id.pass);
        phone = (EditText) v.findViewById(R.id.phone);
        confirmpass = (EditText) v.findViewById(R.id.confirmpass);
        signup = (Button) v.findViewById(R.id.signup);
        signup.setOnClickListener(mOnClickListener);


        name.setTranslationX(800);
        email.setTranslationX(800);
        phone.setTranslationX(800);
        pass.setTranslationX(800);
        confirmpass.setTranslationX(800);
        signup.setTranslationX(800);

        name.setAlpha(b);
        email.setAlpha(b);
        phone.setAlpha(b);
        pass.setAlpha(b);
        confirmpass.setAlpha(b);
        signup.setAlpha(b);


        name.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(100).start();
        email.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(200).start();
        phone.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        pass.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        confirmpass.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        signup.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(700).start();

        return v;
    }
}