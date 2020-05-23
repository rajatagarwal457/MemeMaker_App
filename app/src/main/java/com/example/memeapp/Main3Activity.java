package com.example.memeapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class Main3Activity extends AppCompatActivity {
    int pos;
    int[] arr;
    ImageView imageView;
    OutputStream outputStream;
    ImageAdapter ia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

//        Intent i = getIntent();
//        pos = i.getExtras().getInt("id");
//        ia = new ImageAdapter(this);

        Intent i = getIntent();
        ia = new ImageAdapter(this);
        pos = i.getExtras().getInt("id");
        String str = i.getExtras().getString("pos_arr");
        if(str != null) {
            arr = parse_String(str);
            sort_res(arr);
        }

        imageView = findViewById(R.id.imageView4);
        imageView.setImageResource(ia.mExmaples[pos]);

        ImageButton imbBtn = findViewById(R.id.imageButton3);
        imbBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(Main3Activity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED &&
                        ContextCompat.checkSelfPermission(Main3Activity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
                {
                    ActivityCompat.requestPermissions(Main3Activity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 123);
                    ActivityCompat.requestPermissions(Main3Activity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 123);
                    Toast.makeText(Main3Activity.this, "Need Permission to access storage for Downloading Image", Toast.LENGTH_LONG).show();
                }else {
                    BitmapDrawable drawable = (BitmapDrawable) imageView.getDrawable();
                    Bitmap bitmap = drawable.getBitmap();
                    String dir = getApplicationContext().getFilesDir().getPath();

                    File file = new File(dir, System.currentTimeMillis()+".jpg");
                    try {
                        outputStream = new FileOutputStream(file);
                    }catch (FileNotFoundException ie){
                        ie.printStackTrace();
                    }
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
                    Toast.makeText(Main3Activity.this, "Image saved to gallery.", Toast.LENGTH_LONG).show();
                    try {
                        outputStream.flush();
                    }catch (IOException ie){
                        ie.printStackTrace();
                    }
                    try {
                        outputStream.close();
                    }catch (IOException ie){
                        ie.printStackTrace();
                    }
                }
            }
        });
    }

    public boolean onOptionsItemSelected(MenuItem item){
        int ids = item.getItemId();

        if (ids ==android.R.id.home) {
            finish();
        }

        return true;
    }

    public void sort_res(int[] arr){
        int size = arr.length;
        for(int i=0; i<size; i++){
            int tmp = ia.mThumbIds[i];
            ia.mThumbIds[i] = ia.mThumbIds[arr[i]];
            ia.mThumbIds[arr[i]] = tmp;

            tmp = ia.mExmaples[i];
            ia.mExmaples[i] = ia.mExmaples[arr[i]];
            ia.mExmaples[arr[i]] = tmp;
        }
    }

    public int[] parse_String(String input){
        String output;
        output = input.replace("[","");
        output = output.replace("]","");
        String[] str_array = output.split(",");
        int size = str_array.length;
        int[] arr = new int[size-1];
        for(int i=0; i<size-1; i++){
            str_array[i] = str_array[i].replaceAll("\\s","");
            arr[i] = Integer.parseInt(str_array[i]);
        }
        return arr;
    }
}
