package com.practice.yeonda_makerthon;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
        StudioTempClass studioTemp = new StudioTempClass("ABC studio", "서울특별시 마포구", "스튜디오 간단히 소개하는 글", "30000");
        StudioTempClass studioTemp2 = new StudioTempClass("F10 studio", "서울특별시 광진구", "와타시와 칸코구 카라 키마시타", "20000");
        StudioTempClass studioTemp3 = new StudioTempClass("ABC studio", "서울특별시 마포구", "스튜디오 간단히 소개하는 글", "30000");
        StudioTempClass studioTemp4 = new StudioTempClass("F10 studio", "서울특별시 광진구", "와타시와 칸코구 카라 키마시타", "20000");
        StudioTempClass studioTemp5 = new StudioTempClass("ABC studio", "서울특별시 마포구", "스튜디오 간단히 소개하는 글", "30000");
        StudioTempClass studioTemp6 = new StudioTempClass("F10 studio", "서울특별시 광진구", "와타시와 칸코구 카라 키마시타", "20000");
        adapter.setStudioData(studioTemp);
        adapter.setStudioData(studioTemp2);
        adapter.setStudioData(studioTemp3);
        adapter.setStudioData(studioTemp4);
        adapter.setStudioData(studioTemp5);
        adapter.setStudioData(studioTemp6);
        recyclerView.setAdapter(adapter);

        return view;
    }
}