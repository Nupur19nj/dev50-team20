package com.spydison.todolistjava;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.spydison.todolistjava.databinding.ActivityMainBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity {
ActivityMainBinding binding;
private TaskViewModel taskViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        taskViewModel = new ViewModelProvider(this, (ViewModelProvider.Factory) ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication()))
                .get(TaskViewModel.class);
        binding.floatingActionButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,DataInsert.class);
                intent.putExtra("type","addTask");
                startActivityForResult(intent,1);
            }
        });

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setHasFixedSize(true);
        Adapter adapter = new Adapter();
        binding.recyclerView.setAdapter(adapter);

        taskViewModel.getAllTasks().observe(this, new Observer<List<Task>>() {
            @Override
            public void onChanged(List<Task> tasks) {
                adapter.submitList(tasks);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                if (direction == ItemTouchHelper.LEFT) {
                    taskViewModel.delete(adapter.getTask(viewHolder.getAdapterPosition()));
                    Toast.makeText(MainActivity.this, "Deleted!", Toast.LENGTH_SHORT).show();
                }
                else{
                    Intent intent = new Intent(MainActivity.this,DataInsert.class);
                    intent.putExtra("type","update");
                    intent.putExtra("title",adapter.getTask(viewHolder.getAdapterPosition()).getTitle());
                    intent.putExtra("descrip",adapter.getTask(viewHolder.getAdapterPosition()).getDiscr());
                    intent.putExtra("id",adapter.getTask(viewHolder.getAdapterPosition()).getId());
                    startActivityForResult(intent,2);


                }
            }
        }).attachToRecyclerView(binding.recyclerView);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1){
            String subject = data.getStringExtra("subject");
            String discription = data.getStringExtra("discription");
            Task task = new Task(subject,discription);
            taskViewModel.insert((task));
            Toast.makeText(this, "Task Added", Toast.LENGTH_SHORT).show();
        }

        else if (requestCode==2){
            String subject = data.getStringExtra("subject");
            String discription = data.getStringExtra("discription");
            Task task= new Task(subject,discription);
            task.setId(data.getIntExtra("id",0));
            taskViewModel.update(task);
            Toast.makeText(this, "Task Updated", Toast.LENGTH_SHORT).show();
        }
    }
}