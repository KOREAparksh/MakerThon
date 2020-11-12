package com.practice.yeonda_makerthon;

import android.media.Image;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

public class FragmentSetting extends Fragment {

    private View view;

    private ImageView profile;
    private TextView logout;


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

        Glide.with(this).load(R.drawable.unnamed).circleCrop().into(profile);
    }

}