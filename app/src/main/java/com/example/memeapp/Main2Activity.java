package com.example.memeapp;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.Layout;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.MotionEvent;
import android.view.View.OnTouchListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class Main2Activity extends AppCompatActivity {
    ImageView imageView;
    ImageView egView;
    ImageAdapter ia;
    OutputStream outputStream;
    ViewGroup mainLayout;
    int pos;
    public int prevTextViewId = 0;

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
        egView = findViewById(R.id.imageView2);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), ia.mThumbIds[pos]);
        imageView.setImageBitmap(bitmap);

        egView.setImageResource(ia.mExmaples[pos]);

        ImageButton imbBtn = findViewById(R.id.save_image);
        imbBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(Main2Activity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED &&
                        ContextCompat.checkSelfPermission(Main2Activity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
                {
                    ActivityCompat.requestPermissions(Main2Activity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 123);
                    ActivityCompat.requestPermissions(Main2Activity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 123);
                    Toast.makeText(Main2Activity.this, "Need Permission to access storage for Downloading Image", Toast.LENGTH_LONG).show();
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
                    Toast.makeText(Main2Activity.this, "Image saved to gallery.", Toast.LENGTH_LONG).show();
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

    public void example_image(View view){
        Intent i = new Intent(getApplicationContext(), Main3Activity.class);
        i.putExtra("id",pos);
        startActivity(i);
    }

    @SuppressLint("WrongViewCast")
    public void add_textview(View view){
        EditText text;
        RelativeLayout layout = (RelativeLayout)findViewById(R.id.layout);
        text = (EditText)findViewById(R.id.editText);
        TextView textView = new TextView(this);
        int curTextViewId = prevTextViewId + 1;
        textView.setId(curTextViewId);
        textView.setText(text.getText().toString());
        textView.setBackgroundResource(R.color.white);
        textView.setTextColor(getResources().getColor(R.color.black));
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(100,100);
        layout.addView(textView, params);
        prevTextViewId = curTextViewId;
    }

   /* public OnTouchListener onTouchListener(){
        return new OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                int xDelta;
                int yDelta;

                final int x = (int) event.getRawX();
                final int y = (int) event.getRawY();

                switch (event.getAction() & MotionEvent.ACTION_MASK) {

                    case MotionEvent.ACTION_DOWN:
                        RelativeLayout.LayoutParams lParams = (RelativeLayout.LayoutParams)
                                view.getLayoutParams();

                        xDelta = x - lParams.leftMargin;
                        yDelta = y - lParams.topMargin;
                        break;

                    case MotionEvent.ACTION_UP:

                        break;

                    case MotionEvent.ACTION_MOVE:
                        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view
                                .getLayoutParams();
                        xDelta = x - layoutParams.leftMargin;
                        yDelta = y - layoutParams.topMargin;
                        layoutParams.leftMargin = x - xDelta;
                        layoutParams.topMargin = y - yDelta;
                        layoutParams.rightMargin = 0;
                        layoutParams.bottomMargin = 0;
                        view.setLayoutParams(layoutParams);
                        break;
                }
                mainLayout.invalidate();
                return true;
            }
        };
    }*/

}
