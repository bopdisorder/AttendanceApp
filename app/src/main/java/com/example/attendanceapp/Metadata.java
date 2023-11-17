//Database design
package com.example.attendanceapp;

public class Metadata {

    public static final String INSTRUCTORS_TABLE = "instructors";
    public static final String STUDENTS_TABLE = "students";
    public static final String COURSES_TABLE = "courses";
    public static final String ATTENDANCE_TABLE = "attendence";
    public static final String INSTRUCTOR_COURSE_TABLE = "instructors_courses";
    public static final String STUDENT_COURSE_TABLE = "students_courses";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_INSTRUCTOR_ID = "instructor_id";
    public static final String COLUMN_STUDENT_ID = "students_id";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_DATE_TIME = "date_and_time";
    public static final String COLUMN_ATTENDANCE_STATUS = "attendance_status";
    public static final String COLUMN_COURSE_ID = "course_id";
    public static final String COLUMN_COURSE_NAME = "course_name";
    public static final String COLUMN_COURSE_ID_LIST = "courses_list";

    private Metadata() {}

    public static final String SQL_CREATE_INSTRUCTORS_TABLE =
            "CREATE TABLE " + INSTRUCTORS_TABLE + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY, " +
                    COLUMN_PASSWORD + " TEXT," +
                    COLUMN_NAME + " TEXT)";
//                    COLUMN_COURSE_ID_LIST + " INTEGER REFERENCES " + COURSES_TABLE + "(" + COLUMN_COURSE_ID + "))";

    public static final String DELETE_INSTRUCTORS_TABLE = "DROP TABLE IF EXISTS " + INSTRUCTORS_TABLE;

    public static final String SQL_CREATE_STUDENTS_TABLE =
            "CREATE TABLE " + STUDENTS_TABLE + " (" +
                    COLUMN_ID + " TEXT PRIMARY KEY, " +
                    COLUMN_PASSWORD + " TEXT," +
                    COLUMN_NAME + " TEXT)";
//                    COLUMN_COURSE_ID_LIST + " INTEGER REFERENCES " + COURSES_TABLE + "(" + COLUMN_COURSE_ID + "))";

    public static final String DELETE_STUDENTS_TABLE = "DROP TABLE IF EXISTS " + STUDENTS_TABLE;

    public static final String SQL_CREATE_COURSES_TABLE =
            "CREATE TABLE " + COURSES_TABLE + " (" +
                    COLUMN_COURSE_ID + " INTEGER PRIMARY KEY, " +
                    COLUMN_COURSE_NAME + " TEXT," +
                    COLUMN_DATE_TIME + " TEXT)";

    public static final String DELETE_COURSES_TABLE = "DROP TABLE IF EXISTS " + COURSES_TABLE;

    public static final String SQL_CREATE_ATTENDANCE_TABLE =
            "CREATE TABLE " + ATTENDANCE_TABLE + " (" +
                    COLUMN_NAME + " TEXT," +
                    COLUMN_COURSE_NAME + " TEXT," +
                    COLUMN_DATE_TIME + " TEXT," +
                    COLUMN_ATTENDANCE_STATUS + " TEXT)";

    public static final String DELETE_ATTENDANCE_TABLE = "DROP TABLE IF EXISTS " + ATTENDANCE_TABLE;

    public static final String SQL_CREATE_INSTRUCTOR_COURSE_TABLE =
            "CREATE TABLE " + INSTRUCTOR_COURSE_TABLE + " (" +
                    COLUMN_INSTRUCTOR_ID + " INTEGER REFERENCES " + INSTRUCTORS_TABLE + "(" + COLUMN_ID + "), " +
                    COLUMN_COURSE_ID + " INTEGER REFERENCES " + COURSES_TABLE + "(" + COLUMN_COURSE_ID + "), " +
                    "PRIMARY KEY (" + COLUMN_INSTRUCTOR_ID + ", " + COLUMN_COURSE_ID + "))";

    public static final String DELETE_INSTRUCTOR_COURSE_TABLE = "DROP TABLE IF EXISTS " + INSTRUCTOR_COURSE_TABLE;

    public static final String SQL_CREATE_STUDENT_COURSE_TABLE =
            "CREATE TABLE " + STUDENT_COURSE_TABLE + " (" +
                    COLUMN_STUDENT_ID + " INTEGER REFERENCES " + STUDENTS_TABLE + "(" + COLUMN_ID + "), " +
                    COLUMN_COURSE_ID + " INTEGER REFERENCES " + COURSES_TABLE + "(" + COLUMN_COURSE_ID + "), " +
                    "PRIMARY KEY (" + COLUMN_STUDENT_ID + ", " + COLUMN_COURSE_ID + "))";

    public static final String DELETE_STUDENT_COURSE_TABLE = "DROP TABLE IF EXISTS " + STUDENT_COURSE_TABLE;


}
