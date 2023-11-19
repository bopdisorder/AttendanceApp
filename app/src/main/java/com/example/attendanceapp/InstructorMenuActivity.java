package com.example.attendanceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;

import java.util.ArrayList;

public class InstructorMenuActivity extends AppCompatActivity {

    private GridView gridView;
    private DatabaseOperations databaseOperations;
    private ArrayList<GridItem> gridItemsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructor_menu);
        databaseOperations = new DatabaseOperations(this);
        databaseOperations.open();

        Intent receiveId = getIntent();
        String idString = receiveId.getStringExtra("employee_id");
        Log.d("idString value: ", "Is: " + idString);
        int id = Integer.valueOf(idString);

        gridView = findViewById(R.id.InstructorGridView);

        Cursor cursor = databaseOperations.getCourseDetailsForInstructor(id);
        int courseIdIndex = cursor.getColumnIndexOrThrow(Metadata.COLUMN_COURSE_ID);
        int courseNameIndex = cursor.getColumnIndexOrThrow(Metadata.COLUMN_COURSE_NAME);
        Log.d("course names: ", String.valueOf(courseNameIndex));

        if(courseNameIndex != -1){
            while (cursor.moveToNext())
            {
                int courseId = cursor.getInt(courseIdIndex);

                String courseName = cursor.getString(courseNameIndex);
                GridItem gridItem = new GridItem(courseId ,courseName);
                gridItemsList.add(gridItem);
            }
        }
        Log.d("InstructorMenuActivity", "GridItemsList size: " + gridItemsList.size());
        GridItem[] grids = gridItemsList.toArray(new GridItem[0]);
        GridItemAdapter adapter = new GridItemAdapter(this, grids);
        gridView.setAdapter(adapter);

    }

    public void onDestroy() {
        super.onDestroy();
        if (databaseOperations != null)
            databaseOperations.close();
    }
}