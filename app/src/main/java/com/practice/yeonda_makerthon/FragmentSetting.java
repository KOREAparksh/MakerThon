package com.practice.yeonda_makerthon;

import android.graphics.Color;
import android.graphics.Rect;
import android.media.Image;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class FragmentSetting extends Fragment {

    private View view;

    private ImageView profile;
    private TextView logout;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private AdapterSetting adapter;

    private ArrayList<String> list = new ArrayList<String>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_setting, container, false);

        initialView();


        return view;
    }

    private void initialView() {
        profile = (ImageView)view.findViewById(R.id.profile_image_fragment_setting);
        logout = (TextView)view.findViewById(R.id.logout_button_fragment_setting);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView_frag_setting);

        Glide.with(this).load(R.drawable.unnamed).circleCrop().into(profile);

        recyclerView.setHasFixedSize(true); // 성능강화
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new AdapterSetting();
        adapter.add("공지사항");
        adapter.add("이용약관");
        adapter.add("FAQ");
        adapter.add("고객센터");
        recyclerView.setAdapter(adapter);

    }

}