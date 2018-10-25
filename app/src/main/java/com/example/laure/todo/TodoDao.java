package com.example.laure.todo;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface TodoDao {

    @Delete
    void delete(Todo todo);

    @Update
    void update(Todo todo);

    @Insert
    void insert(Todo todo);

    @Query("SELECT * FROM TODO_TABLE")
    List<Todo> getAll();
}
