package com.monari.movix.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.monari.movix.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.idSignUpButton) Button midSignUpButton;
    @BindView(R.id.name) EditText mname;
    @BindView(R.id.userName) EditText muserName;
    @BindView(R.id.userEmail) EditText muserEmail;
    @BindView(R.id.userPassword) EditText muserPassword;


    private Button idSignUpButton;
    private EditText name;
    private EditText userName;
    private EditText userEmail;
    private EditText userPassword;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ButterKnife.bind(this);


        //lead to login activity
        midSignUpButton.setOnClickListener(this);
    }
            @Override
            public void onClick(View view) {
             if (view == midSignUpButton){
                 String name = mname.getText().toString();
                 String userName = muserName.getText().toString();
                 String userEmail = muserEmail.getText().toString();
                 String userPassword = muserPassword.getText().toString();
                 if (name.isEmpty()||userName.isEmpty()||userEmail.isEmpty()||userPassword.isEmpty()) {

                     Toast toast = Toast.makeText(getApplicationContext(), "All the fields are required.",Toast.LENGTH_SHORT);

                     TextView toastMessage=(TextView) toast.getView().findViewById(android.R.id.message);
                     toastMessage.setTextColor(Color.BLUE);
                     toast.show();
                 }else{

                     Intent intent = new Intent(SignupActivity.this, ProfileActivity.class);
                     intent.putExtra("name", name);
                     intent.putExtra("userName", userName);
                     intent.putExtra("userEmail", userEmail);
                     intent.putExtra("userPassword", userPassword);
                     startActivity(intent);
            }
        }

    }
}