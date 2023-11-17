//Login activity
package com.example.attendanceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText editID, editPassword;
    private Button loginButton;
    private String userID, userPassword;
    private DatabaseOperations databaseOperations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseOperations = new DatabaseOperations(this);
        databaseOperations.open();

        editID = findViewById(R.id.userId);
        editPassword = findViewById(R.id.userPassword);
        loginButton = findViewById(R.id.loginButton);

        if (!databaseOperations.queryInstructor("1"))
        {
            //Insert courses, instructors and students as well as all their details
            long courseId1 = databaseOperations.insertCourse(1, "Biology 1", "MW 09:30");
            long courseId2 = databaseOperations.insertCourse(3, "Chemistry 1", "MW 11:00");
            long courseId3 = databaseOperations.insertCourse(2, "Physics 1", "MW 12:30");
            long courseId4 = databaseOperations.insertCourse(4, "Programming 1", "TT 09:30");
            long courseId5 = databaseOperations.insertCourse(5, "Mobile Application", "TT 11:00");
            long courseId6 = databaseOperations.insertCourse(6, "Software Engineering", "TT 12:30");
//            List<Long> courseIdsForInstructorAndStudents = Arrays.asList(courseId1, courseId2, courseId3, courseId4, courseId5, courseId6);
            databaseOperations.insertInstructor(1, "123", "Ahmed", Arrays.asList(courseId1, courseId2, courseId3));
            databaseOperations.insertInstructor(2, "123", "Bilal", Arrays.asList(courseId4, courseId5, courseId6));
            databaseOperations.insertStudent("U1", "123", "Mohammed", Arrays.asList(courseId1, courseId3, courseId5));
            databaseOperations.insertStudent("U2", "123", "Abdullah", Arrays.asList(courseId2, courseId4, courseId6));
            databaseOperations.insertStudent("U3", "123", "Ibrahim", Arrays.asList(courseId1, courseId2, courseId3));
            databaseOperations.insertStudent("U4", "123", "Sultan", Arrays.asList(courseId4, courseId5, courseId6));

        }

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userID = editID.getText().toString();
                userPassword = editPassword.getText().toString();

                if(databaseOperations.queryInstructor(userID) && databaseOperations.isPasswordCorrect(userID, userPassword))
                {
                    Intent intent = new Intent(getApplicationContext(), InstructorMenuActivity.class);
                    intent.putExtra("employee_id", userID);
                    startActivity(intent);
                }
//                else if(databaseOperations.queryStudent(userID) && databaseOperations.isPasswordCorrect(userID, userPassword))
//                {
//                    //Start the Student activity if query finds student
//                    Intent intent = new Intent(getApplicationContext(), StudentMenuActivity.class);
//                    intent.putExtra("student_id", userID);
//                    startActivity(intent);
//                }
                else
                {
                    Toast.makeText(MainActivity.this, "Invalid id or password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void onDestroy() {
        super.onDestroy();
        if (databaseOperations != null)
            databaseOperations.close();
    }
}