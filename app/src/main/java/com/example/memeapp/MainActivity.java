package com.example.memeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.MenuItem;
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
            EditText edt = findViewById(R.id.editText2);
            String query = edt.getText().toString();
            System.out.println("Here is the query "+query);
            URL url = new URL("http://192.168.1.12:5000/"+query);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer cont = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                cont.append(inputLine);
            }

            int[] output = parse_String(cont.toString());
            for(int each: output){
                System.out.println(each);
            }
            Toast.makeText(MainActivity.this, "Search complete", Toast.LENGTH_LONG ).show();
            search_results(output,cont.toString());
            in.close();
        }catch (Exception ie){
            ie.printStackTrace();
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

    public void search_results(int[] arr, final String str){
        final ImageAdapter ia = new ImageAdapter(this);
        int size = arr.length;
        for(int i=0; i<size; i++){
            int tmp = ia.mThumbIds[i];
            ia.mThumbIds[i] = ia.mThumbIds[arr[i]];
            ia.mThumbIds[arr[i]] = tmp;

            tmp = ia.mExmaples[i];
            ia.mExmaples[i] = ia.mExmaples[arr[i]];
            ia.mExmaples[arr[i]] = tmp;
        }
        System.out.println(""+str);
        GridView gridv = findViewById(R.id.grid1);
        gridv.setAdapter(ia);

        gridv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getApplicationContext(), Main2Activity.class);
                i.putExtra("id",position);
                System.out.println(""+str);
                i.putExtra("pos_arr",str);
                startActivity(i);
            }
        });
    }

}
