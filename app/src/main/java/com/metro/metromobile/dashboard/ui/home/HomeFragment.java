package com.metro.metromobile.dashboard.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.metro.metromobile.R;
import com.metro.metromobile.ViewDetailsListener;
import com.metro.metromobile.activities.BookingDetailsActivity;
import com.metro.metromobile.dashboard.ui.bookings.BookingsAdapter;
import com.metro.metromobile.activities.LoginActivity;
import com.metro.metromobile.model.BookingsResponse;
import com.metro.metromobile.model.MyBookings;
import com.metro.metromobile.fragments.EarnPointsFragment;
import com.metro.metromobile.injector.Injector;
import com.metro.metromobile.rest.ServiceGenerator;
import com.metro.metromobile.shared.base.BaseFragment;
import com.metro.metromobile.utils.app.ServerUtils;
import com.metro.metromobile.utils.keys.AppKeys;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends BaseFragment implements ViewDetailsListener {

    @BindView(R.id.earn_points)
    Button earnPoints;

    @BindView(R.id.createABooking)
    TextView createabooking;


    @BindView(R.id.bookings_recycler)
    RecyclerView bookingsRecycler;

    @BindView(R.id.home_srl)
    SwipeRefreshLayout homeSrl;

    @BindView(R.id.noBookingView)
    LinearLayout noBookingV;

    List<MyBookings> bookingsList;

    private HomeViewModel homeViewModel;
    private BookingsAdapter bookingsAdapter;


    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_home;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        earnPoints.setOnClickListener(view1 -> startActivity(new Intent(getContext(), EarnPointsFragment.class)));
        bookingsList = new ArrayList<>();
        homeSrl.setColorSchemeResources(R.color.colorAccent);
        homeSrl.setOnRefreshListener(this::doRefreshContent);
        //make request call

        bookingsRecycler.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.HORIZONTAL);
        bookingsRecycler.setLayoutManager(llm);
        bookingsAdapter = new BookingsAdapter(bookingsList, this);
        bookingsRecycler.setAdapter(bookingsAdapter);

        prepareGetDataFromServer();

        createabooking.setOnClickListener(view12 -> {

        });
    }

    private void doRefreshContent() {
        doGetFromServer();
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
        Call<BookingsResponse> call = service.getBookings();
        call.enqueue(new Callback<BookingsResponse>() {
            @Override
            public void onResponse(Call<BookingsResponse> call, Response<BookingsResponse> response) {
                Log.d("Loggggg", response.body().getMessage());
                Log.d("Loggggg", response.body().getStatus()+"");
                if(response.isSuccessful()) {

                    processResponse(response);
                    //showLoadedState();

                }
                else if(response.code() == 401){
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                }
            }

            @Override
            public void onFailure(Call<BookingsResponse> call, Throwable t) {
               showErrorMessageToast(t.getMessage());
            }
        });
    }

    private void processResponse(Response<BookingsResponse> response) {
        if(response != null) {
            if(response.body().getStatus()) {
                processData(response.body().getData());
            }else {
                showEmptyState();
            }
        } else {
            showErrorState(null);
        }
    }

    private void processData(List<MyBookings> data) {
        if (data.isEmpty()) {
            showEmptyState();
        } else {
            bookingsAdapter.setBookingData(data);
            showLoadedState();
        }
    }


    // Activity State Methods-----------------------------------------------------------------------
    private void doLoadingState() {
        homeSrl.setRefreshing(true);
      //  noBookingV.setVisibility(View.GONE);
    }

    private void showLoadedState() {
        if (homeSrl.isRefreshing()) {
            homeSrl.setRefreshing(false);
        }

       // noBookingV.setVisibility(View.GONE);
    }

    private void showEmptyState() {
        if (homeSrl.isRefreshing()) {
            homeSrl.setRefreshing(false);
            noBookingV.setVisibility(View.VISIBLE);
        }
        showErrorMessageToast("No Bookings added, Book now");
    }

    private void showErrorState(String msg) {
        if (homeSrl.isRefreshing()) {
            homeSrl.setRefreshing(false);
        }

        noBookingV.setVisibility(View.GONE);

        if (TextUtils.isEmpty(msg)) {
            showErrorMessageToast("Error in reaching server, try again");
        } else {
            showErrorMessageToast(msg);
        }
    }

    @Override
    public void onBookingDetailsClicked(int position) {
       // showInfoMessageToast("Clicked");
        MyBookings item = bookingsAdapter.getMyBookingsList().get(position);
        Intent intent = new Intent(getActivity(), BookingDetailsActivity.class);
      //  Bundle arguments = new Bundle();
       // arguments.putParcelable("BookingKey" , item);
        intent.putExtra("BookingKey", item);
        startActivity(intent);

//        Fragment newsDetailFragment = new NewsDetailFragment();
//        Bundle arguments = new Bundle();
//        arguments.putParcelable(AppKeys.NEWS_OBJECT_KEY , item);
//        newsDetailFragment.setArguments(arguments);
//
//        if (getFragmentManager() != null) {
//            getFragmentManager().beginTransaction()
//                    .replace(R.id.news_container_view, newsDetailFragment)
//                    .addToBackStack(null).commit();
//        }

    }
}