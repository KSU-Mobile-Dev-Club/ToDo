package com.example.laure.todo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final int ADD_ITEM_REQUEST_CODE = 100;

    List<Todo> todoList;

    //the adapter that converts our
    TodoAdapter adapter;

    //The View object that displays our To Do items to the user
    ListView listView;

    TodoDao todoDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        todoDao = TodoDatabase.getDatabase(this).todoDao();

        //get a reference to the ListView object
        listView = findViewById(R.id.todo_listview);

        //Create a new List of todo items
        todoList = todoDao.getAll();

        //creating our ArrayAdapter - we give it the context of the current Activity,
        //one of Android's built in list item layouts, and the item source
        adapter = new TodoAdapter(this,
                R.layout.list_item,
                todoList);

        //give the adapter to the ListView
        listView.setAdapter(adapter);

        //tell Android that our ListView items should make a Context Menu when they are long clicked
        registerForContextMenu(listView);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Todo todo = todoList.get(position);
                todo.isDone = !todo.isDone;
                todoDao.update(todo);
            }
        });

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int position = info.position;
        if (item.getItemId() == R.id.delete)
        {
            todoDao.delete(todoList.get(position));

            todoList.clear();

            todoList.addAll(todoDao.getAll());

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
                String todoName = data.getStringExtra(AddItemActivity.NEW_TODO_ITEM);

                Todo todo = new Todo(todoName);

                todoDao.insert(todo);

                todoList.clear();

                todoList.addAll(todoDao.getAll());

                //Tell the adapter to refresh the ListView
                adapter.notifyDataSetChanged();
            }
        }
    }
}
