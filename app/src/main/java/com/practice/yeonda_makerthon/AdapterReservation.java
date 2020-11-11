package com.practice.yeonda_makerthon;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterReservation extends RecyclerView.Adapter<AdapterReservation.ViewHolder> {

    private ArrayList<StudioTempClass> data = new ArrayList<StudioTempClass>();

    public void setStudioData(StudioTempClass studio) {
        data.add(studio);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.reservation_recycler_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.studioName.setText(data.get(position).name);
        holder.studioLocation.setText(data.get(position).location);
        holder.studioIntroduce.setText(data.get(position).introduce);
        holder.studioPrice.setText(data.get(position).price);
        holder.studioImage.setImageResource(R.drawable.studio_src);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView studioName, studioLocation, studioIntroduce, studioPrice;
        ImageView studioImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            studioName = (TextView) itemView.findViewById(R.id.studio_name_reservation_recycler_item);
            studioLocation = (TextView) itemView.findViewById(R.id.studio_location_reservation_recycler_item);
            studioIntroduce = (TextView) itemView.findViewById(R.id.studio_introduce_short_reservation_recycler_item);
            studioPrice = (TextView) itemView.findViewById(R.id.studio_price_reservation_recycler_item);
            studioImage = (ImageView) itemView.findViewById(R.id.studio_image_reservation_recycler_item);
        }
    }
}
