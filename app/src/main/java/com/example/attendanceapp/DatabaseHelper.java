//This is responsible for managing the creation of the database
package com.example.attendanceapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "AppDatabase.db";

    public DatabaseHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Metadata.SQL_CREATE_INSTRUCTORS_TABLE);
        db.execSQL(Metadata.SQL_CREATE_STUDENTS_TABLE);
        db.execSQL(Metadata.SQL_CREATE_COURSES_TABLE);
        db.execSQL(Metadata.SQL_CREATE_ATTENDANCE_TABLE);
        db.execSQL(Metadata.SQL_CREATE_INSTRUCTOR_COURSE_TABLE);
        db.execSQL(Metadata.SQL_CREATE_STUDENT_COURSE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(Metadata.DELETE_INSTRUCTORS_TABLE);
        db.execSQL(Metadata.DELETE_STUDENTS_TABLE);
        db.execSQL(Metadata.DELETE_COURSES_TABLE);
        db.execSQL(Metadata.DELETE_ATTENDANCE_TABLE);
        db.execSQL(Metadata.DELETE_INSTRUCTOR_COURSE_TABLE);
        db.execSQL(Metadata.DELETE_STUDENT_COURSE_TABLE);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        onUpgrade(db, oldVersion, newVersion);
    }
}
