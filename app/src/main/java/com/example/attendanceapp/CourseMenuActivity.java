package com.example.attendanceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CourseMenuActivity extends AppCompatActivity {
    private Button takeAttendanceBtn, checkAttendanceBtn;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_menu);

        Intent recieveIntent = getIntent();
        int receiveId = recieveIntent.getIntExtra("course_id", 100);
        String receiveName = recieveIntent.getStringExtra("course_name");

        takeAttendanceBtn = findViewById(R.id.TakeAttendanceBtn);
        checkAttendanceBtn = findViewById(R.id.CheckAttendanceBtn);

        takeAttendanceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AttendanceSignalActivity.class);
                intent.putExtra("course_id", receiveId);
                intent.putExtra("course_name", receiveName);
                context.startActivity(intent);
            }
        });

        checkAttendanceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CheckAttendanceActivity.class);
                intent.putExtra("course_id", receiveId);
                intent.putExtra("course_name", receiveName);
                context.startActivity(intent);
            }
        });
    }
}