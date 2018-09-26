package com.example.laure.todo;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //the list of To Do items that will be the data source for the ListView
    ArrayList<String> todoList = new ArrayList<String>();

    //the adapter that converts our
    ArrayAdapter<String> adapter;

    //The View object that displays our To Do items to the user
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Adding a few starting items to our ArrayList
        todoList.add("homework");
        todoList.add("exercise");
        todoList.add("nap");

        //get a reference to the ListView object
        listView = findViewById(R.id.todo_listview);

        //creating our ArrayAdapter - we give it the context of the current Activity,
        //one of Android's built in list item layouts, and the item source
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_checked, todoList);

        //give the adapter to the ListView
        listView.setAdapter(adapter);
    }


    public void addButtonOnClick(View view) {
        //get a reference to the EditText object
        EditText editText = findViewById(R.id.todo_edittext);

        //add the text in the EditText to the ListView
        todoList.add(editText.getText().toString());

        //Remove the text from the EditText
        editText.setText("");

        //tell the adapter to refresh the ListView
        adapter.notifyDataSetChanged();
    }

}
