package com.example.memeapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class Main2Activity extends AppCompatActivity {
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        Intent i = getIntent();
        int pos = i.getExtras().getInt("id");
        ImageAdapter ia = new ImageAdapter(this);

        imageView = findViewById(R.id.imageView3);
//        imageView.setImageResource(ia.mThumbIds[pos]);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), ia.mThumbIds[pos]);
        imageView.setImageBitmap(bitmap);
    }

    public boolean onOptionsItemSelected(MenuItem item){
        int ids = item.getItemId();

        if (ids ==android.R.id.home) {
            finish();
        }

        return true;
    }

    public void download_image(View view){
//        Download image code
    }

}
