package com.monari.movix;

import static androidx.constraintlayout.widget.Constraints.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.idLoginButton) Button midLoginButton;
    @BindView(R.id.signUpButton) Button mSignUpButton;
    @BindView(R.id.idUserEmail) EditText midUserEmail;
    @BindView(R.id.idUserName) EditText midUserName;
    @BindView(R.id.idUserPassword) EditText imdUserPassword;

    Button idLoginButton;
    EditText idUserEmail;
    EditText idUserName;
    EditText idUserPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        mSignUpButton = (Button) findViewById(R.id.signUpButton);
        mSignUpButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);

            }
        });

        midLoginButton.setOnClickListener(this);

    }
            @Override
            public void onClick(View view) {
                if(view == midLoginButton) {
                    String username = midUserName.getText().toString();
                    String password = imdUserPassword.getText().toString();
                    String email = midUserEmail.getText().toString();
                    if (username.isEmpty() || password.isEmpty()|| email.isEmpty() ){

                        Toast toast = Toast.makeText(getApplicationContext(), "All the fields are required.",Toast.LENGTH_SHORT);

                        TextView toastMessage=(TextView) toast.getView().findViewById(android.R.id.message);
                        toastMessage.setTextColor(Color.BLUE);
                        toast.show();
                    }
                    else {

                        Intent intent = new Intent(LoginActivity.this, MoviesActivity.class);
//                        intent.putExtra("username", username);
//                        intent.putExtra("password", password);
//                        intent.putExtra("email", email);

                        startActivity(intent);
                    }

            }

    }
}