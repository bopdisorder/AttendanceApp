//This class is for database operations. Add any new queries that you need here.
package com.example.attendanceapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

public class DatabaseOperations {

    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;

    public DatabaseOperations(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public void open() {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public long insertInstructor(int id, String password, String name, List<Long> courseIds) {
        ContentValues values = new ContentValues();
        values.put(Metadata.COLUMN_ID, id);
        values.put(Metadata.COLUMN_PASSWORD, password);
        values.put(Metadata.COLUMN_NAME, name);
//        values.put(Metadata.COLUMN_COURSE_ID_LIST, courseIds);

        long instructorId = database.insert(Metadata.INSTRUCTORS_TABLE, null, values);

        for (long courseId : courseIds){
            ContentValues relationshipValues = new ContentValues();
            relationshipValues.put(Metadata.COLUMN_INSTRUCTOR_ID, instructorId);
            relationshipValues.put(Metadata.COLUMN_COURSE_ID, courseId);
            database.insert(Metadata.INSTRUCTOR_COURSE_TABLE, null, relationshipValues);
        }
        return instructorId;
    }

    public long insertStudent(String id, String password, String name, List<Long> courseIds) {
        ContentValues values = new ContentValues();
        values.put(Metadata.COLUMN_ID, id);
        values.put(Metadata.COLUMN_PASSWORD, password);
        values.put(Metadata.COLUMN_NAME, name);
//        values.put(Metadata.COLUMN_COURSE_ID_LIST, courseIds);

        long studentId = database.insert(Metadata.STUDENTS_TABLE, null, values);

        for (long courseId : courseIds){
            ContentValues relationshipValues = new ContentValues();
            relationshipValues.put(Metadata.COLUMN_STUDENT_ID, studentId);
            relationshipValues.put(Metadata.COLUMN_COURSE_ID, courseId);
            database.insert(Metadata.STUDENT_COURSE_TABLE, null, relationshipValues);
        }
        return studentId;
    }

    public long insertCourse(int id, String name, String dateTime) {
        ContentValues values = new ContentValues();
        values.put(Metadata.COLUMN_COURSE_ID, id);
        values.put(Metadata.COLUMN_COURSE_NAME, name);
        values.put(Metadata.COLUMN_DATE_TIME, dateTime);

        return database.insert(Metadata.COURSES_TABLE, null, values);
    }

    public long insertAttendance(String studentName, String courseName, String dateTime, String attendanceStatus) {
        ContentValues values = new ContentValues();
        values.put(Metadata.COLUMN_NAME, studentName);
        values.put(Metadata.COLUMN_COURSE_NAME, courseName);
        values.put(Metadata.COLUMN_DATE_TIME, dateTime);
        values.put(Metadata.COLUMN_ATTENDANCE_STATUS, attendanceStatus);

        return database.insert(Metadata.ATTENDANCE_TABLE, null, values);
    }

    public boolean queryStudent(String id)
    {
        // Define 'where' part of query.
        String selection = Metadata.COLUMN_ID + " LIKE ?";
        // Specify arguments in placeholder order.
        String[] selectionArgs = { id };

        Cursor cursor = database.query(
                Metadata.STUDENTS_TABLE,   // The table to query
                null,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null              // The sort order
        );

        boolean studentExists = cursor.moveToFirst();
        cursor.close();

        return studentExists;
    }

    public boolean queryInstructor(String id)
    {
        // Define 'where' part of query.
        String selection = Metadata.COLUMN_ID + " LIKE ?";
        // Specify arguments in placeholder order.
        String[] selectionArgs = {id};

        Cursor cursor = database.query(
                Metadata.INSTRUCTORS_TABLE,   // The table to query
                null,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null              // The sort order
        );

        boolean instructorExists = cursor.moveToFirst();
        cursor.close();

        return instructorExists;
    }

    public Cursor getCourseIdsForInstructor(int instructorId) {
        String query = "SELECT " +
                Metadata.INSTRUCTOR_COURSE_TABLE + "." + Metadata.COLUMN_COURSE_ID +
                " FROM " +
                Metadata.INSTRUCTOR_COURSE_TABLE +
                " WHERE " +
                Metadata.INSTRUCTOR_COURSE_TABLE + "." + Metadata.COLUMN_INSTRUCTOR_ID +
                " = ?";
        String[] selectionArgs = {String.valueOf(instructorId)};

        return database.rawQuery(query, selectionArgs);
    }

    public Cursor getCourseIdsForStudent(int studentId) {
        String query = "SELECT " +
                Metadata.STUDENT_COURSE_TABLE + "." + Metadata.COLUMN_COURSE_ID +
                " FROM " +
                Metadata.STUDENT_COURSE_TABLE +
                " WHERE " +
                Metadata.STUDENT_COURSE_TABLE + "." + Metadata.COLUMN_STUDENT_ID +
                " = ?";
        String[] selectionArgs = {String.valueOf(studentId)};

        return database.rawQuery(query, selectionArgs);
    }

    public boolean doesTableExist(String tableName) {
        Cursor cursor = null;

        // Query the sqlite_master table for the table name
        String query = "SELECT name FROM sqlite_master WHERE type='table' AND name=?";
        cursor = database.rawQuery(query, new String[]{tableName});

        //If the cursor has at least one result, the table exists
        return cursor.moveToFirst();
    }

    public boolean isPasswordCorrect(String id, String providedPassword) {
        if (queryStudent(id)) {
            String[] projection = {
                    Metadata.COLUMN_PASSWORD
            };

            String selection = Metadata.COLUMN_ID + " LIKE ?";
            String[] selectionArgs = {id};

            Cursor cursor = database.query(
                    Metadata.STUDENTS_TABLE,
                    projection,
                    selection,
                    selectionArgs,
                    null,
                    null,
                    null
            );

            if (cursor.moveToFirst()) {
                String storedPassword = cursor.getString(cursor.getColumnIndexOrThrow(Metadata.COLUMN_PASSWORD));

                // Close the cursor to release its resources
                cursor.close();

                // Compare the provided password with the stored password
                return providedPassword.equals(storedPassword);
            }
        }
        else if (queryInstructor(id)) {
            String[] projection = {
                    Metadata.COLUMN_PASSWORD
            };

            String selection = Metadata.COLUMN_ID + " LIKE ?";
            String[] selectionArgs = { id };

            Cursor cursor = database.query(
                    Metadata.INSTRUCTORS_TABLE,
                    projection,
                    selection,
                    selectionArgs,
                    null,
                    null,
                    null
            );

            if (cursor.moveToFirst()) {
                String storedPassword = cursor.getString(cursor.getColumnIndexOrThrow(Metadata.COLUMN_PASSWORD));

                cursor.close();

                return providedPassword.equals(storedPassword);
            }
        }

        return false; // User/Employee does not exist or password doesn't match
    }
}



