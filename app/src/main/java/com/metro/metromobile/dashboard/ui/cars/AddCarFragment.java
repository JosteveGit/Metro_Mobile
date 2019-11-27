package com.metro.metromobile.dashboard.ui.cars;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatAutoCompleteTextView;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;

import com.metro.metromobile.R;
import com.metro.metromobile.injector.Injector;
import com.metro.metromobile.model.request.AddCarRequest;
import com.metro.metromobile.model.response.BaseResponse;
import com.metro.metromobile.rest.ServiceGenerator;
import com.metro.metromobile.shared.base.BaseFragment;
import com.metro.metromobile.shared.dialogs.CustomProgressDialog;

import org.angmarch.views.NiceSpinner;
import org.angmarch.views.NiceSpinnerAdapter;
import org.angmarch.views.OnSpinnerItemSelectedListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddCarFragment extends BaseFragment {
    String carName, modelName, plateNumber;
    String modelYear;
    Button addCar;
    CustomProgressDialog customProgressDialog;
    NiceSpinner spinnerBrand;

    @BindView(R.id.plateNumber)
    AppCompatAutoCompleteTextView plateNumberEt;

    @BindView(R.id.chassis)
    AppCompatAutoCompleteTextView chassisEt;

    @BindView(R.id.modelYear)
    NiceSpinner modelyearSpinner;

    @BindView(R.id.spModel)
    NiceSpinner spinnerModel;
    int year;


    public AddCarFragment() {
        // Required empty public constructor
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_add_carfrag;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        addCar = view.findViewById(R.id.addCarBtn);
        year = Calendar.getInstance().get(Calendar.YEAR);
       // carName = carNameEt.getText().toString();
        //modelName = modelNameEt.getText().toString();
        //plateNumber = plateNumberEt.getText().toString();
       // modelYear = modelyearEt.getText().toString();
        customProgressDialog = new CustomProgressDialog(getActivity());

        spinnerBrand = view.findViewById(R.id.spBrand);
        List<String> dataset = new LinkedList<>(Arrays.asList("Toyota", "Kia", "Hyundai"));
        spinnerBrand.attachDataSource(dataset);
        spinnerBrand.setOnSpinnerItemSelectedListener((parent, view12, position, id) -> {
            if(position == 0){
                //toyota
                List<String> toyoSet = new LinkedList<>(Arrays.asList("Camry", "Corolla", "Yaris"));
                spinnerModel.attachDataSource(toyoSet);
            }else if(position == 1){
                List<String> kiaSet = new LinkedList<>(Arrays.asList("Rio", "Cerato", "Optima"));
                spinnerModel.attachDataSource(kiaSet);
            }
            else if(position == 2){
                List<String> hyun = new LinkedList<>(Arrays.asList("Elantra", "Accent"));
                spinnerModel.attachDataSource(hyun);
            }
        });

        List<String> dateSet = new ArrayList<>();
        for(int i=1990; i< 2023; i++){
            dateSet.add(String.valueOf(i));
        }
        modelyearSpinner.attachDataSource(dateSet);

        addCar.setOnClickListener(view1 -> {
            if (TextUtils.isEmpty(plateNumberEt.getText().toString())) {
                showErrorMessageToast("Plate Number is Required");
                return;
            }
            if (TextUtils.isEmpty(chassisEt.getText().toString())) {
                showErrorMessageToast("Chassis Number is Required");
                return;
            }

            String carImage = "";
            carName = spinnerBrand.getText().toString();
            modelName = spinnerModel.getText().toString();
            modelYear = modelyearSpinner.getText().toString();
            plateNumber = plateNumberEt.getText().toString();
            if (carName.equalsIgnoreCase("Toyota")){
                carImage = "toyota.jpg";
            }else if(carName.equalsIgnoreCase("Kia")){
                carImage = "kia.png";
            }else if(carName.equalsIgnoreCase("Hyundai")){
                carImage = "hyundai.jpg";
            }

            customProgressDialog.showDialog();
            ServiceGenerator service = Injector.provideRetrofit().create(ServiceGenerator.class);
            Call<BaseResponse> call = service.addCar(new AddCarRequest(carName, modelName, modelYear, plateNumber, chassisEt.getText().toString(), carImage));
            call.enqueue(new Callback<BaseResponse>() {
                @Override
                public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                    customProgressDialog.hideDialog();
                    showSuccessMessageToast("Successfully added car");
//                    if (getFragmentManager() != null) {
//                        getFragmentManager().beginTransaction()
//                                .replace(R.id.nav_host_fragment, new CarFragment())
//                                .addToBackStack(null).commit();
//                    }
                   getActivity().onBackPressed();
                }

                @Override
                public void onFailure(Call<BaseResponse> call, Throwable t) {
                    customProgressDialog.hideDialog();
                    showErrorMessageToast("Error adding Car, pls try again later");
                }
            });

        });
    }
}
