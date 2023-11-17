package com.example.attendanceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class InstructorMenuActivity extends AppCompatActivity {

    private Button biologyButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructor_menu);

        Intent receiveId = getIntent();
        String id = receiveId.getStringExtra("user_id");



        biologyButton = findViewById(R.id.biologyButton);

        biologyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AttendanceSignalActivity.class);
                startActivity(intent);
            }
        });
    }
}