package com.spydison.todolistjava;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = Task.class,version = 1)
public abstract class TaskDatabase extends RoomDatabase {

        private static TaskDatabase instance;
        public abstract Task_DAO task_Dao();
        public static synchronized TaskDatabase getInstance(Context context){
            if (instance == null){
                instance = Room.databaseBuilder(context.getApplicationContext(),TaskDatabase.class,"Task_Database").fallbackToDestructiveMigration().build();
            }
            return instance;
        }
}
