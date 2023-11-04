//Login activity
package com.example.attendanceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText editID, editPassword;
    private Button loginButton;
    private String userID, userPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editID = findViewById(R.id.userId);
        editPassword = findViewById(R.id.userPassword);
        loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userID = editID.getText().toString();
                userPassword = editPassword.getText().toString();

                Intent intent = new Intent(getApplicationContext(), InstructorMenuActivity.class);
                startActivity(intent);


//                if(userID == student && userPassword == correct)
//                {
//                    //Start the student activity if query finds student
//                }
//                else if(userID == instructor && userPassword == correct)
//                {
//                    //Start the instructor activity if query finds instructor
//                }
//                else
//                {
//                    Toast.makeText(MainActivity.this, "Invalid id or password", Toast.LENGTH_SHORT).show();
//                }
            }
        });
    }
}