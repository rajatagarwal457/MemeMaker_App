package com.example.memeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridView gridv = findViewById(R.id.grid1);
        gridv.setAdapter(new ImageAdapter(this));

        gridv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getApplicationContext(), Main2Activity.class);
                i.putExtra("id",position);
                startActivity(i);
            }
        });
    }

    public void button_search(View view){
        // Python info retrieval code here
    }
}
