package com.monari.movix;

import static androidx.constraintlayout.widget.Constraints.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.idLoginButton) Button midLoginButton;
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

        midLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(LoginActivity.this, MoviesActivity.class);
                startActivity(intent);

            }
        });

//
//
//
//        midLoginButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                checkInput ();
//
//            }
//        });
//
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

//    public void checkInput (){
//        String user = idUserName.getText().toString().trim();
//        String email = idUserEmail.getText().toString().trim();
//        String pass = idUserPassword.getText().toString().trim();
//
//        if(user.equals("")||email.equals("")||pass.equals("")){
//            Toast.makeText(LoginActivity.this, "Required", Toast.LENGTH_SHORT).show();
//        }

//        if (isEmail(idUserEmail) == false|| idUserEmail.equals("")) {
//            idUserEmail.setError("Enter valid email!");
//        }
//
//        if (isEmpty(idUserName)) {
//            Toast t = Toast.makeText(this, "You must enter username to login!", Toast.LENGTH_SHORT);
//            t.show();
//        }
//        if (isEmpty(idUserPassword)) {
//            Toast t = Toast.makeText(this, "Password required", Toast.LENGTH_SHORT);
//            t.show();
//        }

    }
}