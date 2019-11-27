package com.metro.metromobile.dashboard.ui.bookings;

import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.metro.metromobile.R;
import com.metro.metromobile.ViewDetailsListener;
import com.metro.metromobile.model.MyBookings;
import com.metro.metromobile.utils.app.ServerUtils;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class BookingsAdapter extends RecyclerView.Adapter<BookingsAdapter.BookingsViewHolder> {

    List<MyBookings> myBookingsList;
    private ViewDetailsListener detailsItemListener;

    public BookingsAdapter(List<MyBookings> myBookingsList, ViewDetailsListener detailsItemListener) {
        this.myBookingsList = myBookingsList;
        this.detailsItemListener = detailsItemListener;
    }

    public List<MyBookings> getMyBookingsList() {
        return myBookingsList;
    }

    public void setMyBookingsList(List<MyBookings> myBookingsList) {
        this.myBookingsList = myBookingsList;
    }

    @NotNull
    @Override
    public BookingsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.booking_item_single, parent, false);

        return new BookingsAdapter.BookingsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull BookingsViewHolder holder, int position) {
        MyBookings myBookings = myBookingsList.get(position);
        // Set item views based on your views and data model
        if (myBookings.getBookingStatus()!= null){
            holder.bookingStatus.setText(myBookings.getBookingStatus());
        }

        if (myBookings.getServiceType()!= null){
            holder.serviceType.setText(myBookings.getServiceType());
        }

        String serviceTypes = myBookings.getServiceType().trim()+" ";

        String type = "";
        List<String> myServices = new ArrayList<>();
        for (int i=0;i<serviceTypes.length();i++){
            if(serviceTypes.charAt(i)!=','){
                type+= serviceTypes.charAt(i);
            }
            else{
                myServices.add(type);
                type = "";
            }
        }


        Log.d("ServicesInList", myServices.size()+"");

        Log.d("ServicesTypeJosteve", myBookings.getServiceType());

        if (myBookings.getCar().get(0).getCarName()!= null){
            holder.carname.setText(myBookings.getCar().get(0).getCarName()+" "+myBookings.getCar().get(0).getModelName()+" "+myBookings.getCar().get(0).getModelYear());
        }

        if (myBookings.getCar().get(0).getPlateNumber()!= null){
            holder.carPlate.setText(myBookings.getCar().get(0).getPlateNumber());
        }
        if (myBookings.getCar().get(0).getCarImage()!= null){
            String carImageUrl = "https://api.mmobileservices.com/data/brands/";
            carImageUrl += myBookings.getCar().get(0).getCarName().trim().toLowerCase() + ".png";
            Picasso.get().load(carImageUrl).into(holder.carLogo);
            Log.d("CarLogo", carImageUrl);        }
    }

    @Override
    public int getItemCount() {
        return myBookingsList.size();
    }

    public void setBookingData(List<MyBookings> data) {
        this.myBookingsList = data;
        notifyDataSetChanged();
    }


    public class BookingsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView serviceType,bookingStatus, carname, carPlate, one, two, three,four,five,six;
        ImageView carLogo;
        Button viewDetailsBtn;

        public BookingsViewHolder(@NonNull View itemView) {
            super(itemView);
            serviceType = itemView.findViewById(R.id.bk_servicetype);
            bookingStatus = itemView.findViewById(R.id.bk_bookingstat);
            carLogo = itemView.findViewById(R.id.bklogo);
            carPlate = itemView.findViewById(R.id.bkcar_plate);
            carname = itemView.findViewById(R.id.bkcar_nametmodel);
            viewDetailsBtn = itemView.findViewById(R.id.bk_viewbooking);

            viewDetailsBtn.setOnClickListener(view -> detailsItemListener.onBookingDetailsClicked(getAdapterPosition()));

        }

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        public void onClick(View v) {
            int position  = getAdapterPosition();
            if(v.getId() == R.id.bk_viewbooking){
                detailsItemListener.onBookingDetailsClicked(position);
            }
            detailsItemListener.onBookingDetailsClicked(position);
//            boolean isBookmarked;
//            if (v.getId() == R.id.news_bookmark_item_icon) {
//                if (data.get(position).getIsBookmarked().equals(BooleanType.TYPE_TRUE)){
//                    data.get(position).setIsBookmarked(BooleanType.TYPE_FALSE);
//                    isBookmarked = true;
//                } else{
//                    data.get(position).setIsBookmarked(BooleanType.TYPE_TRUE);
//                    isBookmarked = false;
//                }
//                notifyItemChanged(position);
//                newsItemListener.onBookmarkClicked(position, isBookmarked);
//                return;
//            }

            //detailsItemListener.onBookingDetailsClicked(position);
        }
    }
}
