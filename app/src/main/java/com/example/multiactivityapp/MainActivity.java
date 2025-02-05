package com.example.multiactivityapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

/**
 * This Activity allows the user to select a major and get information in two ways. The first is a
 * short overview about the major and the second is a more detailed option.
 */
public class MainActivity extends AppCompatActivity {

    private Spinner topicSpinner;
    private Button overviewButton;
    private Button detailsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Get UI references
        topicSpinner = findViewById(R.id.spinnerTopics);
        overviewButton = findViewById(R.id.buttonOverview);
        detailsButton = findViewById(R.id.buttonDetails);

        // Listeners for the Overview and Details Button's
        overviewButton.setOnClickListener(new OverviewListener());
        detailsButton.setOnClickListener(new DetailsListener());

    } // onCreate

    /**
     * Private inner class that provides the onClick method implementation for overview
     */
    private class OverviewListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {

            String selectedTopic = topicSpinner.getSelectedItem().toString();
            Intent intent = new Intent(MainActivity.this, Overview.class);
            intent.putExtra("topic", selectedTopic);
            intent.putExtra("infoType", "overview");
            startActivity(intent);
        } // onClick
    } // OverviewListener

    /**
     * Private inner class that provides the onClick method implementation for details
     */
    private class DetailsListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {

            String selectedTopic = topicSpinner.getSelectedItem().toString();

        }
    }

} // MainActivity