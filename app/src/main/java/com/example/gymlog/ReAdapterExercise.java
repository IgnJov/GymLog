package com.example.gymlog;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.sql.Array;
import java.util.ArrayList;

public class ReAdapterExercise extends RecyclerView.Adapter<ReAdapterExercise.MyViewHolder> {
    ArrayList<Exercise> exercistList;

    ReAdapterExercise(ArrayList<Exercise> exercistList){
        this.exercistList = exercistList;
    }

    public void setDataSet(ArrayList<Exercise> exercistList){
        this.exercistList = exercistList;
    }

    @NonNull
    @Override
    public ReAdapterExercise.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.exercise_row, parent, false);
        return new ReAdapterExercise.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReAdapterExercise.MyViewHolder holder, int position) {
        holder.textView_exerciseName.setText(exercistList.get(position).getExercise_name());
    }

    @Override
    public int getItemCount() {
        return exercistList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView textView_exerciseName;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView_exerciseName = itemView.findViewById(R.id.textView_exerciseName);
        }
    }
}
