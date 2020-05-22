package com.example.memeapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;

public class Main3Activity extends AppCompatActivity {
    int pos;
    int[] arr;
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
