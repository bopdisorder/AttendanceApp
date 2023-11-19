package com.example.attendanceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class AttendanceSignalActivity extends AppCompatActivity {

    private ImageView signalGIF;
    private Button receiveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_signal);

        Intent recieveIntent = getIntent();
        int receiveId = recieveIntent.getIntExtra("course_id", 100);
        String receiveName = recieveIntent.getStringExtra("course_name");

        Toast.makeText(getApplicationContext(), "Id: " + receiveId + "\nName: " + receiveName, Toast.LENGTH_SHORT).show();

        signalGIF = findViewById(R.id.signalGIF);

        receiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AttendanceListActivity.class);
                startActivity(intent);
                //Send the list of students that attended with the intent
            }
        });
    }
}