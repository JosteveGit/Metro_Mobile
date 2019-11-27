package com.metro.metromobile.dashboard.ui.bookings;


import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.fragment.app.Fragment;

import com.metro.metromobile.R;
import com.metro.metromobile.dashboard.ui.cars.MyCar;
import com.metro.metromobile.injector.Injector;
import com.metro.metromobile.model.request.AddBookingRequest;
import com.metro.metromobile.model.response.BaseResponse;
import com.metro.metromobile.rest.ServiceGenerator;
import com.metro.metromobile.shared.base.BaseFragment;
import com.metro.metromobile.shared.dialogs.CustomProgressDialog;
import com.metro.metromobile.utils.manager.SessionManager;
import com.seatgeek.placesautocomplete.PlacesAutocompleteTextView;

import org.angmarch.views.NiceSpinner;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewBookingFrag extends BaseFragment implements View.OnClickListener {
    private CustomProgressDialog customProgressDialog;
    @BindView(R.id.sp_select_car)
    NiceSpinner spSelectCar;
    @BindView(R.id.sp_preferred_session)
    NiceSpinner spPreferredSession;
    @BindView(R.id.cb_fuel)
    CheckBox cbFuel;
    @BindView(R.id.cb_ac)
    CheckBox cbAc;
    @BindView(R.id.cb_battery)
    CheckBox cbBattery;
    @BindView(R.id.cb_brake)
    CheckBox cbBrake;
    @BindView(R.id.cb_quickservice)
    CheckBox cbQService;
    @BindView(R.id.cb_exhaust)
    CheckBox cbExhaust;
    @BindView(R.id.cb_belt)
    CheckBox cbBelt;

    @BindView(R.id.sp_select_day)
    EditText spSelectDay;

    @BindView(R.id.bk_contactpersonname)
    AppCompatEditText contactPerson;

    @BindView(R.id.bk_pickupaddr)
    PlacesAutocompleteTextView pickupAddrPlaces;

    @BindView(R.id.bk_info)
    AppCompatEditText additionalInfo;

    @BindView(R.id.bk_personphone)
    AppCompatEditText contactPhone;
    final Calendar myCalendar = Calendar.getInstance();
    DatePicker mDatePicker;
    int carIdSelected;

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

        spSelectDay.setText(sdf.format(myCalendar.getTime()));
    }


    public NewBookingFrag() {
        // Required empty public constructor
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_new_booking;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        cbQService.setOnClickListener(this);
        cbFuel.setOnClickListener(this);
        cbExhaust.setOnClickListener(this);
        cbBrake.setOnClickListener(this);
        cbBattery.setOnClickListener(this);
        cbAc.setOnClickListener(this);
        cbBelt.setOnClickListener(this);

        customProgressDialog = new CustomProgressDialog(getActivity());
        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), date, myCalendar
                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());

        spSelectDay.setOnClickListener(v1 -> datePickerDialog.show());
        contactPerson.setText(SessionManager.getFirstName() + " " + SessionManager.getLastName());
        contactPhone.setText(SessionManager.getUserPhone());
        List<String> sessionSet = new LinkedList<>(Arrays.asList("9am - 11am", "11am - 1pm", "1pm - 3pm", "3pm - 5pm"));
        spPreferredSession.attachDataSource(sessionSet);

        //get carlist
        List<MyCar> myCars;
        myCars = SessionManager.getCarsFromPreference();
        if (myCars==null){
            myCars = new ArrayList<>();
        }
        List<String> carSetString = new ArrayList<>();
        for (int i = 0; i < myCars.size(); i++) {
            carSetString.add(myCars.get(i).getCarName() + " " + myCars.get(i).getModelName() + " " + myCars.get(i).getPlateNumber());
        }
        // List<String> carSet = new LinkedList<>(Arrays.asList());
        spSelectCar.attachDataSource(carSetString);
        List<MyCar> finalMyCars = myCars;
        spSelectCar.setOnSpinnerItemSelectedListener((parent, view1, position, id) -> {
            carIdSelected = Integer.valueOf(finalMyCars.get(position).getId());
            Log.d("IdSelected", carIdSelected + "");
        });

        pickupAddrPlaces.setOnPlaceSelectedListener(
                place -> showSuccessMessageToast(place.description)
        );
    }

    static String serviceTypeString = "";
    static int count = 0;


    @OnClick(R.id.book_now_btn)
    public void bookNow() {
        String service_type, service_day, service_time, pickup_address, additional_note, contact_person, contact_phone;
        if (count < 1) {
            showErrorMessageToast("At least a service type must be selected");
            return;
        }
        service_type = serviceTypeString;
        Log.d("ServiceType", service_type);
        service_day = spSelectDay.getText().toString();
        service_time = spPreferredSession.getText().toString();
        pickup_address = pickupAddrPlaces.getText().toString();
        contact_person = contactPerson.getText().toString();
        contact_phone = contactPhone.getText().toString();
        additional_note = additionalInfo.getText().toString();
        if (TextUtils.isEmpty(service_day)) {
            showErrorMessageToast("Service Day is a compulsory field");
            return;
        }
        if (TextUtils.isEmpty(pickup_address)) {
            showErrorMessageToast("Pickup address is a compulsory field");
            return;
        }

        if (TextUtils.isEmpty(service_time)) {
            showErrorMessageToast("Service Time is a compulsory field");
            return;
        }

        if (TextUtils.isEmpty(contact_person)) {
            showErrorMessageToast("You need to fill the Contact Person field");
            return;
        }

        if (TextUtils.isEmpty(service_time)) {
            showErrorMessageToast("You need to fill the Service Time field");
            return;
        }


        AddBookingRequest body = new AddBookingRequest();
        body.setCar_id(carIdSelected);
        body.setContact_person(contact_person);
        body.setContact_phone(contact_phone);
        body.setPickup_address(pickup_address);
        body.setService_day(service_day);
        body.setService_time(service_time);
        body.setService_type(serviceTypeString);
        body.setAdditional_note(additional_note);
        //showErrorMessageToast(serviceTypeString);
        doSendDetailsToServer(body);
    }

    private void doSendDetailsToServer(AddBookingRequest body) {
        customProgressDialog.showDialog();
        ServiceGenerator service = Injector.provideRetrofit().create(ServiceGenerator.class);
        Call<BaseResponse> call = service.addBooking(body);
        call.enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                customProgressDialog.hideDialog();
                processResponse(response);

            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                customProgressDialog.hideDialog();
                Log.d("BookingResponseFail", t.getMessage());
                showErrorMessageToast(getString(R.string.error_text));
            }
        });
    }

    private void processResponse(Response<BaseResponse> response) {
        if (response != null) {
            if (response.body().getStatus()) {
                showSuccessMessageToast("Booking Successful");
                getActivity().onBackPressed();
            } else {
                showErrorMessageToast(response.body().getMessage());
            }
        } else {
            showErrorMessageToast(getString(R.string.error_text));
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cb_ac:
                if (cbAc.isChecked()) {
                    if (!(serviceTypeString.contains(cbAc.getText().toString()))){
                        serviceTypeString = cbAc.getText().toString() + ", ";
                        count++;
                    }
                }
            case R.id.cb_battery:
                if (cbBattery.isChecked()) {
                    if(!(serviceTypeString.contains(cbBattery.getText().toString()))){
                        serviceTypeString = serviceTypeString + cbBattery.getText().toString() + ", ";
                        count++;
                    }

                }
            case R.id.cb_brake:
                if (cbBrake.isChecked()) {
                    if(!(serviceTypeString.contains(cbBrake.getText().toString()))){
                        serviceTypeString = serviceTypeString + cbBrake.getText().toString() + ", ";
                        count++;
                    }
                }
            case R.id.cb_quickservice:
                if (cbQService.isChecked()) {
                    if(!(serviceTypeString.contains(cbQService.getText().toString()))){
                        serviceTypeString = serviceTypeString + cbQService.getText().toString() + ", ";
                        count++;
                    }
                }
            case R.id.cb_fuel:
                if (cbFuel.isChecked()) {
                    if(!(serviceTypeString.contains(cbFuel.getText().toString()))){
                        serviceTypeString = serviceTypeString + cbFuel.getText().toString() + ", ";
                        count++;
                    }
                }
            case R.id.cb_exhaust:
                if (cbExhaust.isChecked()) {
                    if(!(serviceTypeString.contains(cbExhaust.getText().toString()))){
                        serviceTypeString = serviceTypeString + cbExhaust.getText().toString() + ", ";
                        count++;
                    }
                }
            case R.id.cb_belt:
                if (cbBelt.isChecked()) {
                    if(!(serviceTypeString.contains(cbBelt.getText().toString()))){
                        serviceTypeString = serviceTypeString + cbBelt.getText().toString() + ", ";
                        count++;
                    }
                } else
                    // Do your coding
                    break;
                // Perform your logic
        }
    }
}
