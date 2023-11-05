package com.example.usapparelinventoryapp.screens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.usapparelinventoryapp.R;
import com.example.usapparelinventoryapp.screens.CreateColorsScreen;
import com.example.usapparelinventoryapp.screens.CreateLocationsScreen;
import com.example.usapparelinventoryapp.screens.CreatePalletScreen;
import com.example.usapparelinventoryapp.screens.CreateSizesScreen;
import com.example.usapparelinventoryapp.screens.CreateStylesScreen;
import com.example.usapparelinventoryapp.screens.SearchScreen;

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