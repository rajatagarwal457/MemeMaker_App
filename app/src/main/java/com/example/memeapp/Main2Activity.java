package com.example.memeapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import static android.media.MediaScannerConnection.scanFile;

public class Main2Activity extends AppCompatActivity {

    private Uri passImageUri;
    ImageButton processImage;
    EditText addTextEditText;
    private static int IMAGE_REQ = 44;
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
        imageView.setImageResource(ia.mThumbIds[pos]);

        processImage =  findViewById(R.id.imageButton2);
        addTextEditText = findViewById(R.id.editText);

        processImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(addTextEditText.getText().toString()))
                {
                    addTextEditText.setError("Enter the text");
                }else
                {
                    String text = addTextEditText.getText().toString();
                    addTextEditText.setText("");
                    addTextOnImage(text);
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

    public void download_image(View view){
//        Download image code
    }

    private void addTextOnImage(String text) {

        //pass the data to add it in image
        Intent intent = new Intent(Main2Activity.this,TextOnImage.class);
        Bundle bundle = new Bundle();
        bundle.putString(TextOnImage.IMAGE_IN_URI,passImageUri.toString()); //image uri
        bundle.putString(TextOnImage.TEXT_COLOR,"#27ceb8");                 //initial color of the text
        bundle.putFloat(TextOnImage.TEXT_FONT_SIZE,20.0f);                  //initial text size
        bundle.putString(TextOnImage.TEXT_TO_WRITE,text);                   //text to be add in the image
        intent.putExtras(bundle);
        startActivityForResult(intent, TextOnImage.TEXT_ON_IMAGE_REQUEST_CODE); //start activity for the result
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == IMAGE_REQ)
        {
            if(resultCode == RESULT_OK)
            {
                passImageUri = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), passImageUri);
                    imageView.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        if(requestCode == TextOnImage.TEXT_ON_IMAGE_REQUEST_CODE)
        {
            if(resultCode == TextOnImage.TEXT_ON_IMAGE_RESULT_OK_CODE)
            {
                Uri resultImageUri = Uri.parse(data.getStringExtra(TextOnImage.IMAGE_OUT_URI));
                try {
                    Bitmap  bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), resultImageUri);
                    imageView.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else if(resultCode == TextOnImage.TEXT_ON_IMAGE_RESULT_FAILED_CODE)
            {
                String errorInfo = data.getStringExtra(TextOnImage.IMAGE_OUT_ERROR);
                Log.d("MainActivity", "onActivityResult: "+errorInfo);
            }
        }
        super.onActivityResult(requestCode,resultCode,data);
    }

}
