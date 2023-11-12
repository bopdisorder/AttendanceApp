//This class is for database operations. Add any new queries that you need here.
package com.example.attendanceapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DatabaseOperations {

    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;

    public DatabaseOperations(Context context)
    {
        dbHelper = new DatabaseHelper(context);
    }

    public void open()
    {
        database = dbHelper.getWritableDatabase();
    }

    public void close()
    {
        dbHelper.close();
    }

    public long insertInstructor(int id, String password, String name, String courseIds)
    {
        ContentValues values = new ContentValues();
        values.put(Metadata.COLUMN_ID, id);
        values.put(Metadata.COLUMN_PASSWORD, password);
        values.put(Metadata.COLUMN_NAME, name);
        values.put(Metadata.COLUMN_COURSE_ID_ARRAY, courseIds);

        return database.insert(Metadata.INSTRUCTORS_TABLE, null, values);
    }

    public long insertStudent(int id, String password, String name, String courseIds)
    {
        ContentValues values = new ContentValues();
        values.put(Metadata.COLUMN_ID, id);
        values.put(Metadata.COLUMN_PASSWORD, password);
        values.put(Metadata.COLUMN_NAME, name);
        values.put(Metadata.COLUMN_COURSE_ID_ARRAY, courseIds);

        return database.insert(Metadata.STUDENTS_TABLE, null, values);
    }

    public long insertCourse(int id, String name, String dateTime)
    {
        ContentValues values = new ContentValues();
        values.put(Metadata.COLUMN_ID, id);
        values.put(Metadata.COLUMN_NAME, name);
        values.put(Metadata.COLUMN_DATE_TIME, dateTime);

        return database.insert(Metadata.COURSES_TABLE, null, values);
    }

    public long insertAttendance(String studentName, String courseName, String dateTime, String attendanceStatus)
    {
        ContentValues values = new ContentValues();
        values.put(Metadata.COLUMN_NAME, studentName);
        values.put(Metadata.COLUMN_COURSE_NAME, courseName);
        values.put(Metadata.COLUMN_DATE_TIME, dateTime);
        values.put(Metadata.COLUMN_ATTENDANCE_STATUS, attendanceStatus);

        return database.insert(Metadata.ATTENDANCE_TABLE, null, values);
    }
}
