package com.spydison.todolistjava;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "my_Tasks")
 public class Task {
    private String title;
    private String discr;

    @PrimaryKey(autoGenerate = true)
    private int id;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public Task(String title, String discr) {
        this.title = title;
        this.discr = discr;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDiscr() {
        return discr;
    }

    public void setDiscr(String discr) {
        this.discr = discr;
    }
}
