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

    } // onCreate
} // Overview