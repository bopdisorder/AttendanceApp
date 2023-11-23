package com.example.attendanceapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class GridItemAdapter extends ArrayAdapter<GridItem> {

    private final Context context;
    private GridItem[] gridItem;
    private DatabaseOperations databaseOperations;


    public GridItemAdapter(@NonNull Context context, GridItem[] gridItem) {
        super(context, R.layout.grid_item_layout, gridItem);
        this.context = context;
        this.gridItem = gridItem;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row = convertView;
        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.grid_item_layout, parent, false);
        }

        Button button = row.findViewById(R.id.gridButton);

        GridItem currentItem = gridItem[position];

        button.setText(currentItem.getCourseName());

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, CourseMenuActivity.class);
                intent.putExtra("course_id", currentItem.getCourseId());
                intent.putExtra("course_name", currentItem.getCourseName());
                context.startActivity(intent);
            }
        });

        return row;

    }
}
