package com.spydison.todolistjava;

import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.AsyncDifferConfig;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.spydison.todolistjava.databinding.EachTaskBinding;


public class Adapter extends ListAdapter<Task,Adapter.ViewHolder> {

public Adapter(){
    super(CALLBACK);
}

private static final DiffUtil.ItemCallback<Task> CALLBACK = new DiffUtil.ItemCallback<Task>() {
    @Override
    public boolean areItemsTheSame(@NonNull Task oldItem, @NonNull Task newItem) {
        return oldItem.getId() == newItem.getId();
    }

    @Override
    public boolean areContentsTheSame(@NonNull Task oldItem, @NonNull Task newItem) {
        return oldItem.getTitle().equals(newItem.getTitle());
    }
};

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.each_task,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Task task = getItem(position);
        holder.binding.taskTitle.setText(task.getTitle());
        holder.binding.taskDiscrip.setText(task.getDiscr());
    }

    public Task getTask(int position){
        return getItem(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
         EachTaskBinding binding;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = EachTaskBinding.bind(itemView);
        }

    }
}
