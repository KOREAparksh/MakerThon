package com.practice.yeonda_makerthon;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.w3c.dom.Text;

public class FragmentSmartKey extends Fragment {
    View view;
    private TextView remainTime_h;
    private TextView remainTime_m;
    private TextView endTime;
    private ProgressBar progressBar;
    private TextView studioName, studioLocation;
    private Button returnButton;
    private CheckBox nfcCheckBox;
    private LinearLayout openButton, closeButton;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_smart_key, container, false);

        initView();


        return  view;
    }

    private void initView() {
        remainTime_h = (TextView)view.findViewById(R.id.remain_time_hour_smartKey);
        remainTime_m = (TextView)view.findViewById(R.id.remain_time_min_smartKey);
        endTime = (TextView)view.findViewById(R.id.end_time_smartKey);
        progressBar = (ProgressBar)view.findViewById(R.id.progressbar);
        studioName = (TextView)view.findViewById(R.id.studio_name_smartKey);
        studioLocation = (TextView)view.findViewById(R.id.studio_location_smartKey);
        returnButton = (Button)view.findViewById(R.id.return_button_smartKey);
        nfcCheckBox = (CheckBox)view.findViewById(R.id.nfc_checkbox_smartKey);
        openButton = (LinearLayout)view.findViewById(R.id.open_button_smartKey);
        closeButton = (LinearLayout)view.findViewById(R.id.close_button_smartKey);
    }
}