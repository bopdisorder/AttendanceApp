package com.example.attendanceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class CheckAttendanceActivity extends AppCompatActivity {
    private DatabaseOperations databaseOperations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_attendance);
        databaseOperations.open();

        Intent recieveIntent = getIntent();
        int receiveId = recieveIntent.getIntExtra("course_id", 100);
        String receiveName = recieveIntent.getStringExtra("course_name");

        //get the list of students taking the course, query students courses table
        //For each student list their absences and attendances from query the attendance table
    }

    public void onDestroy() {
        super.onDestroy();
        if (databaseOperations != null)
            databaseOperations.close();
    }
}