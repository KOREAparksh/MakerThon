package com.practice.yeonda_makerthon;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.studioName.setText(data.get(position).getName());
        holder.studioLocation.setText(data.get(position).getLocation());
        holder.studioIntroduce.setText(data.get(position).getIntroduce());
        holder.studioPrice.setText(data.get(position).getPrice());
        if(position == 0)
            holder.studioImage.setImageResource(R.drawable.studio_src1);
        else if(position==1)
            holder.studioImage.setImageResource(R.drawable.studio_src2);
        else
            holder.studioImage.setImageResource(R.drawable.studio_src3);


        holder.totalLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ActivityDetailView.class);
                intent.putExtra("class", data.get(position));
                v.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView studioName, studioLocation, studioIntroduce, studioPrice;
        ImageView studioImage;
        LinearLayout totalLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            studioName = (TextView) itemView.findViewById(R.id.studio_name_reservation_recycler_item); studioName.setSelected(true);
            studioLocation = (TextView) itemView.findViewById(R.id.studio_location_reservation_recycler_item); studioLocation.setSelected(true);
            studioIntroduce = (TextView) itemView.findViewById(R.id.studio_introduce_short_reservation_recycler_item);
            studioPrice = (TextView) itemView.findViewById(R.id.studio_price_reservation_recycler_item);
            studioImage = (ImageView) itemView.findViewById(R.id.studio_image_reservation_recycler_item);
            totalLayout = (LinearLayout)itemView.findViewById(R.id.layout_reservation_item);
        }
    }
}
