package com.monari.movix;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.monari.movix.ui.LoginActivity;
import com.monari.movix.ui.SignupActivity;

public class MainActivity extends AppCompatActivity {

    private Button mGetstartedButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);//run all of the default behaviors for an activity
        setContentView(R.layout.activity_main);//tells the activity which layout to use for the device screen



        mGetstartedButton = (Button) findViewById(R.id.getStartedButton);
        mGetstartedButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);

            }
        });
    }
}