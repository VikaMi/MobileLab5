package com.example.v.vikaApp.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.v.vikaApp.R;
import com.example.v.vikaApp.Validation;

public class MainActivity extends AppCompatActivity {

    private static final String USER_PREF = "USER_PREF";
    private static final String USER_LIST = "USER_LIST";

    private Button mShowCatsButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        initViews();


        mShowCatsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CatsActivity.class);
                startActivity(intent);
            }
        });

    }

    private void initViews() {
        mShowCatsButton = findViewById(R.id.cat_button);
    }

}
