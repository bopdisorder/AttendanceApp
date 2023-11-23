//This will contain the list view showing students who attended. This activity will also insert into the attending table
package com.example.attendanceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AttendanceListActivity extends AppCompatActivity {

    private ListView listView;
    private DatabaseOperations databaseOperations;
    private ArrayList<AttendanceListItem> itemsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_list);

        //For storing date and time in the attendance table
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        String formattedDateTime = dateFormat.format(date);

        //Get Array of strings from previous activity of students that attended
        Intent receiveIntent = getIntent();
        String[] attendingStudents = receiveIntent.getStringArrayExtra("");

        listView = findViewById(R.id.listView);

        AttendanceListItem[] emptyItems = new AttendanceListItem[0];
        AttendanceListAdapter adapter = new AttendanceListAdapter(this, emptyItems);

        //get list of attending student and put their names in an array to display on listView
        //Use insertAttendance() to insert all the attending and absent student into the attendance table
        AttendanceListItem[] items = itemsList.toArray(new AttendanceListItem[0]);
        adapter = new AttendanceListAdapter(this, items);
        listView.setAdapter(adapter);
    }

    public void onDestroy() {
        super.onDestroy();
        if (databaseOperations != null)
            databaseOperations.close();
    }
}