package com.example.memeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

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

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    public void button_search(View view){
        // Python info retrieval code here
        try {
            URL url = new URL("http://192.168.1.12:5000/");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer cont = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                cont.append(inputLine);
            }
            Toast.makeText(getApplicationContext(), cont, Toast.LENGTH_LONG ).show();
            in.close();
        }catch (Exception ie){
            ie.printStackTrace();
        }


    }
}
