package com.example.attendanceapp;

public class GridItem {
    private String courseName;
    private int courseId;

    public GridItem(int courseId, String courseName) {
        this.courseId = courseId;
        this.courseName = courseName;
    }

    public int getCourseId()
    {
        return courseId;
    }

    public String getCourseName() {
        return courseName;
    }
}
