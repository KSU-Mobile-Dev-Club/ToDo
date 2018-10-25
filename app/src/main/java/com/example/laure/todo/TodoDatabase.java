package com.example.laure.todo;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {Todo.class}, version = 1)
public abstract class TodoDatabase extends RoomDatabase {

    private static TodoDatabase soleInstance;

    public static TodoDatabase getDatabase(Context context) {
        if (soleInstance == null) {
            soleInstance = Room.databaseBuilder(context.getApplicationContext(),
                    TodoDatabase.class,
                    "TODO_DATABASE")
                    .allowMainThreadQueries()
                    .build();
        }
        return soleInstance;
    }

    public abstract TodoDao todoDao();
}
