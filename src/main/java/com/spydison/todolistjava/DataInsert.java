package com.spydison.todolistjava;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.spydison.todolistjava.databinding.ActivityDataInsertBinding;

public class DataInsert extends AppCompatActivity {
ActivityDataInsertBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityDataInsertBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String type=getIntent().getStringExtra("type");
        if(type.equals("update")){
        setTitle("update");
        binding.subject.setText(getIntent().getStringExtra("title"));
        binding.discription.setText(getIntent().getStringExtra("descrip"));
        int id = getIntent().getIntExtra("id",0);
        binding.addTask.setText("Update Task");
        binding.addTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent();
                intent.putExtra("subject", binding.subject.getText().toString());
                intent.putExtra("discription", binding.discription.getText().toString());
                intent.putExtra("id",id);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
        }else {

            setTitle("Add Task");
            binding.addTask.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent();
                    intent.putExtra("subject", binding.subject.getText().toString());
                    intent.putExtra("discription", binding.discription.getText().toString());
                    setResult(RESULT_OK, intent);
                    finish();
                }

            });
        }
    }
}