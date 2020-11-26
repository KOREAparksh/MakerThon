package com.practice.yeonda_makerthon;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 */
public class FragmentReservation extends Fragment {

    RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    AdapterReservation adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_reservation, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView_frag_reservation);
        recyclerView.setHasFixedSize(true); // 성능강화
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        adapter = new AdapterReservation();

        for(int i=0; i<ActivityMain.studioList.size(); i++){
            adapter.setStudioData(ActivityMain.studioList.get(i));
        }

        recyclerView.setAdapter(adapter);

        return view;
    }
}