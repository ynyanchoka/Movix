package com.monari.movix;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignupActivity extends AppCompatActivity {
    Button signUp;
    EditText name;
    EditText userName;
    EditText userEmail;
    EditText userPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        signUp = (Button)findViewById(R.id.signUpButton);
        userName = (EditText)findViewById(R.id.userName);
        userEmail = (EditText)findViewById(R.id.userEmail);
        userPassword = (EditText)findViewById(R.id.userPassword);
        name = (EditText)findViewById(R.id.name);


        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkUserInput ();

            }
        });
    }
    boolean isEmail(EditText text) {
        CharSequence email = text.getText().toString();
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }

    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }

    public void checkUserInput (){
//        String user = userName.getText().toString().trim();
//        String pass = userPassword.getText().toString().trim();
//        String email = userEmail.getText().toString().trim();
//        String personName = name.getText().toString().trim();
        if (isEmail(userEmail) == false|| userEmail.equals("")) {
            userEmail.setError("Enter valid email!");
        }

        if (isEmpty(userName)) {
            Toast t = Toast.makeText(this, "You must enter username to signup!", Toast.LENGTH_SHORT);
            t.show();
        }
        if (isEmpty(name)) {
            Toast t = Toast.makeText(this, "Name required", Toast.LENGTH_SHORT);
            t.show();
        }
        if (isEmpty(userPassword)) {
            Toast t = Toast.makeText(this, "Password required", Toast.LENGTH_SHORT);
            t.show();
        }
//
//        if (user.equals("")|| pass.equals ("")||email.equals("")){
//            Toast.makeText(this, "You must enter name to signup", Toast.LENGTH_SHORT).show();}

    }
}