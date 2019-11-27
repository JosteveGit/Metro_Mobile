package com.metro.metromobile.dashboard.ui.bookings;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.metro.metromobile.R;
import com.metro.metromobile.ViewDetailsListener;
import com.metro.metromobile.activities.BookingDetailsActivity;
import com.metro.metromobile.dashboard.ui.cars.CarsAdapter;
import com.metro.metromobile.dashboard.ui.cars.MyCars;
import com.metro.metromobile.injector.Injector;
import com.metro.metromobile.model.BookingsResponse;
import com.metro.metromobile.model.MyBookings;
import com.metro.metromobile.rest.ServiceGenerator;
import com.metro.metromobile.shared.base.BaseFragment;
import com.metro.metromobile.utils.app.ServerUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class ActiveBookingFrag extends BaseFragment implements ViewDetailsListener {
    @BindView(R.id.booking_srl)
    SwipeRefreshLayout bookingSrl;
    BookingsAdapter bookingAdapter;
    @BindView(R.id.activeBkRv)
    RecyclerView recList;
    @BindView(R.id.noBookingVieww)
    LinearLayout noBookingV;
    List<MyBookings> bookingsList;
    public ActiveBookingFrag() {

    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_active_booking;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bookingsList = new ArrayList<>();
        bookingSrl.setColorSchemeResources(R.color.colorAccent);
        bookingSrl.setOnRefreshListener(this::doRefreshContent);
        //make request call

        recList.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recList.setLayoutManager(llm);
        bookingAdapter = new BookingsAdapter(bookingsList, this);
        recList.setAdapter(bookingAdapter);

        prepareGetDataFromServer();
    }

    private void doRefreshContent() {
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
                processResponse(response);
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
            bookingAdapter.setBookingData(data);
            showLoadedState();
        }
    }

    // Activity State Methods-----------------------------------------------------------------------
    private void doLoadingState() {
        bookingSrl.setRefreshing(true);
        noBookingV.setVisibility(View.GONE);
    }

    private void showLoadedState() {
        if (bookingSrl.isRefreshing()) {
            bookingSrl.setRefreshing(false);
        }

         noBookingV.setVisibility(View.GONE);
    }

    private void showEmptyState() {
        if (bookingSrl.isRefreshing()) {
            bookingSrl.setRefreshing(false);
            noBookingV.setVisibility(View.VISIBLE);
        }
        showErrorMessageToast("No Bookings added, Book now");
    }

    private void showErrorState(String msg) {
        if (bookingSrl.isRefreshing()) {
            bookingSrl.setRefreshing(false);
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
        MyBookings item = bookingAdapter.getMyBookingsList().get(position);
        Intent intent = new Intent(getActivity(), BookingDetailsActivity.class);
        //  Bundle arguments = new Bundle();
        // arguments.putParcelable("BookingKey" , item);
        intent.putExtra("BookingKey", item);
        startActivity(intent);
    }
}
