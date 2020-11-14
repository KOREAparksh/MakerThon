package com.practice.yeonda_makerthon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.practice.yeonda_makerthon.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ActivityLogin extends AppCompatActivity implements View.OnClickListener, TextView.OnEditorActionListener {

    private ConstraintLayout parentLayout;
    private TextView signUpText;
    private EditText id_editText, pw_editText;
    private Button loginButton;
    private LinearLayout progressBarLayout;

    private FirebaseAuth mAuth;

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
        signUpText.setOnClickListener(this);
        pw_editText.setOnEditorActionListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_button_login_act :
                checkLogin(); resetView(); break;
            case R.id.signUp_login_act : break;
            default:
        }
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        boolean handled = false;

        if(actionId == EditorInfo.IME_ACTION_DONE){
            loginButton.callOnClick();
            handled = true;
        }

        return handled;
    }

    //텍스트 형식 확인 및 로그인 확인하기
    private void checkLogin() {
        //확인
        if(id_editText.getText().toString().isEmpty() || pw_editText.getText().toString().isEmpty()){ //빈칸검사
            Toast.makeText(getApplicationContext(), "빈칸이 있습니다", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(!isEmailValid(id_editText.getText().toString())){
            Toast.makeText(getApplicationContext(), "아이디는 이메일형식으로~", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(pw_editText.getText().toString().length() < 8){
            Toast.makeText(getApplicationContext(), "비밀번호는 8자리 이상~", Toast.LENGTH_SHORT).show();
            return;
        }

        isNowLoading(true); //로딩 시작


        //로그인하기
        mAuth.signInWithEmailAndPassword(id_editText.getText().toString(), pw_editText.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            // 로그인 성공 시
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);

                        } else {
                            // 로그인 실패 시
                            Toast.makeText(getApplicationContext(), "Authentication failed.\n"
                                    + task.getException().toString(), Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }

                    }
                });

    }

    private void updateUI(FirebaseUser user){
        //로그인 안되어 있으면
        if(user == null){
            isNowLoading(false);//로딩 풀기
        }
        //로그인 되었으면
        else {
            Intent intent = new Intent(ActivityLogin.this, ActivityMain.class);
            startActivity(intent);
            ActivityLogin.this.finish();
        }

    }

    private void isNowLoading(boolean loading){
        //터치가 되게
        if(loading == false){
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            progressBarLayout.setVisibility(View.GONE);
        }
        //터치가 안되게
        else if(loading ==true){
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            progressBarLayout.setVisibility(View.VISIBLE);
        }
    }

    /**
     * This is for clearFocus when you touch not edittextView
     * For Security
     * */
    private void setFocusSystem() {
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
        progressBarLayout = (LinearLayout)findViewById(R.id.login_progressbar_layout_login_act);
        mAuth = FirebaseAuth.getInstance();
    }

    //이메일 체크 정규식 함수
    public static boolean isEmailValid(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

}