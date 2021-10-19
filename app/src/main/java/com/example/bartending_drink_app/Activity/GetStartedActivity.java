package com.example.bartending_drink_app.Activity;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import com.example.bartending_drink_app.R;

public class GetStartedActivity extends AppCompatActivity {
    Button btnGetStarted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_started);

        btnGetStarted = findViewById(R.id.btn_get_started);
        btnGetStarted.setOnClickListener(v -> {
            Intent loginIntent = new Intent(GetStartedActivity.this, MainActivity.class);
            startActivity(loginIntent);
            finish();
        });

    }

}