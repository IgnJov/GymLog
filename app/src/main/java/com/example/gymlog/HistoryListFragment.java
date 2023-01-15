package com.example.gymlog;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HistoryListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HistoryListFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HistoryListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HistoryListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HistoryListFragment newInstance(String param1, String param2) {
        HistoryListFragment fragment = new HistoryListFragment();
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
        return inflater.inflate(R.layout.fragment_history_list, container, false);
    }

    private String convertDateFormat(int year, int month, int day){
        String stringDate = String.valueOf(day) + "-" + String.valueOf(month) + "-" + String.valueOf(year);
        String inputPattern = "d-M-yyyy";
        String outputPattern = "dd MMMM yyyy";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);

        Date date = null;
        String convertedStringDate = null;
        try {
            date = inputFormat.parse(stringDate);
            convertedStringDate = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return convertedStringDate;
    }

    private String convertDateSQLiteFormat(String stringDate){
        String inputPattern = "dd MMMM yyyy";
        String outputPattern  = "yyyy-MM-dd";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);

        Date date = null;
        String convertedStringDate = null;
        try {
            date = inputFormat.parse(stringDate);
            convertedStringDate = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return convertedStringDate;
    }

    public void updateDataSet(String date){
        DatabaseHelper dbHelper = new DatabaseHelper(getActivity());
        Cursor cursor = dbHelper.getHistoryDataBasedOnDate(date);

        ArrayList<String> exerciseNameList = new ArrayList<>();
        ArrayList<Integer> setsList = new ArrayList<>();
        ArrayList<Integer> repsList = new ArrayList<>();
        ArrayList<Integer> weightList = new ArrayList<>();

        if(cursor.moveToFirst()) {
            do {
                exerciseNameList.add(cursor.getString(0));
                setsList.add(cursor.getInt(1));
                repsList.add(cursor.getInt(2));
                weightList.add(cursor.getInt(3));
            } while (cursor.moveToNext());
        }

        adapter.setDataSet(exerciseNameList, setsList, repsList, weightList);
        adapter.notifyDataSetChanged();
    }

    //Element Variable
    ImageButton imageButton_calendar;
    TextView textView_calendar;
    FloatingActionButton floatingActionButton_addRoutine;

    //Recycler View component
    ReAdapterHistory adapter = null;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Get all elements
        imageButton_calendar = getView().findViewById(R.id.imageButton_calendar);
        textView_calendar = getView().findViewById(R.id.textView_calendar);
        floatingActionButton_addRoutine = getView().findViewById(R.id.floatingActionButton_addRoutine);

        //Get current date
        Calendar calendar = Calendar.getInstance();
        final int currentYear = calendar.get(Calendar.YEAR);
        final int currentMonth = calendar.get(Calendar.MONTH);
        final int currentDay = calendar.get(Calendar.DAY_OF_MONTH);

        //Set text view to current date
        String currentDate = (String) DateFormat.format("dd MMMM yyyy", (new Date()).getTime());
        textView_calendar.setText(currentDate);

        //Set dataset for recycler view
        DatabaseHelper dbHelper = new DatabaseHelper(getActivity());
        Cursor cursor = dbHelper.getHistoryDataBasedOnDate(convertDateSQLiteFormat(currentDate));

        ArrayList<String> exerciseNameList = new ArrayList<>();
        ArrayList<Integer> setsList = new ArrayList<>();
        ArrayList<Integer> repsList = new ArrayList<>();
        ArrayList<Integer> weightList = new ArrayList<>();

        if(cursor.moveToFirst()) {
            do {
                exerciseNameList.add(cursor.getString(0));
                setsList.add(cursor.getInt(1));
                repsList.add(cursor.getInt(2));
                weightList.add(cursor.getInt(3));
            } while (cursor.moveToNext());
        }

        //Set recycler view
        RecyclerView RE = getView().findViewById(R.id.recyclerView_history);
        adapter = new ReAdapterHistory(exerciseNameList, setsList, repsList, weightList);
        RE.setLayoutManager(new LinearLayoutManager(getActivity()));
        RE.setAdapter(adapter);

        imageButton_calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                        month++;
                        String convertedDate = convertDateFormat(year, month, dayOfMonth);
                        textView_calendar.setText(convertedDate);
                        updateDataSet(convertDateSQLiteFormat(convertedDate));
                    }
                }, currentYear, currentMonth, currentDay);

                datePickerDialog.show();
            }
        });

        floatingActionButton_addRoutine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Get date picker input
                String selectedDate = convertDateSQLiteFormat(textView_calendar.getText().toString());

                //Create intent with selected date data
                Intent intent = new Intent(getActivity(), AddRoutineActivity.class);
                intent.putExtra("date", selectedDate);

                startActivity(intent);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        updateDataSet(convertDateSQLiteFormat(textView_calendar.getText().toString()));
    }
}