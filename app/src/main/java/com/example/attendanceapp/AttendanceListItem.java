package com.example.attendanceapp;

public class AttendanceListItem {
    private String StudentName, AttendanceStatus;

    public AttendanceListItem(String studentName, String attendanceStatus) {
        StudentName = studentName;
        AttendanceStatus = attendanceStatus;
    }

    public String getStudentName() {
        return StudentName;
    }

    public void setStudentName(String studentName) {
        StudentName = studentName;
    }

    public String getAttendanceStatus() {
        return AttendanceStatus;
    }

    public void setAttendanceStatus(String attendanceStatus) {
        AttendanceStatus = attendanceStatus;
    }
}
