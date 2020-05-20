package com.example.memeapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class Main2Activity extends AppCompatActivity {
    ImageView imageView;
    ImageAdapter ia;
    int pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        Intent i = getIntent();
        pos = i.getExtras().getInt("id");
        ia = new ImageAdapter(this);

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

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void download_image(View view){
//        Download image code
        if (ContextCompat.checkSelfPermission(Main2Activity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(Main2Activity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(Main2Activity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 123);
            ActivityCompat.requestPermissions(Main2Activity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 123);
            Toast.makeText(Main2Activity.this, "Need Permission to access storage for Downloading Image", Toast.LENGTH_LONG).show();
        }else {
            Drawable drawable = getDrawable(ia.mThumbIds[pos]);
            Bitmap bitmap = ((BitmapDrawable)drawable).getBitmap();
            String savedImageURL = MediaStore.Images.Media.insertImage(
                    getContentResolver(),
                    bitmap,
                    "Image"+pos,
                    "Meme image"
            );
//            Toast.makeText(Main2Activity.this, "Downloading Image", Toast.LENGTH_LONG).show();
//            Uri savedImageURI = Uri.parse(savedImageURL);
            Toast.makeText(Main2Activity.this, "Image saved to gallery.\n" + savedImageURL, Toast.LENGTH_LONG).show();
        }
    }

}
