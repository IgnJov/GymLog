package com.example.gymlog;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.sql.Array;
import java.util.ArrayList;

public class ReAdapterExercise extends RecyclerView.Adapter<ReAdapterExercise.MyViewHolder> {
    private final RecyclerViewInterface recyclerViewInterface;
    ArrayList<Exercise> exercistList;

    ReAdapterExercise(ArrayList<Exercise> exercistList, RecyclerViewInterface recyclerViewInterface){
        this.exercistList = exercistList;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    public void setDataSet(ArrayList<Exercise> exercistList){
        this.exercistList = exercistList;
    }

    @NonNull
    @Override
    public ReAdapterExercise.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.exercise_row, parent, false);
        return new ReAdapterExercise.MyViewHolder(view, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull ReAdapterExercise.MyViewHolder holder, int position) {
        holder.textView_exerciseName.setText(exercistList.get(position).getExercise_name());
        holder.textView_exerciseMuscle.setText(exercistList.get(position).getMuscle());
    }

    @Override
    public int getItemCount() {
        return exercistList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView textView_exerciseName;
        TextView textView_exerciseMuscle;
        ImageButton imageButton_delete;
        public MyViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);
            textView_exerciseName = itemView.findViewById(R.id.textView_exerciseName);
            textView_exerciseMuscle = itemView.findViewById(R.id.textView_exerciseMuscle);
            imageButton_delete = itemView.findViewById(R.id.imageButton_delete);

            imageButton_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (recyclerViewInterface != null){
                        int pos = getAdapterPosition();

                        if(pos != RecyclerView.NO_POSITION){
                            recyclerViewInterface.onItemClick(pos);
                        }
                    }
                }
            });
        }
    }
}
