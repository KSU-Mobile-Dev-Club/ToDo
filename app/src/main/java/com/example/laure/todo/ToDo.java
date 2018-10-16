package com.example.laure.todo;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = MyDatabase.TABLE_NAME_TODO)
public class ToDo{

    @PrimaryKey(autoGenerate = true)
    public int todo_id;

    public String name;

    @Override
    public String toString() {
        return name;
    }
}
