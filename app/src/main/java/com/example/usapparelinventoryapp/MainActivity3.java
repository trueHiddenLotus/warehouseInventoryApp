package com.example.usapparelinventoryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
    }

    public void openMainActivity(View view) {
        startActivity(new Intent(this, MainActivity.class));
    }

    public void openMainActivity4(View view) {
        startActivity(new Intent(this, MainActivity4.class));
    }
    public void openMainActivity5(View view) {
        startActivity(new Intent(this, MainActivity5.class));
    }

    public void openMainActivity6(View view) {
        startActivity(new Intent(this, MainActivity6.class));
    }

    public void openMainActivity7(View view) {
        startActivity(new Intent(this, MainActivity7.class));
    }
    public void openMainActivity8(View view) {
        startActivity(new Intent(this, MainActivity8.class));
    }
}