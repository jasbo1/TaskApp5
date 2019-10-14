package com.taskapp.room;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.taskapp.Task;

import java.util.List;

@Dao

public interface TaskDao {

    @Query("SELECT * FROM task ORDER BY title ASC")
    List<Task> getAll();

    @Query("SELECT * FROM task ORDER BY title ASC") //DESC
    List<Task> getAllSorted();



    @Insert
    void insert(Task task) ;

    @Delete
    void delete (Task task) ;

    @Update
    void  update(Task task);
}



