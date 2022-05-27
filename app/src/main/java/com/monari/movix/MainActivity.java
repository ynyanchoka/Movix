package com.monari.movix;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button mGetStartedButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);//run all of the default behaviors for an activity
        setContentView(R.layout.activity_main);//tells the activity which layout to use for the device screen

        mGetStartedButton = (Button) findViewById(R.id.getStartedButton);//(Button) typecasts our view as Button
        mGetStartedButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(MainActivity.this, "Welcome to Movix", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainActivity.this, SignupActivity.class);
                startActivity(intent);

            }
        });
    }
}