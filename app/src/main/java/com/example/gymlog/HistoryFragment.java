package com.example.gymlog;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HistoryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HistoryFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HistoryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HistoryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HistoryFragment newInstance(String param1, String param2) {
        HistoryFragment fragment = new HistoryFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_history, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Get all components
        Spinner spinnerExercise = getActivity().findViewById(R.id.spinnerExercise);
        EditText editTextSets = getActivity().findViewById(R.id.editTextSets);
        EditText editTextReps = getActivity().findViewById(R.id.editTextReps);
        EditText editTextWeight = getActivity().findViewById(R.id.editTextWeight);
        Button buttonAddExerciseDetail = getActivity().findViewById(R.id.buttonAddExerciseDetail);

        //Get Exercises Record
        DatabaseHelper dbHelper = new DatabaseHelper(getActivity());
        ArrayList<Exercise> exerciseArrayList = Exercise.getAllRecord(getActivity());
        ArrayList<String> list = new ArrayList<>();
        for (Exercise e: exerciseArrayList
        ) {
            list.add(e.getExercise_name());
        }

        //Convert array list to array
        String[] listArray = new String[list.size()];
        listArray = list.toArray(listArray);

        //Set spinner values
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, listArray);
        spinnerExercise.setAdapter(adapter);

        buttonAddExerciseDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int sets = Integer.parseInt(editTextSets.getText().toString());
                int reps = Integer.parseInt(editTextReps.getText().toString());
                int weight = Integer.parseInt(editTextWeight.getText().toString());
            }
        });
    }
}