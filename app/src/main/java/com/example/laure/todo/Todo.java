package com.example.laure.todo;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "TODO_TABLE")
public class Todo {
    public String name;

    public boolean isDone;

    @PrimaryKey(autoGenerate = true)
    public int id;

    public Todo(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
