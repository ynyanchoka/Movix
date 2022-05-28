package com.monari.movix;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;

public class SignupActivity extends AppCompatActivity {
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
        midSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });



//
//
//        idSignUpButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                checkUserInput ();
//
//            }
//        });
//    }
//    boolean isEmail(EditText text) {
//        CharSequence email = text.getText().toString();
//        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
//    }
//
//    boolean isEmpty(EditText text) {
//        CharSequence str = text.getText().toString();
//        return TextUtils.isEmpty(str);
//    }
//
//    public void checkUserInput (){
//
//        if (isEmail(userEmail) == false|| userEmail.equals("")) {
//            userEmail.setError("Enter valid email!");
//        }
//
//        if (isEmpty(userName)) {
//            Toast t = Toast.makeText(this, "You must enter username to signup!", Toast.LENGTH_SHORT);
//            t.show();
//        }
//        if (isEmpty(name)) {
//            Toast t = Toast.makeText(this, "Name required", Toast.LENGTH_SHORT);
//            t.show();
//        }
//        if (isEmpty(userPassword)) {
//            Toast t = Toast.makeText(this, "Password required", Toast.LENGTH_SHORT);
//            t.show();
//        }
//
//
    }
}