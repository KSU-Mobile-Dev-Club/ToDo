package com.example.laure.todo;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface DaoAccess {

    @Insert
    long insertToDo(ToDo todo);

    @Query("SELECT * FROM " + MyDatabase.TABLE_NAME_TODO)
    List<ToDo> fetchAllToDos();

    @Delete
    int deleteToDo(ToDo todo);
}
