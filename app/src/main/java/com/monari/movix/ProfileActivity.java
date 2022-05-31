package com.monari.movix;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ProfileActivity extends AppCompatActivity {
    private TextView mProfileName;
    private TextView mUserName;
    private Button mGetStartedButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        mProfileName = (TextView) findViewById(R.id.profileName);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String userName = intent.getStringExtra("userName");
        String userEmail = intent.getStringExtra("userEmail");
        mProfileName.setText("Name: " + name + "\n" +"User Name: " + userName+"\n" +"Email: " + userEmail);


        mGetStartedButton = (Button) findViewById(R.id.getStarted);//(Button) typecasts our view as Button
        mGetStartedButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(ProfileActivity.this, MoviesActivity.class);
                startActivity(intent);

            }
        });

    }
}