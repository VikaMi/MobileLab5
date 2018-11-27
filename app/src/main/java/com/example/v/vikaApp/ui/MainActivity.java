package com.example.v.vikaApp.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.v.vikaApp.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private static final String USER_PREF = "USER_PREF";
    private static final String USER_LIST = "USER_LIST";


    private SharedPreferences mUserPref;

    public static Intent getStartIntent(Context context) {
        return new Intent(context, MainActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({ R.id.button_cat})
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.button_cat:
                startActivity(CatsActivity.getStartIntent(MainActivity.this));
                break;
        }
    }


}
