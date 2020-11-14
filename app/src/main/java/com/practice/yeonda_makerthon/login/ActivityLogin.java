package com.practice.yeonda_makerthon.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.practice.yeonda_makerthon.R;

public class ActivityLogin extends AppCompatActivity implements View.OnClickListener {

    private ConstraintLayout parentLayout;
    private TextView signUpText;
    private EditText id_editText, pw_editText;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWindow().setStatusBarColor(Color.WHITE);

        initView();
        setFocusSystem();//뷰 외 포커스 풀기
        setListener();//버튼에 리스너 달기

    }

    private void setListener() {
        loginButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

    }

    private void setFocusSystem() {
        /**
         * This is for clearFocus when you touch not edittextView
         * For Security
         * */
        parentLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                resetView();
                return false;
            }
        });
    }

    void resetView()
    {
        View view = getCurrentFocus();
        if(view != null) {
            view.clearFocus();
            InputMethodManager imm = (InputMethodManager) getApplicationContext().getSystemService(INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    private void initView() {
        parentLayout = (ConstraintLayout)findViewById(R.id.parent_layout_login_act);
        signUpText = (TextView)findViewById(R.id.signUp_login_act);
        id_editText = (EditText)findViewById(R.id.id_login_act);
        pw_editText = (EditText)findViewById(R.id.pw_login_act);
        loginButton = (Button)findViewById(R.id.login_button_login_act);
    }

}