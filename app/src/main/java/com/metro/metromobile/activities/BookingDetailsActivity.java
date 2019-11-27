package com.metro.metromobile.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.metro.metromobile.R;
import com.metro.metromobile.model. MyBookings;
import com.metro.metromobile.shared.base.BaseActivity;
import com.metro.metromobile.utils.keys.AppKeys;

import butterknife.BindView;

public class BookingDetailsActivity extends BaseActivity {
    @BindView(R.id.bd_platenum)
    TextView bdPlateNum;

    @BindView(R.id.bd_address)
    TextView bdAddress;

    @BindView(R.id.bd_carname)
    TextView bdCarName;

    @BindView(R.id.bd_servicetype)
    TextView bdServiceType;

    @BindView(R.id.bd_contactperson)
    TextView bdContactPerson;

    @BindView(R.id.bd_contactphone)
    TextView bdContactPhone;

    @BindView(R.id.bd_date)
    TextView bdDate;

    @BindView(R.id.bd_status)
    TextView bdStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_details);

        MyBookings item = getIntent().getExtras().getParcelable("BookingKey");
        if (item != null) {
            bdAddress.setText(item.getPickupAddress());
            bdContactPerson.setText(item.getContactPerson());
            bdContactPhone.setText(item.getContactPhone());
            bdDate.setText(item.getServiceDay());
            bdStatus.setText(item.getBookingStatus());
            bdServiceType.setText(item.getServiceType());
//            bdPlateNum.setText(item.getCar().get(0).getPlateNumber());
//            bdCarName.setText(item.getCar().get(0).getCarName()+" "+item.getCar().get(0).getModelName()+" "+item.getCar().get(0).getModelYear());
        }else{
            showErrorMessageToast("Null value");
        }

    }

    public void doGoBack(View view) {
        super.onBackPressed();
    }
}
