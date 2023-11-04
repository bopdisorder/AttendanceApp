package com.example.attendanceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

public class AttendanceSignalActivity extends AppCompatActivity {

    private ImageView signalGIF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_signal);

        signalGIF = findViewById(R.id.signalGIF);

        //Upon receiving at least one signal from a student go the next activity
    }
}