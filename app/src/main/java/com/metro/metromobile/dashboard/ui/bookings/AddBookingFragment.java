package com.metro.metromobile.dashboard.ui.bookings;


import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatAutoCompleteTextView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputEditText;
import com.metro.metromobile.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


public class AddBookingFragment extends Fragment {

    final Calendar myCalendar = Calendar.getInstance();
    EditText selectDay;


    DatePickerDialog.OnDateSetListener date = (view, year, monthOfYear, dayOfMonth) -> {
        // TODO Auto-generated method stub
        myCalendar.set(Calendar.YEAR, year);
        myCalendar.set(Calendar.MONTH, monthOfYear);
        myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        updateLabel();
    };

    private void updateLabel() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        selectDay.setText(sdf.format(myCalendar.getTime()));
    }

    public AddBookingFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_add_booking, container, false);
        selectDay = v.findViewById(R.id.serviceDay);
        selectDay.setOnClickListener(v1 -> new DatePickerDialog(getContext(), date, myCalendar
                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show());
        return v;
    }

}
