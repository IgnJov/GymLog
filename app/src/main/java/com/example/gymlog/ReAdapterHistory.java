package com.example.gymlog;

import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ReAdapterHistory extends RecyclerView.Adapter<ReAdapterHistory.MyViewHolder>{
    private ArrayList<String> exerciseNameList;
    private ArrayList<Integer> setsList;
    private ArrayList<Integer> repsList;
    private ArrayList<Integer> weightList;

    public ReAdapterHistory(Cursor cursor, ArrayList<String> exerciseNameList, ArrayList<Integer> setsList, ArrayList<Integer> repsList, ArrayList<Integer> weightList){
        this.exerciseNameList = exerciseNameList;
        this.setsList = setsList;
        this.repsList = repsList;
        this.weightList = weightList;
    }

    @NonNull
    @Override
    public ReAdapterHistory.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.history_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReAdapterHistory.MyViewHolder holder, int position) {
        holder.exerciseName.setText(exerciseNameList.get(position));
        holder.sets.setText(setsList.get(position).toString());
        holder.reps.setText(repsList.get(position).toString());
        holder.weight.setText(weightList.get(position).toString());
    }

    @Override
    public int getItemCount() {
        return exerciseNameList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView exerciseName;
        TextView sets;
        TextView reps;
        TextView weight;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.exerciseName = itemView.findViewById(R.id.textView_exerciseNameLabel);
            this.sets = itemView.findViewById(R.id.textView_setsLabel);
            this.reps = itemView.findViewById(R.id.textView_repsLabel);
            this.weight = itemView.findViewById(R.id.textView_weightLabel);
        }
    }
}
