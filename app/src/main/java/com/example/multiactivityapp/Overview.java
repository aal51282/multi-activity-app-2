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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

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
        String topic = intent.getStringExtra(MainActivity.TOPIC_TYPE);
        String infoType = intent.getStringExtra(MainActivity.INFO_TYPE);

        // Determine the appropriate raw file
        int rawInfo = getRawInfo(topic, infoType);
        String infoText = readRawTextFile(rawInfo);
        textInfo.setText(infoText);

        // Determine which image to display
        int imageResInfo = getImageInfo(topic, infoType);
        imageInfo.setImageResource(imageResInfo);

    } // onCreate

    /**
     * Determines the appropriate raw text file.
     */
    private int getRawInfo(String topic, String infoType) {
        if (topic.equals("Computer Science")) {
            if (infoType.equals("overview")) {
                return R.raw.cs_overview;
            } else {
                return R.raw.cs_details;
            }
        } else if (topic.equals("Genetics")) {
            if (infoType.equals("overview")) {
                return R.raw.genetics_overview;
            } else {
                return R.raw.genetics_details;
            }
        } else if (topic.equals("Economy")) {
            if (infoType.equals("overview")) {
                return R.raw.economy_overview;
            } else {
                return R.raw.economy_details;
            }
        } else if (topic.equals("Chemistry")) {
            if (infoType.equals("overview")) {
                return R.raw.chemistry_overview;
            } else {
                return R.raw.chemistry_details;
            }
        } else if (topic.equals("Mathematics")) {
            if (infoType.equals("overview")) {
                return R.raw.mathematics_overview;
            } else {
                return R.raw.mathematics_details;
            }
        } // if

        // Message if no raw file exists
        return R.raw.default_info;

    } // getRawInfo

    /**
     * Given the topic and infoType this helper method can read a text file from the raw resources.
     */
    private String readRawTextFile(int res) {
        // Open the raw file
        InputStream inputStream = getResources().openRawResource(res);

        // Create a buffer reader
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        // Use a StringBuilder to accumulate lines from the file
        StringBuilder stringBuilder = new StringBuilder();

        String line;
        try {
            // Read the file line by line
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            } // while
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // Always close the reader to free resources
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            } // try
        } // try

        // Return the file content as a String
        return stringBuilder.toString();

    } // readRawTextFile

    /**
     * Determines the appropriate image file.
     */
    private int getImageInfo(String topic, String infoType) {
        if (topic.equals("Computer Science")) {
            if (infoType.equals("overview")) {
                return R.drawable.cs_overview;
            } else {
                return R.drawable.cs_details;
            }
        } else if (topic.equals("Genetics")) {
            if (infoType.equals("overview")) {
                return R.drawable.genetics_overview;
            } else {
                return R.drawable.genetics_details;
            }
        } else if (topic.equals("Economy")) {
            if (infoType.equals("overview")) {
                return R.drawable.economy_overview;
            } else {
                return R.drawable.economy_details;
            }
        } else if (topic.equals("Chemistry")) {
            if (infoType.equals("overview")) {
                return R.drawable.chemistry_overview;
            } else {
                return R.drawable.chemistry_details;
            }
        } else if (topic.equals("Mathematics")) {
            if (infoType.equals("overview")) {
                return R.drawable.mathematics_overview;
            } else {
                return R.drawable.mathematics_details;
            }
        } // if

        // Default image if no image exists
        return R.drawable.default_image;

    } // getImageInfo

} // Overview