package com.metro.metromobile.dashboard.ui.cars;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.metro.metromobile.R;
import com.metro.metromobile.injector.Injector;
import com.metro.metromobile.model.response.AuthenticationResponse;
import com.metro.metromobile.rest.ServiceGenerator;
import com.metro.metromobile.shared.base.BaseFragment;
import com.metro.metromobile.utils.app.ServerUtils;
import com.metro.metromobile.utils.manager.SessionManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class CarsFrag extends BaseFragment {

    @BindView(R.id.cars_srl)
    SwipeRefreshLayout carsSrl;

    @BindView(R.id.myCars)
    RecyclerView recList;

    List<MyCar> myCarList;

    @BindView(R.id.no_cars_view)
    View noCarsView;

    @BindView(R.id.nocarimg)
    ImageView nocarImg;

    @BindView(R.id.nocartext)
    TextView nocarText;

//    @BindView(R.id.nocarbtn)
//    View nocarBtn;



    CarsAdapter carsAdapter;

    public CarsFrag() {
        // Required empty public constructor
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_carsfrag;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        myCarList = new ArrayList<>();
        carsSrl.setColorSchemeResources(R.color.colorAccent);
        carsSrl.setOnRefreshListener(this::doRefreshContent);
        //make request call

        recList.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recList.setLayoutManager(llm);
        carsAdapter = new CarsAdapter(myCarList);
        recList.setAdapter(carsAdapter);

        prepareGetDataFromServer();

    }

    private void prepareGetDataFromServer() {
        if (ServerUtils.isNetworkUnavailable(getContext())) {
            // TODO
            showErrorMessageToast(getContext().getString(R.string.no_network_connection));
        } else {
            doLoadingState();
            doGetFromServer();
        }
    }

    private void doGetFromServer() {
        ServiceGenerator service = Injector.provideRetrofit().create(ServiceGenerator.class);
        Call<MyCars> call = service.getMyCars();

        call.enqueue(new Callback<MyCars>() {
            @Override
            public void onResponse(Call<MyCars> call, Response<MyCars> response) {
                List<MyCar> cars;
                if(response.body().getData()==null){
                    cars = new ArrayList<>();
                }else{
                    cars = response.body().getData();
                }
                SessionManager.saveCarListInPreference(cars);
                processResponse(response);
                showLoadedState();
            }

            @Override
            public void onFailure(Call<MyCars> call, Throwable t) {
                showErrorMessageToast(t.getMessage());
            }
        });
    }

    private void processResponse(Response<MyCars> response) {
        if(response != null) {
            if(response.code() == 200) {
                List<MyCar> cars;
                if(response.body().getData()==null){
                    cars = new ArrayList<>();
                }else{
                    cars = response.body().getData();
                }
                processData(cars);
            } else if(response.code() == 404){
                showEmptyState();
            }
        } else {
            showErrorState(null);
        }
    }

    private void processData(List<MyCar> data) {
        if (data.isEmpty()) {
            showEmptyState();
        } else {
            carsAdapter.setCarData(data);
            showLoadedState();
        }
    }

    private void doRefreshContent() {
        doGetFromServer();
    }

    // Activity State Methods-----------------------------------------------------------------------
    private void doLoadingState() {
        carsSrl.setRefreshing(true);
        noCarsView.setVisibility(View.INVISIBLE);
    }

    private void showLoadedState() {
        if (carsSrl.isRefreshing()) {
            carsSrl.setRefreshing(false);
        }

  //      noCarsView.setVisibility(View.INVISIBLE);
    }

    private void showEmptyState() {
        if (carsSrl.isRefreshing()) {
            carsSrl.setRefreshing(false);
        }
        showErrorMessageToast("No cars added, add a Car");
        noCarsView.setVisibility(View.VISIBLE);
    }

    private void showErrorState(String msg) {
        if (carsSrl.isRefreshing()) {
            carsSrl.setRefreshing(false);
        }

        noCarsView.setVisibility(View.VISIBLE);

        if (TextUtils.isEmpty(msg)) {
            showErrorMessageToast("Error in reaching server, try again");
        } else {
            showErrorMessageToast(msg);
        }
    }
}
