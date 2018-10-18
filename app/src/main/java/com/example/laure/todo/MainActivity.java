package com.example.laure.todo;

import android.annotation.SuppressLint;
import android.arch.persistence.room.Room;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.SystemClock;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.GestureDetector;
import android.view.MenuInflater;
import android.view.MenuItem;
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
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final int ADD_ITEM_REQUEST_CODE = 100;

    ArrayList<String> todoList;

    //the adapter that converts our
    ArrayAdapter adapter;

    //The View object that displays our To Do items to the user
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get a reference to the ListView object
        listView = findViewById(R.id.todo_listview);

        //Create a new List of todo items
        todoList = new ArrayList<String>();

        //creating our ArrayAdapter - we give it the context of the current Activity,
        //one of Android's built in list item layouts, and the item source
        adapter = new ArrayAdapter(this,
                R.layout.list_item,
                todoList);

        //give the adapter to the ListView
        listView.setAdapter(adapter);

        //tell Android that our ListView items should make a Context Menu when they are long clicked
        registerForContextMenu(listView);

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int position = info.position;
        if (item.getItemId() == R.id.delete)
        {
            //remove the selected item from the List of ToDos
            todoList.remove(position);

            //tell the adapter to refresh the ListView
            adapter.notifyDataSetChanged();
            return true;
        }
        return false;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        MenuInflater inflater = getMenuInflater();

        //tell the inflater to build a Context Menu using the menu resource file we made
        inflater.inflate(R.menu.menu_list, menu);
    }

    public void addItemClickHandler(View view) {
        //Create an Intent that tells Android we want to start the AddItemActivity class
        Intent intent = new Intent(this, AddItemActivity.class);

        //Start the AddItemActivity class, expecting to get a result back
        startActivityForResult(intent, ADD_ITEM_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        //Check to see if the AddItemActivity is returning to us
        if (requestCode == ADD_ITEM_REQUEST_CODE)
        {
            //Check to see if the user clicked the Submit button (vs. the back button)
            if (resultCode == RESULT_OK)
            {
                //Grab the extra from the Intent (this is the To Do item)
                String todo = data.getStringExtra(AddItemActivity.NEW_TODO_ITEM);

                //Add the new To Do item to the list
                todoList.add(todo);

                //Tell the adapter to refresh the ListView
                adapter.notifyDataSetChanged();
            }
        }
    }
}
