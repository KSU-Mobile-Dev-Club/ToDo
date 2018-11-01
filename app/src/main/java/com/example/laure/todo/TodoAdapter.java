package com.example.laure.todo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class TodoAdapter extends ArrayAdapter<Todo> {
    Context context;
    int listItemLayout;
    List<Todo> todoList;

    public TodoAdapter(@NonNull Context context, int resource, @NonNull List<Todo> objects) {
        super(context, resource, objects);
        this.context = context;
        this.listItemLayout = resource;
        todoList = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null)
        {
            convertView = LayoutInflater.from(context).inflate(listItemLayout, parent, false);
        }
        Todo todo = todoList.get(position);
        TextView tv = convertView.findViewById(R.id.text1);
        tv.setText(todo.name);

        ListView lv = (ListView) parent;
        lv.setItemChecked(position, todo.isDone);
        return convertView;
    }
}
