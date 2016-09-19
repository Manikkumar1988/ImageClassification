package com.example.mani.classifyimg;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent classifyingActivityIntent = new Intent(getApplicationContext(),ClassifyingActivity.class);
        startActivity(classifyingActivityIntent);
        finish();
    }
}
