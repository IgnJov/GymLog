package com.example.gymlog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class REAdapter_Exercise extends RecyclerView.Adapter<REAdapter_Exercise.MyViewHolder>{
    Context context;
    ArrayList<Exercise> exerciseArrayList;

    public REAdapter_Exercise(Context context, ArrayList<Exercise> exerciseArrayList) {
        this.context = context;
        this.exerciseArrayList = exerciseArrayList;
    }

    @NonNull
    @Override
    public REAdapter_Exercise.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.exercise_row_layout, parent , false);
        return new REAdapter_Exercise.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull REAdapter_Exercise.MyViewHolder holder, int position) {
        holder.exerciseName.setText(exerciseArrayList.get(position).getExercise_name());
    }

    @Override
    public int getItemCount() {
        return exerciseArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView exerciseName;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            exerciseName = itemView.findViewById(R.id.exerciseName);
        }
    }
}
