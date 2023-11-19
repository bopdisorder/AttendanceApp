package com.example.attendanceapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class AttendanceListAdapter extends ArrayAdapter<AttendanceListItem> {
    private final Context context;
    private final AttendanceListItem[] items;

    public AttendanceListAdapter(@NonNull Context context, AttendanceListItem[] items)
    {
        super(context, R.layout.attendance_list_item, items);
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row = convertView;
        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.attendance_list_item, parent, false);
        }

        TextView nameText = row.findViewById(R.id.itemName);
        TextView statusText = row.findViewById(R.id.itemStatus);

        AttendanceListItem currentItem = items[position];

        nameText.setText(currentItem.getStudentName());
        statusText.setText(currentItem.getAttendanceStatus());

        return row;
    }
}
