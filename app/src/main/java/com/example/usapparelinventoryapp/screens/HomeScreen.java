package com.example.usapparelinventoryapp.screens;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.usapparelinventoryapp.R;

public class HomeScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
    }

    public void openMainActivity(View view) {
        startActivity(new Intent(this, CreatePalletScreen.class));
    }

    public void openMainActivity4(View view) {
        startActivity(new Intent(this, CreateStylesScreen.class));
    }
    public void openMainActivity5(View view) {
        startActivity(new Intent(this, CreateLocationsScreen.class));
    }

    public void openMainActivity6(View view) {
        startActivity(new Intent(this, CreateColorsScreen.class));
    }

    public void openMainActivity7(View view) {
        startActivity(new Intent(this, CreateSizesScreen.class));
    }
    public void openMainActivity8(View view) {
        startActivity(new Intent(this, SearchScreen.class));
    }
}