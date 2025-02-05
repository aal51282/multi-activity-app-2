package com.example.multiactivityapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class Overview extends AppCompatActivity {

    private TextView textInfo;
    private ImageView imageInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_overview);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Get UI references
        imageInfo = findViewById(R.id.imageInfo);
        textInfo = findViewById(R.id.textInfo);

        // Get the topic and text from MainActivity
        Intent intent = getIntent();
        String topic = intent.getStringExtra("topic");
        String infoType = intent.getStringExtra("infoType");

        // Determine the appropriate raw file
        int rawInfo = getRawInfo(topic, infoType);
        String infoText = readRawTextFile(rawInfo);
        textInfo.setText(infoText);

    } // onCreate

    /**
     * Determines the appropriate raw text file.
     */
    private int getRawInfo(String topic, String infoType) {
        if (topic.equals("Computer Science")) {
            return infoType.equals("overview") ? R.raw.cs_overview : R.raw.cs_details;
        }
        return R.raw.default_info;
    } // getRawInfo


    /**
     * Reads a text file from the raw resources.
     */
    private String readRawTextFile(int resId) {
        InputStream inputStream = getResources().openRawResource(resId);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int i;
        try {
            i = inputStream.read();
            while (i != -1) {
                byteArrayOutputStream.write(i);
                i = inputStream.read();
            }
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return byteArrayOutputStream.toString();
    }

} // Overview