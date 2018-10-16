package com.example.laure.todo;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

public class AddItemActivity extends AppCompatActivity {

    public static final String NEW_TODO_ITEM = "ToDoItem";
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        editText = findViewById(R.id.input_item);
    }


    public void submitButtonClickHandler(View view) {
        //get the text in the edit text (this is the new To Do Item)
        String item = editText.getText().toString();

        //Don't need context or class to return to, Android will take care of this for us
        Intent intent = new Intent();

        //Add the new To Do item as an extra in the Intent
        intent.putExtra(NEW_TODO_ITEM, item);

        //Since the user clicked "Submit" we want to set the result to RESULT_OK
        //This will produce a different result than if the user clicked the back button
        setResult(RESULT_OK, intent);

        //close the app and return to the MainActivity
        finish();
    }
}
