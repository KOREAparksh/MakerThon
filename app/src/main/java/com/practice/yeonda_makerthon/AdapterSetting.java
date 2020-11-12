package com.practice.yeonda_makerthon;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterSetting extends RecyclerView.Adapter<AdapterSetting.ViewHolder> {

    View view;
    private ArrayList<String> list = new ArrayList<String>();

    public void add(String str) {
        list.add(str);
    }

    @NonNull
    @Override
    public AdapterSetting.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.setting_recycler_item, parent, false);
        AdapterSetting.ViewHolder viewHolder = new AdapterSetting.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterSetting.ViewHolder holder, int position) {
        holder.tv.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv = (TextView)itemView.findViewById(R.id.text_setting_recycler_item);
        }
    }
}
