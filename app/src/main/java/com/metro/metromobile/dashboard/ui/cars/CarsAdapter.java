package com.metro.metromobile.dashboard.ui.cars;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.metro.metromobile.R;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CarsAdapter extends RecyclerView.Adapter<CarsAdapter.CarsViewHolder> {

    List<MyCar> myCarsList;

    public CarsAdapter(List<MyCar> myCarsList) {
        this.myCarsList = myCarsList;
    }

    @NotNull
    @Override
    public CarsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.car_item_model, parent, false);

        return new CarsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CarsViewHolder holder, int position) {
        MyCar myCar = myCarsList.get(position);
        // Set item views based on your views and data model
        if (myCar.getModelName() != null) {
            holder.modelName.setText(myCar.getModelName());
        }

        if (myCar.getPlateNumber() != null) {
            holder.plateNumber.setText(myCar.getPlateNumber());
        }

        if (myCar.getCarStatus() != null) {
            holder.status.setText(myCar.getCarStatus());
        }

        if (myCar.getLastService() != null) {
            holder.lastServiceDate.setText(myCar.getLastService());
        }

        if (myCar.getCarName() != null) {
            holder.carname.setText(myCar.getCarName() + " ");
            Log.d("CarName", myCar.getCarName());

        }
        if (myCar.getCarLogo() != null) {
            String carImageUrl = "https://api.mmobileservices.com/data/brands/";
            carImageUrl += myCar.getCarName().trim().toLowerCase() + ".png";
            Picasso.get().load(carImageUrl).into(holder.carLogo);
            Log.d("CarLogo", carImageUrl);
        }

    }

    @Override
    public int getItemCount() {
        return myCarsList.size();
    }

    public void setCarData(List<MyCar> data) {
        this.myCarsList = data;
        notifyDataSetChanged();
    }


    public class CarsViewHolder extends RecyclerView.ViewHolder {
        TextView plateNumber, lastServiceDate, status, modelName, carname;
        ImageView carLogo;

        public CarsViewHolder(@NonNull View itemView) {
            super(itemView);
            plateNumber = itemView.findViewById(R.id.numberPlate);
            lastServiceDate = itemView.findViewById(R.id.lastService);
            status = itemView.findViewById(R.id.stat);
            modelName = itemView.findViewById(R.id.motorName);
            carLogo = itemView.findViewById(R.id.motorlogo);
            carname = itemView.findViewById(R.id.carmotorName);
        }
    }
}
