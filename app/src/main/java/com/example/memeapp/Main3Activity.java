package com.example.memeapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;

public class Main3Activity extends AppCompatActivity {
    int pos;
    ImageView imageView;
    ImageAdapter ia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        Intent i = getIntent();
        pos = i.getExtras().getInt("id");
        ia = new ImageAdapter(this);

        imageView = findViewById(R.id.imageView4);
        imageView.setImageResource(ia.mExmaples[pos]);
    }

    public boolean onOptionsItemSelected(MenuItem item){
        int ids = item.getItemId();

        if (ids ==android.R.id.home) {
            finish();
        }

        return true;
    }
}
